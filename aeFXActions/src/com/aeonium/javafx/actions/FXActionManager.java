/*
 * Copyright (C) 2018 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.aeonium.javafx.actions;

import com.aeonium.javafx.behaviour.FXAbstractBehaviour;
import com.aeonium.javafx.behaviour.DefaultFXBehaviourHandler;
import com.aeonium.javafx.actions.util.InstanceMap;
import com.aeonium.javafx.actions.annotations.AnnotationHandler;
import com.aeonium.javafx.actions.annotations.FXAManager;
import com.aeonium.javafx.actions.annotations.FXAction;
import com.aeonium.javafx.behaviour.FXBehaviour;
import com.aeonium.javafx.actions.annotations.FXKeyEventAction;
import com.aeonium.javafx.actions.annotations.FXKeyEventActions;
import com.aeonium.javafx.behaviour.DefaultFXBehavioursHandler;
import com.aeonium.javafx.behaviour.FXBehaviours;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Callback;

/**
 * <p>
 * The FXActionManager is a controller factory as well as an annotation
 * processor. It is used to create the controller for a JavaFX FXML document,
 * and subsequently search the controller instance for runtime annotations that
 * describe the mapping of actions to ui elements. Use in in a way like: </p>
 *
 * <pre>
 *
 * {@literal public void start(Stage stage) throws Exception {
 * FXActionManager myActionControllerFactory = new FXActionManager();
 *
 * // Shortcut, if you have a fxml without stylesheets
 * //Parent root = FXMLLoader.load(
 * //        getClass().getResource("FXMLDocument.fxml"),
 * //        null,
 * //        null, myActionControllerFactory);
 *
 * // use this approach if the fxml uses stylesheets:
 * FXMLLoader fxmlLoader = new FXMLLoader();
 * fxmlLoader.setLocation(getClass().getResource("FXMLDocument.fxml"));
 * fxmlLoader.setControllerFactory(myActionControllerFactory);
 * Parent root = (Parent) fxmlLoader.load();
 *
 * myActionControllerFactory.initActions();
 *
 * // optional: get the action by its type and parametrize it:
 * MyAction action = myActionControllerFactory.getAction(MyAction.class);
 * action.setText("Erste Aktion");
 *
 * Scene scene = new Scene(root);
 *
 * stage.setScene(scene);
 * stage.show();
 * }
 * }
 * </pre>
 *
 * <p>
 * As the example shows, the <code>.getAction()</code>-Method is used as a
 * universal factory method for the action instances. You may create actions
 * yourself, but this is not the recommended way.
 * </p>
 * <p>
 * <strong>Please note: </strong>This approach implies that your actions should
 * be thought of as "quasi-singletons", i.e., the FXActionManager only knows one
 * instance of each action class. This is a design constraint that should be
 * taken care of.
 * </p>
 *
 * @author robert rohm
 */
public class FXActionManager implements Callback<Class<?>, Object> {

  private static final Logger LOG = Logger.getLogger(FXActionManager.class.getName());

  /**
   * List of controlles registered with this action manager instance.
   */
  private final List<Object> myControllers = Collections.synchronizedList(new ArrayList<>());

  /**
   * List of the controllers that are already initialized.
   */
  private final List<Object> myProcessedControllers = Collections.synchronizedList(new ArrayList<>());

  /**
   * Map for the action instances - at present, the action mananager maintaines
   * only a single instance for each action class.
   */
  private final InstanceMap<? extends FXAbstractAction> instanceMap = new InstanceMap<>();

  /**
   * An observable list for currently running tasks - the action manager puts
   * any action into this list as soon as it gets startet, and it removes it
   * from this list, if it is done.
   */
  private final ObservableList<Task> currentTasks = FXCollections.observableArrayList();

  /**
   * Property for the list of currently running tasks.
   */
  private final ListProperty<Task> currentTaskProp = new SimpleListProperty(this.currentTasks);

  /**
   * Whether to execute actions synchronously or asynchronously - defaults to
   * true, since you might not want to block the UI.
   */
  private final BooleanProperty doActionsAsync = new SimpleBooleanProperty(true);

