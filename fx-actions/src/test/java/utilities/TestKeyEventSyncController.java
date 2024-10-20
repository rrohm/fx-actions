/*
 * Copyright (C) 2024 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

import com.aeonium.javafx.actions.FXActionManager;
import com.aeonium.javafx.actions.annotations.FXAManager;
import com.aeonium.javafx.actions.annotations.FXKeyEventAction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Test dummy controller with one button and a key event action on it.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class TestKeyEventSyncController {

  @FXML
  @FXKeyEventAction(action = DummyKeySyncAction.class, keycode = "CTRL+C")
  private Button button1;

  @FXAManager
  private FXActionManager actionManager;

  public FXActionManager getActionManager() {
    return actionManager;
  }

  public void setButton1(Button button1) {
    this.button1 = button1;
  }

}
