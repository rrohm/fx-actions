/*
 * Copyright (C) 2014 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

import static com.aeonium.javafx.behaviour.FXAbstractBehaviour.BehaviourAssignmentMode.ADD;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 * @deprecated Experimental, currently not in use!
 */
public class FXAbstractBehaviour {

  protected BehaviourAssignmentMode assignmentMode = ADD;

  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragDetectedProperty">onDragDetected</a>
   */
  protected EventHandler<? super MouseEvent> onDragDetected = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragDoneProperty">onDragDone</a>
   */
  protected EventHandler<? super DragEvent> onDragDone = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragDroppedProperty">onDragDropped</a>
   */
  protected EventHandler<? super DragEvent> onDragDropped = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragEnteredProperty">onDragEntered</a>
   */
  protected EventHandler<? super DragEvent> onDragEntered = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragExitedProperty">onDragExited</a>
   */
  protected EventHandler<? super DragEvent> onDragExited = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onDragOverProperty">onDragOver</a>
   */
  protected EventHandler<? super DragEvent> onDragOver = null;

  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDragEnteredProperty">onMouseDragEntered</a>
   */
  protected EventHandler<? super MouseDragEvent> onMouseDragEntered = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDragExitedProperty">onMouseDragExited</a>
   */
  protected EventHandler<? super MouseDragEvent> onMouseDragExited = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDragOverProperty">onMouseDragOver</a>
   */
  protected EventHandler<? super MouseDragEvent> onMouseDragOver = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDragReleasedProperty">onMouseDragReleased</a>
   */
  protected EventHandler<? super MouseDragEvent> onMouseDragReleased = null;

  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseDraggedPropertyProperty">onMouseDraggedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseDragged = null;

  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseClickedPropertyProperty">onMouseClickedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseClicked = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseEnteredPropertyProperty">onMouseEnteredProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseEntered = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseExitedPropertyProperty">onMouseExitedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseExited = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseMovedPropertyProperty">onMouseMovedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseMoved = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMousePressedPropertyProperty">onMousePressedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMousePressed = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onMouseReleasedPropertyProperty">onMouseReleasedProperty</a>
   */
  protected EventHandler<? super MouseEvent> onMouseReleased = null;

  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onKeyPressedProperty">onKeyPressed</a>
   */
  protected EventHandler<? super KeyEvent> onKeyPressed = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onKeyReleasedProperty">onKeyReleased</a>
   */
  protected EventHandler<? super KeyEvent> onKeyReleased = null;
  /**
   * @see <a href="http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#onKeyTypedProperty">onKeyTyped</a>
   */
  protected EventHandler<? super KeyEvent> onKeyTyped = null;



  public void bind(final Node node){
    this.bind(node, this.assignmentMode);
  }

  public void bind(final Node node, final BehaviourAssignmentMode assignmentMode){
    if (assignmentMode.equals(BehaviourAssignmentMode.ADD)) {
      addBehaviour(node, this);
    } else {
      assignBehaviour(node, this);
    }
  }

  private static void assignBehaviour(final Node node, final FXAbstractBehaviour behaviour) {
    if (behaviour.onDragDetected != null) node.setOnDragDetected(behaviour.onDragDetected);
    if (behaviour.onDragDone != null)     node.setOnDragDone(behaviour.onDragDone);
    if (behaviour.onDragDropped != null)  node.setOnDragDropped(behaviour.onDragDropped);
    if (behaviour.onDragEntered != null)  node.setOnDragEntered(behaviour.onDragEntered);
    if (behaviour.onDragExited != null)   node.setOnDragExited(behaviour.onDragExited);
    if (behaviour.onDragOver != null)     node.setOnDragOver(behaviour.onDragOver);

    if (behaviour.onKeyPressed != null)   node.setOnKeyPressed(behaviour.onKeyPressed);
    if (behaviour.onKeyReleased != null)  node.setOnKeyReleased(behaviour.onKeyReleased);
    if (behaviour.onKeyTyped != null)     node.setOnKeyTyped(behaviour.onKeyTyped);

    if (behaviour.onMouseClicked != null)     node.setOnMouseClicked(behaviour.onMouseClicked);
    if (behaviour.onMouseDragEntered != null) node.setOnMouseDragEntered(behaviour.onMouseDragEntered);
    if (behaviour.onMouseDragExited != null)  node.setOnMouseDragExited(behaviour.onMouseDragExited);
    if (behaviour.onMouseDragOver != null)    node.setOnMouseDragOver(behaviour.onMouseDragOver);
    if (behaviour.onMouseDragReleased != null) node.setOnMouseDragReleased(behaviour.onMouseDragReleased);
    if (behaviour.onMouseDragged != null)     node.setOnMouseDragged(behaviour.onMouseDragged);
    if (behaviour.onMouseEntered != null)     node.setOnMouseEntered(behaviour.onMouseEntered);
    if (behaviour.onMouseExited != null)      node.setOnMouseExited(behaviour.onMouseExited);
    if (behaviour.onMouseMoved != null)       node.setOnMouseMoved(behaviour.onMouseMoved);
    if (behaviour.onMousePressed != null)     node.setOnMousePressed(behaviour.onMousePressed);
    if (behaviour.onMouseReleased != null)    node.setOnMouseReleased(behaviour.onMouseReleased);
  }

  private static void addBehaviour(final Node node, final FXAbstractBehaviour behaviour) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * @return the assignmentMode
   */
  public BehaviourAssignmentMode getAssignmentMode() {
    return assignmentMode;
  }

  /**
   * @param assignmentMode the assignmentMode to set
   */
  public void setAssignmentMode(BehaviourAssignmentMode assignmentMode) {
    this.assignmentMode = assignmentMode;
  }


  public enum BehaviourAssignmentMode {
    /**
     * Add a listener for this event type.
     */
    ADD,
    /**
     * Assign a listener for this event type, eventually replacing previous listeners.
     */
    ASSIGN
  }

}
