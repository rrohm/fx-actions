/*
 * Copyright (C) 2020 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;

/**
 * The abstract base class for all aeFXAction actions. The abstract action is a
 * service. Thus, it uses tasks for encapsulating the action logic. You can use
 * this base class in two different ways:
 * <ol>
 * <li>The first, convenient way is to implement your action logic in the
 * onAction() method. This is suitable for simple actions that will not take
 * much time - and thus will not need progress tracking.
 * </li>
 * <li>If your action needs progress tracking and a more sophisticated task
 * logic, then you better implement your custom onCreateTask factory method.
 * </li>
 * </ol>
 *
 * @author robert rohm
 */
public abstract class FXAbstractAction extends Service {

  private static final Logger LOG = Logger.getLogger(FXAbstractAction.class.getName());

  /**
   * A reference to the action manager. This will be used for common progress
   * monitoring etc.
   */
  private FXActionManager manager = null;

  private final BooleanProperty disable = new SimpleBooleanProperty();

  /**
   * A text property, as action title, button caption or menu item label.
   */
  private final StringProperty text = new SimpleStringProperty("FXAction");

  /**
   * An action may have a key combination, for those nodes that support this.
   */
  private final ObjectProperty<KeyCombination> keyCombination = new SimpleObjectProperty<>();

  /**
   * An optional tooltip for all controls that use tooltips (MenuItem does not).
   */
  private final ObjectProperty<Tooltip> tooltip = new SimpleObjectProperty<>();

  /**
   * An icon graphic for contorls that use one.
   */
  private final ObjectProperty<Image> image = new SimpleObjectProperty<>();

  /**
   * Stores a reference to the last event that triggered the action.
   */
  protected Event lastEvent;

  /**
   * Whether to execute this action asynchronously (default) or synchronously.
   */
  protected boolean doExecuteAsync = true;

  /**
   * Create a new instance.
   */
  public FXAbstractAction() {
    // no op
  }

  /**
   * Default implementation: create a new Task from the Service and put the task
   * into the manager's current tasks list. The default task invokes the
   * onAction method of the action imlementation. This method also checks if
   * your action implements the onCreateTask method in order to create a custom
   * task implementation.
   *
   * @return The tast to be executed by the service.
   */
  @Override
  protected Task createTask() {
    LOG.finest("createTask");

    final FXAbstractAction me = this;
    final Task customTask = this.onCreateTask();

    if (customTask == null) {

      // construct and return a default task that invokes onAction()
      final Task task = new Task() {

        {
          this.updateTitle(me.getText());
        }

        @Override
        protected Object call() throws Exception {
//          System.err.println("call " + this);
          try {
            LOG.entering(this.getClass().getName(), "call");
            this.updateProgress(-1.0, 1);
            // causes exception, Task can only be used on FX Thread
            this.updateMessage(me.getText().concat(" ..."));

            onAction(me.lastEvent);
            me.lastEvent = null;

            LOG.exiting(this.getClass().getName(), "call");
            this.updateProgress(0, 1);
            this.updateMessage("OK.");

          } catch (Exception e) {
            LOG.throwing(this.getClass().getName(), "call", e);
          } finally {
            // unnecessary - FXActionManager attaches an according event handler on removal.
//            manager.currentTasksProperty().remove(this);
          }
          return null;
        }
      };
      this.manager.currentTasksProperty().add(task);
      return task;
    } else {
      this.manager.currentTasksProperty().add(customTask);
      return customTask;
    }
  }

  /**
   * This is a convenience method where you can place your action logic if you
   * do not need to update task progress etc.
   *
   * @param event The event that triggered the action.
   */
  protected void onAction(Event event) {
  }

  /**
   * Override this method in order to create your own task implementation - in
   * this case you may leave the onAction Method empty.
   *
   * @return The task to be executed by the service
   */
  protected Task onCreateTask() {
    return null;
  }

  public KeyCombination getKeyCombination() {
    return this.keyCombination.get();
  }

  public void setKeyCombination(KeyCombination kc) {
    this.keyCombination.set(kc);
  }

  public ObjectProperty<KeyCombination> keyCombinationProperty() {
    return this.keyCombination;
  }

  public String getText() {
    return text.get();
  }

  public Event getLastEvent() {
    return this.lastEvent;
  }

  public void setLastEvent(Event t) {
    this.lastEvent = t;
  }

  /**
   * Set the text property value of this action - the text is uses for the
   * captions of the controls where this action gets assigned.
   *
   * @param value The action text.
   */
  public void setText(String value) {
    text.set(value);
  }

  /**
   * The text property of an action will be used as label or caption.
   *
   * @return The text property of the action, or an empty string if unused
   */
  public StringProperty textProperty() {
    return text;
  }

  /**
   * Get the optional tooltip, if provided. Null, if not.
   *
   * @return The optional tooltip, if provided. Null, if not.
   */
  public Tooltip getTooltip() {
    return tooltip.get();
  }

  /**
   * Set the optional tooltip for the action. Controls using the action will be
   * bound to this tooltip where applicable.
   * <p>
   * <strong>Attention!</strong> Since the tooltip modifies the sceene graph,
   * this method may have to be invoked on the JavaFX application thread.
   * </p>
   *
   * @param value The tooltip object.
   */
  public void setTooltip(Tooltip value) {
    tooltip.set(value);
  }

  /**
   * An optional tooltip for the action. Controls using the action will be bound
   * to this tooltip where applicable.
   *
   * @return The tooltip property or null if not assigned.
   */
  public ObjectProperty tooltipProperty() {
    return tooltip;
  }

  public boolean isDisabled() {
    return disable.get();
  }

  public void setDisabled(boolean value) {
    disable.set(value);
  }

  public BooleanProperty disableProperty() {
    return disable;
  }

  public Image getImage() {
    return image.get();
  }

  public void setImage(Image value) {
    image.set(value);
  }

  public ObjectProperty imageProperty() {
    return image;
  }

  public FXActionManager getManager() {
    return manager;
  }

  public void setManager(FXActionManager manager) {
    this.manager = manager;
  }

  public boolean isDoExecuteAsync() {
    return doExecuteAsync;
  }

  public void setDoExecuteAsync(boolean doExecuteAsync) {
    this.doExecuteAsync = doExecuteAsync;
  }
}