  /**
   * Map for maintaining the mapping of handlers to action classes.
   */
  private final Map<Class, AnnotationHandler> handlerMap;

  /**
   * Lock for synchronizing access to the controllers list.
   */
  private final Lock myControllersLock = new ReentrantLock();

  public FXActionManager() {
    this.handlerMap = new HashMap<>();
    this.handlerMap.put(FXAction.class, new DefaultAnnotationHandler(this));
    this.handlerMap.put(FXKeyEventAction.class, new DefaultKeyEventAnnotationHandler(this));
    this.handlerMap.put(FXKeyEventActions.class, new DefaultKeyEventsAnnotationHandler(this));
    this.handlerMap.put(FXBehaviour.class, new DefaultFXBehaviourHandler(this));
    this.handlerMap.put(FXBehaviours.class, new DefaultFXBehavioursHandler(this));

    this.currentTasks.addListener((ListChangeListener.Change<? extends Task> change) -> {
      LOG.log(Level.FINEST, "tasks list changed: {0}", change);
      
      while (change.next()) {
        if (change.wasAdded()) {
          for (final Task task : change.getAddedSubList()) {
            LOG.log(Level.FINEST, "task added: {0}", task);
            
            /*
            * if task done/failed/cancelled: remove listener and remove task from list
            */
            final EventHandler eventHandler = new EventHandler() {
              
              @Override
              public void handle(Event t) {
                LOG.log(Level.FINEST, "removing listener because of: {0}", t.getEventType());
                
                if (t.getEventType().equals(WorkerStateEvent.WORKER_STATE_CANCELLED)
                        || t.getEventType().equals(WorkerStateEvent.WORKER_STATE_FAILED)
                        || t.getEventType().equals(WorkerStateEvent.WORKER_STATE_SUCCEEDED)) {
                  task.removeEventHandler(WorkerStateEvent.ANY, this);
                  currentTasks.remove(task);
                }
              }
            };
            task.addEventHandler(WorkerStateEvent.ANY, eventHandler);
          }
        }
      }
    });
  }

  /**
   * This method is a standard callback to produce a controller instance of the
   * given type, registers it with the action manager an tries to process
   * framework annotations – <strong>this method is NOT meant ot be invoked by
   * the application directly.</strong>
   *
   * @param controllerClass The controller class to create a new instance from. 
   * @return The new controller instance.
   */
  @Override
  public Object call(Class<?> controllerClass) {
    Object o = null;
    try {
      o = controllerClass.newInstance();

      // gather instance
      this.addController(o);
      // inject actions manager if needed
      this.processManagerAnnotations(o);

    } catch (InstantiationException | IllegalAccessException ex) {
      LOG.log(Level.WARNING, "Cannot instantiate {0}", controllerClass);
    }
    return o;
  }

  /**
   * Process annotations in the given object, i.e.: inject instances of the
   * manager or the actions in the fields of the object. The actual handling
   * (injection etc.) is delegated to the annotation handlers mapped to the
   * annotation types.
   *
   * @param o
   */
  private void processAnnotations(Object o) {
    LOG.log(Level.FINEST, "processAnnotations {0}", o);

    Class<?> klasse = o.getClass();

    Field[] fields = klasse.getDeclaredFields();

    for (Field field : fields) {
      final Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
      if (declaredAnnotations.length == 0) {
        continue;
      }

      Object control = null;

      field.setAccessible(true);

      for (Class annotationClass : this.handlerMap.keySet()) {
        if (field.isAnnotationPresent(annotationClass)) {

          Annotation annotation = field.getAnnotation(annotationClass);

          this.handlerMap.get(annotationClass).handle(o, field, annotation);
        }
      }

//      if (field.isAnnotationPresent(FXBehaviour.class)) {
//        FXBehaviour fxBehaviour = field.getAnnotation(FXBehaviour.class);
//        String name = fxBehaviour.behaviour().getName();
//
//        try {
//          FXAbstractBehaviour behaviour = this.getBehaviour((Class<FXAbstractBehaviour>) Class.forName(name), fxBehaviour.useSharedInstance());
//
//          behaviour.bind((Node) control, behaviour.getAssignmentMode());
//
//        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
//          Logger.getLogger(FXActionManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }
    }
  }

