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
import com.aeonium.javafx.actions.annotations.FXAction;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * Test dummy controller with one menu item.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class TestMenuItemController {

  @FXML
  @FXAction(action = DummyAction.class)
  private MenuItem menuItem1;

  @FXAManager
  private FXActionManager actionManager;

  public FXActionManager getActionManager() {
    return actionManager;
  }

  public void setMenuItem1(MenuItem menuItem1) {
    this.menuItem1 = menuItem1;
  }

}
