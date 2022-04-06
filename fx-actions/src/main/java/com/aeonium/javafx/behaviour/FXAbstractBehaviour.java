/*
 * Copyright (C) 2021 Robert Rohm  &lt;r.rohm@aeonium-systems.de&gt;.
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
package com.aeonium.javafx.behaviour;

import static com.aeonium.javafx.behaviour.FXAbstractBehaviour.Mode.ADD;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 * Base class for implementing you own declaratively assignable behaviour
 * classes. Basically, a behaviour class can be seen as a class that consists of
 * a number of event handler methods for handling various events. These handler
 * methods can be assigned or added to any JavaFX node.
 *
 * <p>
 * Defining a behaviour class feels much like defining the behaviour of a JavaFX
 * node itself: you create event handler methods and assign them to event
 * properties. A FXAbstractBehaviour implementation uses simple POJO fields. If
 * your behaviour class does not need to maintain state, you may use static
 * method references, like this:
 * </p>
 * <pre><code>
 * public class MyBehaviour extends FXAbstractBehaviour {
 *
 * &nbsp;&nbsp;public MyBehaviour() {
 * &nbsp;&nbsp;&nbsp;&nbsp;this.onKeyPressed = MyBehaviour::handle;
 * &nbsp;&nbsp;}
 *
 * &nbsp;&nbsp;private static void handle(KeyEvent event) {
 * &nbsp;&nbsp;&nbsp;&nbsp;// ...
 * &nbsp;&nbsp;}
 * }
 * </code></pre>
 * <p>
 * You may use one event handler property or many or all of them. You may define
 * single handler methods for each supported event, or you may use the same
 * handler method for all supported events. That's totally up to you.
 * </p>
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public abstract class FXAbstractBehaviour {

  protected Mode assignmentMode = ADD;

  /**
   * Assign a handler method to this field if your behaviour class shall define
   * DRAG_DETECTED behaviour.
   *
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragDetectedProperty">onDragDetected</a>
   */
  protected EventHandler<? super MouseEvent> onDragDetected = null;
  /**
   * Assign a handler method to this field if your behaviour class shall define
   * DRAG_DONE behaviour.
   *
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragDoneProperty">onDragDone</a>
   */
  protected EventHandler<? super DragEvent> onDragDone = null;
  /**
   * Assign a handler method to this field if your behaviour class shall define
   * DRAG_DROPPED behaviour.
   *
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragDroppedProperty">onDragDropped</a>
   */
  protected EventHandler<? super DragEvent> onDragDropped = null;
  /**
   * Assign a handler method to this field if your behaviour class shall define
   * DRAG_ENTERED behaviour.
   *
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragEnteredProperty">onDragEntered</a>
   */
  protected EventHandler<? super DragEvent> onDragEntered = null;
  /**
   * Assign a handler method to this field if your behaviour class shall define
   * DRAG_EXITED behaviour.
   *
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragExitedProperty">onDragExited</a>
   */
  protected EventHandler<? super DragEvent> onDragExited = null;
  /**
   * Assign a handler method to this field if your behaviour class shall define
   * DRAG_OVER behaviour.
   *
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragOverProperty">onDragOver</a>
   */
  protected EventHandler<? super DragEvent> onDragOver = null;

  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDragEnteredProperty">onMouseDragEntered</a>
   */
  protected EventHandler<? super MouseDragEvent> onMouseDragEntered = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDragExitedProperty">onMouseDragExited</a>
   */
  protected EventHandler<? super MouseDragEvent> onMouseDragExited = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDragOverProperty">onMouseDragOver</a>
   */
  protected EventHandler<? super MouseDragEvent> onMouseDragOver = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDragReleasedProperty">onMouseDragReleased</a>
   */
  protected EventHandler<? super MouseDragEvent> onMouseDragReleased = null;

  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDraggedPropertyProperty">onMouseDraggedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseDragged = null;

  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseClickedPropertyProperty">onMouseClickedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseClicked = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseEnteredPropertyProperty">onMouseEnteredProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseEntered = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseExitedPropertyProperty">onMouseExitedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseExited = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseMovedPropertyProperty">onMouseMovedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseMoved = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMousePressedPropertyProperty">onMousePressedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMousePressed = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseReleasedPropertyProperty">onMouseReleasedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseReleased = null;

  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onKeyPressedProperty">onKeyPressed</a>
   */
  protected EventHandler<? super KeyEvent> onKeyPressed = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onKeyReleasedProperty">onKeyReleased</a>
   */
  protected EventHandler<? super KeyEvent> onKeyReleased = null;
  /**
   * @see
   * <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onKeyTypedProperty">onKeyTyped</a>
   */
  protected EventHandler<? super KeyEvent> onKeyTyped = null;

  /**
   * Bind the behaviour to a node, using the default assignment mode, i.e., the
   * behaviour is added to eventually other already existing event handlers.
   * This method cannot be overridden, since it is a special version of 
   * {@link FXAbstractBehaviour#bind(javafx.scene.Node, com.aeonium.javafx.behaviour.FXAbstractBehaviour.Mode) }
   * and simply delegates to that method. If you need some custom binding
   * behaviour, override that method instead.
   *
   * @param node The node that is to be augmented with new behaviour.
   */
  public final void bind(final Node node) {
    if (node == null) {
      throw new NullPointerException("You try to apply a FXBehaviour to a null node: " + this.getClass().getName());
    }
    
    this.bind(node, this.assignmentMode);
  }

  /**
   * Bind the behaviour to a node, defining the assignment mode: a behaviour can
   * be either added to existing event handlers or assigned to an event,
   * replacing existing handlers.
   *
   * <p>
   * If you need some individual binding mechanism, you may override this
   * method.
   * </p>
   *
   * @param node The node that is to be augmented with new behaviour.
   * @param assignmentMode The assignment mode (ADD|ASSIGN)
   */
  public void bind(final Node node, final Mode assignmentMode) {
    if (node == null) {
      throw new NullPointerException("You try to apply a FXBehaviour to a null node: " + this.getClass().getName());
    }
    
    switch (assignmentMode) {
      case ADD:
        addBehaviour(node, this);
        break;
      case FILTER:
        addBehaviour(node, this, true);
        break;
      default:
        assignBehaviour(node, this);
        break;
    }
  }

  /**
   * Assign a behaviour to a node, i.e., if the behaviour defines an event
   * handler for an event, existing handlers get replaced.
   *
   * @param node
   * @param behaviour
   */
  private static void assignBehaviour(final Node node, final FXAbstractBehaviour behaviour) {
    if (behaviour.onDragDetected != null) {
      node.setOnDragDetected(behaviour.onDragDetected);
    }
    if (behaviour.onDragDone != null) {
      node.setOnDragDone(behaviour.onDragDone);
    }
    if (behaviour.onDragDropped != null) {
      node.setOnDragDropped(behaviour.onDragDropped);
    }
    if (behaviour.onDragEntered != null) {
      node.setOnDragEntered(behaviour.onDragEntered);
    }
    if (behaviour.onDragExited != null) {
      node.setOnDragExited(behaviour.onDragExited);
    }
    if (behaviour.onDragOver != null) {
      node.setOnDragOver(behaviour.onDragOver);
    }

    if (behaviour.onKeyPressed != null) {
      node.setOnKeyPressed(behaviour.onKeyPressed);
    }
    if (behaviour.onKeyReleased != null) {
      node.setOnKeyReleased(behaviour.onKeyReleased);
    }
    if (behaviour.onKeyTyped != null) {
      node.setOnKeyTyped(behaviour.onKeyTyped);
    }

    if (behaviour.onMouseClicked != null) {
      node.setOnMouseClicked(behaviour.onMouseClicked);
    }
    if (behaviour.onMouseDragEntered != null) {
      node.setOnMouseDragEntered(behaviour.onMouseDragEntered);
    }
    if (behaviour.onMouseDragExited != null) {
      node.setOnMouseDragExited(behaviour.onMouseDragExited);
    }
    if (behaviour.onMouseDragOver != null) {
      node.setOnMouseDragOver(behaviour.onMouseDragOver);
    }
    if (behaviour.onMouseDragReleased != null) {
      node.setOnMouseDragReleased(behaviour.onMouseDragReleased);
    }
    if (behaviour.onMouseDragged != null) {
      node.setOnMouseDragged(behaviour.onMouseDragged);
    }
    if (behaviour.onMouseEntered != null) {
      node.setOnMouseEntered(behaviour.onMouseEntered);
    }
    if (behaviour.onMouseExited != null) {
      node.setOnMouseExited(behaviour.onMouseExited);
    }
    if (behaviour.onMouseMoved != null) {
      node.setOnMouseMoved(behaviour.onMouseMoved);
    }
    if (behaviour.onMousePressed != null) {
      node.setOnMousePressed(behaviour.onMousePressed);
    }
    if (behaviour.onMouseReleased != null) {
      node.setOnMouseReleased(behaviour.onMouseReleased);
    }
  }

  /**
   * Add a behaviour to a node, i.e., if the behaviour defines a handler for an
   * event, this handler is added, not replacing eventually existing handlers.
   *
   * @param node The node.
   * @param behaviour The behaviour that provides the hander(s)
   * @param asFilter Whether the handler(s) get applied as event filter or event
   * handler.
   */
  private static void addBehaviour(final Node node, final FXAbstractBehaviour behaviour, boolean asFilter) {
    
    if (behaviour.onDragDone != null) {
      if (asFilter) {
        node.addEventFilter(DragEvent.DRAG_DONE, behaviour.onDragDone);
      } else {
        node.addEventHandler(DragEvent.DRAG_DONE, behaviour.onDragDone);
      }
    }
    if (behaviour.onDragDropped != null) {
      if (asFilter) {
        node.addEventFilter(DragEvent.DRAG_DROPPED, behaviour.onDragDropped);
      } else {
        node.addEventHandler(DragEvent.DRAG_DROPPED, behaviour.onDragDropped);
      }
    }
    if (behaviour.onDragEntered != null) {
      if (asFilter) {
        node.addEventFilter(DragEvent.DRAG_ENTERED, behaviour.onDragEntered);
      } else {
        node.addEventHandler(DragEvent.DRAG_ENTERED, behaviour.onDragEntered);
      }
    }
    if (behaviour.onDragExited != null) {
      if (asFilter) {
        node.addEventFilter(DragEvent.DRAG_EXITED, behaviour.onDragExited);
      } else {
        node.addEventHandler(DragEvent.DRAG_EXITED, behaviour.onDragExited);
      }
    }
    if (behaviour.onDragOver != null) {
      if (asFilter) {
        node.addEventFilter(DragEvent.DRAG_OVER, behaviour.onDragOver);
      } else {
        node.addEventHandler(DragEvent.DRAG_OVER, behaviour.onDragOver);
      }
    }

    if (behaviour.onKeyPressed != null) {
      if (asFilter) {
        node.addEventFilter(KeyEvent.KEY_PRESSED, behaviour.onKeyPressed);
      } else {
        node.addEventHandler(KeyEvent.KEY_PRESSED, behaviour.onKeyPressed);
      }
    }
    if (behaviour.onKeyReleased != null) {
      if (asFilter) {
        node.addEventFilter(KeyEvent.KEY_RELEASED, behaviour.onKeyReleased);
      } else {
        node.addEventHandler(KeyEvent.KEY_RELEASED, behaviour.onKeyReleased);
      }
    }
    if (behaviour.onKeyTyped != null) {
      if (asFilter) {
        node.addEventFilter(KeyEvent.KEY_TYPED, behaviour.onKeyTyped);
      } else {
        node.addEventHandler(KeyEvent.KEY_TYPED, behaviour.onKeyTyped);
      }
    }

    if (behaviour.onDragDetected != null) {
      if (asFilter) {
        node.addEventFilter(MouseDragEvent.DRAG_DETECTED, behaviour.onDragDetected);
      } else {
        node.addEventHandler(MouseDragEvent.DRAG_DETECTED, behaviour.onDragDetected);
      }
    }
    if (behaviour.onMouseDragEntered != null) {
      if (asFilter) {
        node.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, behaviour.onMouseDragEntered);
      } else {
        node.addEventHandler(MouseDragEvent.MOUSE_DRAG_ENTERED, behaviour.onMouseDragEntered);
      }
    }
    if (behaviour.onMouseDragExited != null) {
      if (asFilter) {
        node.addEventFilter(MouseDragEvent.MOUSE_DRAG_EXITED, behaviour.onMouseDragExited);
      } else {
        node.addEventHandler(MouseDragEvent.MOUSE_DRAG_EXITED, behaviour.onMouseDragExited);
      }
    }
    if (behaviour.onMouseDragOver != null) {
      if (asFilter) {
        node.addEventFilter(MouseDragEvent.MOUSE_DRAG_OVER, behaviour.onMouseDragOver);
      } else {
        node.addEventHandler(MouseDragEvent.MOUSE_DRAG_OVER, behaviour.onMouseDragOver);
      }
    }
    if (behaviour.onMouseDragReleased != null) {
      if (asFilter) {
        node.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, behaviour.onMouseDragReleased);
      } else {
        node.addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, behaviour.onMouseDragReleased);
      }
    }

    if (behaviour.onMouseClicked != null) {
      if (asFilter) {
        node.addEventFilter(MouseEvent.MOUSE_CLICKED, behaviour.onMouseClicked);
      } else {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, behaviour.onMouseClicked);
      }
    }
    if (behaviour.onMouseDragged != null) {
      if (asFilter) {
        node.addEventFilter(MouseEvent.MOUSE_DRAGGED, behaviour.onMouseDragged);
      } else {
        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, behaviour.onMouseDragged);
      }
    }
    if (behaviour.onMouseEntered != null) {
      if (asFilter) {
        node.addEventFilter(MouseEvent.MOUSE_ENTERED, behaviour.onMouseEntered);
      } else {
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, behaviour.onMouseEntered);
      }
    }
    if (behaviour.onMouseExited != null) {
      if (asFilter) {
        node.addEventFilter(MouseEvent.MOUSE_EXITED, behaviour.onMouseExited);
      } else {
        node.addEventHandler(MouseEvent.MOUSE_EXITED, behaviour.onMouseExited);
      }
    }
    if (behaviour.onMouseMoved != null) {
      if (asFilter) {
        node.addEventFilter(MouseEvent.MOUSE_MOVED, behaviour.onMouseMoved);
      } else {
        node.addEventHandler(MouseEvent.MOUSE_MOVED, behaviour.onMouseMoved);
      }
    }
    if (behaviour.onMousePressed != null) {
      if (asFilter) {
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, behaviour.onMousePressed);
      } else {
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, behaviour.onMousePressed);
      }
    }
    if (behaviour.onMouseReleased != null) {
      if (asFilter) {
        node.addEventFilter(MouseEvent.MOUSE_RELEASED, behaviour.onMouseReleased);
      } else {
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, behaviour.onMouseReleased);
      }
    }
  }

  /**
   * @return the assignmentMode
   */
  public Mode getAssignmentMode() {
    return assignmentMode;
  }

  /**
   * @param assignmentMode the assignmentMode to set
   */
  public void setAssignmentMode(Mode assignmentMode) {
    this.assignmentMode = assignmentMode;
  }

  /**
   * Convenience method, add a behaviour as an event handler.
   *
   * @param node The node that the behaviour should get applied to.
   * @param behaviour The behaviour that provides event handlers.
   */
  private static void addBehaviour(Node node, FXAbstractBehaviour behaviour) {
    addBehaviour(node, behaviour, false);
  }

  /**
   * Defines the assignment mode for the behaviours - either add the behaviour
   * or replace eventually already existing event handlers.
   */
  public enum Mode {

    /**
     * Add a listener for this event type.
     */
    ADD,
    /**
     * Assign a listener for this event type, eventually replacing previously
     * existing listeners.
     */
    ASSIGN,
    /**
     * Add this behaviour as an event filter.
     */
    FILTER
  }
}