  /**
   * Inject instances of this manager.
   *
   * @param o
   */
  private void processManagerAnnotations(Object o) {
    Class<?> klasse = o.getClass();

    Field[] fields = klasse.getDeclaredFields();

    for (Field field : fields) {

      try {
        if (field.isAnnotationPresent(FXAManager.class)) {
          field.setAccessible(true);
          field.set(o, this);
        }
      } catch (IllegalArgumentException | IllegalAccessException ex) {
        LOG.log(Level.SEVERE, null, ex);
      }
    }
  }

  private void addController(Object o) {
    try {
      myControllersLock.lock();
      this.myControllers.add(o);
    } finally {
      myControllersLock.unlock();
    }
  }

  /**
   * Initialize the actions that are annotated in the controllers, i.e., process
   * the annotations of all objects that have been added to the controllers
   * list. Each object is only processed once, so it is safe to invoke this
   * method repeatedly.
   */
  public synchronized void initActions() {
//    final Iterator<Object> iterator = myControllers.iterator();
//    while (iterator.hasNext()) {
//      Object object = myControllers.iterator().next();
//      if (!this.myProcessedControllers.contains(object)) {
//        this.processAnnotations(object);
//      }
//      this.myProcessedControllers.add(object);
//    }
    // leads to concurrent modification exception!
    try {
      myControllersLock.lock();
      for (Object object : myControllers) {
        if (!this.myProcessedControllers.contains(object)) {
          this.processAnnotations(object);
        }
        this.myProcessedControllers.add(object);
      }
    } finally {
      myControllersLock.unlock();
    }
  }

  /**
   * A variant of {@link #initActions()} for use with already instantiated
   * controllers.
   *
   *
   * @param controller The controller to be initialized
   */
  public synchronized void initActions(Object controller) {
    this.addController(controller);
    this.processManagerAnnotations(controller);
    this.initActions();
  }

