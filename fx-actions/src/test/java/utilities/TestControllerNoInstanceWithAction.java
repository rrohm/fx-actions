/*
 * Copyright (C) 2021 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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
import com.aeonium.javafx.actions.annotations.FXAction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class TestControllerNoInstanceWithAction {

  public Button getButton() {
    return button;
  }

  public void setButton(Button button) {
    this.button = button;
  }

  public static class InnerAction2 extends FXAbstractAction {

    private TestControllerNoInstanceWithAction controller;

    public InnerAction2() {
      // no op constructor
    }

    public InnerAction2(TestControllerNoInstanceWithAction controller) {
      this.controller = controller;
    }

  }

  @FXML
  @FXAction(action = InnerAction2.class)
  private Button button;
}
