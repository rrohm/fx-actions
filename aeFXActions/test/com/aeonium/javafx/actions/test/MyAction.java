/*
 * Copyright (C) 2016 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

package com.aeonium.javafx.actions.test;

import com.aeonium.javafx.actions.FXAbstractAction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

/**
 * A simple example action - a mere "dummy".
 *
 * @author robert rohm
 */
public class MyAction extends FXAbstractAction{

  public MyAction() {
    this.setText("_Erstens ...");
    this.setTooltip(new Tooltip("Die erste Aktion"));
    this.setImage(new Image("com/aeonium/javafx/actions/ae_icons_16_task.png"));

// does not throw an exception, but does not show the image either. (?):
//    this.setImage(new Image("file:ae_icons_16_task.png"));
  }



  @FXML
  @Override
  public void onAction(Event event){
    System.out.println("!!!!!!!!!!!!!!!!!!!!");
  }
}
