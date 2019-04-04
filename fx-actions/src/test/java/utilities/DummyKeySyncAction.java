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

import com.aeonium.javafx.actions.FXAbstractAction;
import javafx.event.Event;

/**
 * Dummy key action with synchronous execution.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class DummyKeySyncAction extends FXAbstractAction {

  private boolean onActionCalled = false;

  public DummyKeySyncAction() {
    this.doExecuteAsync = false;
  }
  

  @Override
  protected void onAction(Event event) {
    super.onAction(event);
    this.onActionCalled = true;
  }

  public boolean isOnActionCalled() {
    return onActionCalled;
  }

}
