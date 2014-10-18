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

package com.aeonium.javafx.actions;

import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Default handler for the execution of actions, allows for asnychronous
 * execution as a service (default), generally synchronous execution as
 * defined in the action implementiation and individual synchronous execution
 * defined in the annotation.
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultActionHandler<T extends Event> implements EventHandler<T> {

  private final FXAbstractAction action;

  private boolean execSyncOnly = false;

  /**
   * Standard constructor, requires only the action instance to get handled.
   * @param action
   */
  public DefaultActionHandler(FXAbstractAction action) {
    this.action = action;
  }

  /**
   *
   * @param action
   * @param execSyncOnly Force synchronous execution.
   */
  public DefaultActionHandler(FXAbstractAction action, boolean execSyncOnly) {
    this.action = action;
    this.execSyncOnly = execSyncOnly;
  }

  @Override
  public void handle(T t) {
    System.err.println("DefaultActionHandler.handle " + this.action + " " + this.action.getText());
    t.consume();

    if (action.isRunning() || action.isDisabled()) {
      return;
    }

    if (action.doExecuteAsync && !this.execSyncOnly) {
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
