/*
 * Copyright (C) 2019 Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;.
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
package utilities;

import com.aeonium.javafx.behaviour.FXAbstractBehaviour;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 * Dummy Behaviour for testing.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class TestBehaviour extends FXAbstractBehaviour {

  private boolean isKeyPressed = false;
  private boolean isKeyReleased = false;
  private boolean isKeyTyped = false;
  private boolean isDragDetected = false;
  private boolean isDragDone = false;
  private boolean isDragExited = false;
  private boolean isDragDropped = false;
  private boolean isDragEntered = false;
  private boolean isDragOver = false;
  private boolean isMouseClicked = false;
  private boolean isMouseDragEntered = false;
  private boolean isMouseDragExited = false;
  private boolean isMouseDragOver = false;
  private boolean isMouseDragReleased = false;
  private boolean isMouseDragged = false;
  private boolean isMouseEntered = false;
  private boolean isMouseExited = false;
  private boolean isMouseMoved = false;
  private boolean isMousePressed = false;
  private boolean isMouseReleased = false;

  public TestBehaviour() {
    this.onKeyPressed = (event) -> {
      this.isKeyPressed = true;
      event.consume();
    };
    this.onKeyReleased = (event) -> {
      this.isKeyReleased = true;
      event.consume();
    };
    this.onKeyTyped = (event) -> {
      this.isKeyTyped = true;
      event.consume();
    };
    this.onDragDetected = (event) -> {
      this.isDragDetected = true;
      event.consume();
    };
    this.onDragDone = (event) -> {
      this.isDragDone = true;
      event.consume();
    };
    this.onDragDropped = (event) -> {
      this.isDragDropped = true;
      event.consume();
    };
    this.onDragEntered = (event) -> {
      this.isDragEntered = true;
      event.consume();
    };
    this.onDragExited = (event) -> {
      this.isDragExited = true;
      event.consume();
    };
    this.onDragOver = (event) -> {
      this.isDragOver = true;
      event.consume();
    };
    this.onMouseClicked = (event) -> {
      this.isMouseClicked = true;
      event.consume();
    };
    this.onMouseDragEntered = (event) -> {
      this.isMouseDragEntered = true;
      event.consume();
    };
    this.onMouseDragExited = (event) -> {
      this.isMouseDragExited = true;
      event.consume();
    };
    this.onMouseDragOver = (event) -> {
      this.isMouseDragOver = true;
      event.consume();
    };
    this.onMouseDragReleased = (event) -> {
      this.isMouseDragReleased = true;
      event.consume();
    };
    this.onMouseDragged = (event) -> {
      this.isMouseDragged = true;
      event.consume();
    };
    this.onMouseEntered = (event) -> {
      this.isMouseEntered = true;
      event.consume();
    };
    this.onMouseExited = (event) -> {
      this.isMouseExited = true;
      event.consume();
    };
    this.onMouseMoved = (event) -> {
      this.isMouseMoved = true;
      event.consume();
    };
    this.onMousePressed = (event) -> {
      this.isMousePressed = true;
      event.consume();
    };
    this.onMouseReleased = (event) -> {
      this.isMouseReleased = true;
      event.consume();
    };
  }

  public EventHandler<? super KeyEvent> getOnKeyPressed() {
    return onKeyPressed;
  }

  public EventHandler<? super MouseEvent> getOnDragDetected() {
    return onDragDetected;
  }

  public EventHandler<? super DragEvent> getOnDragDone() {
    return onDragDone;
  }

  public EventHandler<? super DragEvent> getOnDragDropped() {
    return onDragDropped;
  }

  public EventHandler<? super DragEvent> getOnDragEntered() {
    return onDragEntered;
  }

  public EventHandler<? super DragEvent> getOnDragExited() {
    return onDragExited;
  }

  public EventHandler<? super DragEvent> getOnDragOver() {
    return onDragOver;
  }

  public EventHandler<? super KeyEvent> getOnKeyReleased() {
    return onKeyReleased;
  }

  public EventHandler<? super KeyEvent> getOnKeyTyped() {
    return onKeyTyped;
  }

  public EventHandler<? super MouseEvent> getOnMouseClicked() {
    return onMouseClicked;
  }

  public EventHandler<? super MouseDragEvent> getOnMouseDragEntered() {
    return onMouseDragEntered;
  }

  public EventHandler<? super MouseDragEvent> getOnMouseDragExited() {
    return onMouseDragExited;
  }

  public EventHandler<? super MouseDragEvent> getOnMouseDragOver() {
    return onMouseDragOver;
  }

  public EventHandler<? super MouseDragEvent> getOnMouseDragReleased() {
    return onMouseDragReleased;
  }

  public EventHandler<? super MouseEvent> getOnMouseDragged() {
    return onMouseDragged;
  }

  public EventHandler<? super MouseEvent> getOnMouseEntered() {
    return onMouseEntered;
  }

  public EventHandler<? super MouseEvent> getOnMouseExited() {
    return onMouseExited;
  }

  public EventHandler<? super MouseEvent> getOnMouseMoved() {
    return onMouseMoved;
  }

  public EventHandler<? super MouseEvent> getOnMousePressed() {
    return onMousePressed;
  }

  public EventHandler<? super MouseEvent> getOnMouseReleased() {
    return onMouseReleased;
  }

  public boolean isKeyPressed() {
    return isKeyPressed;
  }

  public boolean isKeyReleased() {
    return isKeyReleased;
  }

  public boolean isKeyTyped() {
    return isKeyTyped;
  }

  public boolean isDragDetected() {
    return isDragDetected;
  }

  public boolean isDragDone() {
    return isDragDone;
  }

  public boolean isDragExited() {
    return isDragExited;
  }

  public boolean isDragDropped() {
    return isDragDropped;
  }

  public boolean isDragEntered() {
    return isDragEntered;
  }

  public boolean isDragOver() {
    return isDragOver;
  }

  public boolean isMouseClicked() {
    return isMouseClicked;
  }

  public boolean isMouseDragEntered() {
    return isMouseDragEntered;
  }

  public boolean isMouseDragExited() {
    return isMouseDragExited;
  }

  public boolean isMouseDragOver() {
    return isMouseDragOver;
  }

  public boolean isMouseDragReleased() {
    return isMouseDragReleased;
  }

  public boolean isMouseDragged() {
    return isMouseDragged;
  }

  public boolean isMouseEntered() {
    return isMouseEntered;
  }

  public boolean isMouseExited() {
    return isMouseExited;
  }

  public boolean isMouseMoved() {
    return isMouseMoved;
  }

  public boolean isMousePressed() {
    return isMousePressed;
  }

  public boolean isMouseReleased() {
    return isMouseReleased;
  }

}