  /**
   * Factory method for action instances. An action is first looked for in the
   * instances map. If it is not found, it gets created and registered in the
   * map. Hence, this method manages always a single instance of every action.
   *
   * <p>
   * This method also works with nested classes. If your action class is nested
   * within a JavaFX controller, it may be important that the single instance of
   * the action references members of one controller instance. In this case,
   * provide a static getInstance()-Method that makes the controller behave like
   * a (pseudo-)singleton.</p>
   *
   * @param <T> The action type
   * @param c The action class type
   * @return The managed instance of the action class.
   * @throws ClassNotFoundException Thrown when the action class is not
   * accesible
   * @throws InstantiationException Thrown when the action class is not
   * accesible
   * @throws IllegalAccessException Thrown when access to the getInstance method
   * in the controller is not accessible.
   * @throws java.lang.NoSuchMethodException Thrown when there is no getInstance
   * method in the controller
   * @throws java.lang.reflect.InvocationTargetException Thrown when the
   * getInstance method cannot be invoked
   */
  public <T extends FXAbstractAction> T getAction(Class<T> c) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
    T o = this.instanceMap.get(c);
    if (o == null) {

      // Action has an enclosing class, i.e. is an inner class?
      Class<?> enclosingClass = c.getEnclosingClass();

      if (enclosingClass != null) {
        Class<?>[] argtypes = {};
        final Method methodGetInstance = enclosingClass.getMethod("getInstance", argtypes);

        if (methodGetInstance != null) {
          Object enclosingInstance = methodGetInstance.invoke(null, (Object[]) argtypes);
          Constructor<T> declaredConstructor = c.getDeclaredConstructor(enclosingClass);
          o = declaredConstructor.newInstance(enclosingInstance);

        } else {
          Object enclosingInstance = enclosingClass.newInstance();
          Constructor<T> declaredConstructor = c.getDeclaredConstructor(enclosingClass);
          o = declaredConstructor.newInstance(enclosingInstance);
        }

      } else {
        o = c.newInstance();
      }

      o.setManager(this);
      this.instanceMap.put(c, o);
    }
    return o;
  }

  /**
   * Fetch an instance of the behaviour type, either a new one or an already
   * created instance from the internal instance map.
   *
   * @deprecated Experimental - needs to be moved to the aeFXBehaviour project.
   * @param <T> Any descendant of FXAbstractBehaviour
   * @param c The behaviour class
   * @param isSingleton Whether to maintain only one instance or to create
   * instances for each node that the behaviour is to be applied to.
   * @return The behaviour instance
   * @throws InstantiationException ... if the behaviour object cannot be
   * instaniated. This may happen if it does not have a default constructor.
   * @throws IllegalAccessException ... if other problems arise when
   * instantiating the behaviour object.
   */
  public <T extends FXAbstractBehaviour> T getBehaviour(Class<T> c, boolean isSingleton) throws InstantiationException, IllegalAccessException {
    if (isSingleton) {
      T o = this.instanceMap.get(c);
      if (o == null) {
        o = c.newInstance();
        this.instanceMap.put(c, o);
      }
      return o;

    } else {
      return c.newInstance();
    }
  }

  /**
   * Default method to get an instance of a behaviour class.
   *
   * @param <T> Any descendant of FXAbstractBehaviour
   * @param c The behaviour class
   * @return The behaviour instance
   * @throws InstantiationException ... if the behaviour object cannot be
   * instaniated. This may happen if it does not have a default constructor.
   * @throws IllegalAccessException ... if other problems arise when
   * instantiating the behaviour object.
   */
  public <T extends FXAbstractBehaviour> T getBehaviour(Class<T> c) throws InstantiationException, IllegalAccessException {
    return c.newInstance();
  }

  public ObservableList<Task> getCurrentTasks() {
    return currentTaskProp.get();
  }

  /**
   * Add a new handler for a new annotation type.
   *
   * @param actionAnnotation The annotation to be handled.
   * @param handler The handler for the annotation.
   */
  public void addHandler(Class<? extends Annotation> actionAnnotation, AnnotationHandler handler) {
    this.handlerMap.put(actionAnnotation, handler);
  }

  /**
   * Returns a reference to the list property that holds the currently running
   * tasks. You may use this for progress monitoring etc.
   *
   * @return The list property for current tasks.
   */
  public ListProperty<Task> currentTasksProperty() {
    return currentTaskProp;
  }

  /**
   * Whether the FXActionManager instance executes the actions in general
   * asynchronosly or sanchronously.
   *
   * @return Flag for sync. or async execution.
   */
  public boolean isDoActionsAsync() {
    return doActionsAsync.get();
  }

  /**
   * Determine whether the actions should be in general executed snchronously or
   * asynchronously - default is async.
   *
   * @param value Flag for sync. or async execution.
   */
  public void setDoActionsAsync(boolean value) {
    doActionsAsync.set(value);
  }

  /**
   * Whether the FXActionManager instance executes the actions in general
   * asynchronosly or sanchronously.
   *
   * @return JavaFX property for the flag.
   */
  public BooleanProperty doActionsAsyncProperty() {
    return doActionsAsync;
  }

  /**
   * Default action handler for the managed nodes: provides for asynchronous as
   * well as synchronous execution of the assigned action.
   *
   * @param <T> The event type.
   */
  public class DefaultActionHandler<T extends Event> implements EventHandler<T> {

    private final FXAbstractAction action;

    public DefaultActionHandler(FXAbstractAction action) {
      this.action = action;
    }

    @Override
    public void handle(T t) {
      t.consume();

      if (action.isRunning() || action.isDisabled()) {
        return;
      }

      if (doActionsAsync.get()) {
        // async exec:
        action.setLastEvent(t);
        if (!action.stateProperty().get().equals(Worker.State.READY)) {
          action.reset();
        }
        action.start();
      } else {
        action.onAction(t); // synchronous execution
      }
    }
  }
}
