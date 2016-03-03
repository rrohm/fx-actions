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

import com.aeonium.javafx.actions.annotations.FXAction;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import com.aeonium.javafx.actions.FXActionManager;
import com.aeonium.javafx.actions.annotations.FXAManager;
import java.lang.reflect.InvocationTargetException;

/**
 * An example controller, check it out for information on how to use the
 * annotations.
 *
 * @author robert rohm
 */
public class FXMLDocumentController implements Initializable {

  @FXML
  @FXAction(action = MyAction.class)
  private Label label;

  @FXML
  @FXAction(action = MyAction.class)
  private MenuItem miAction;

  @FXML
  @FXAction(action = MyAction.class)
  private Button button;

  @FXML
  @FXAction(action = MySecondAction.class)
  private Button meTooButton;

  // optional: get the actions manager in order to bind action properties
  @FXAManager
  private FXActionManager actionManager;

  @FXML
  private CheckBox cb;

  // Replaced:
//  @FXML
//  private MyAction myAction = new MyAction();
//  @FXML
  // Replaced:
//  private void handleButtonAction(ActionEvent event) {
//    System.out.println("You clicked me!");
//    label.setText("Hello World!");
//  }
  @FXML
  private void handleTestAction(ActionEvent event) {
    System.out.println("You clicked me!");
//    this.miAction.setDisable(true); // geht!!!
    this.miAction.disableProperty().set(true); // geht!!!
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    System.out.println(this.getClass().getName() + " initializing ...");

    try {
      this.actionManager.getAction(MyAction.class).disableProperty().bind(this.cb.selectedProperty());
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchMethodException ex) {
      Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalArgumentException ex) {
      Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvocationTargetException ex) {
      Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
