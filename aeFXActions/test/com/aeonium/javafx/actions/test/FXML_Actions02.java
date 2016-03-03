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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import com.aeonium.javafx.actions.FXActionManager;

/**
 * A simple proof-of-concept application. Have a look at it's source code to
 * get an overview on the main concepts.
 *
 *
 * @author robert rohm
 */
public class FXML_Actions02 extends Application {

  private FXActionManager myActionControllerFactory;

  @Override
  public void start(Stage stage) throws Exception {
    myActionControllerFactory = new FXActionManager();

    // We would do this without our controller factory:
//    Parent root = FXMLLoader.load(
//            getClass().getResource("FXMLDocument.fxml"));

    // we would do this without stylesheets referenced in the FXML
//    Parent root = FXMLLoader.load(
//            getClass().getResource("FXMLDocument.fxml"),
//            null,
//            null, myActionControllerFactory);

    // the ONLY way to get started in javafx 2.2 if we have stylesheets in the fxml:
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("FXMLDocument.fxml"));
    fxmlLoader.setControllerFactory(myActionControllerFactory);
    Parent root = (Parent) fxmlLoader.load();

    myActionControllerFactory.initActions();


    // optional: get the action by its type and parametrize it:
    MyAction action = myActionControllerFactory.getAction(MyAction.class);
    action.setText("Erste Aktion");
    action.setTooltip(new Tooltip("Das ist die Erste Aktion"));

    Scene scene = new Scene(root);
//    scene.getStylesheets().add("styles.css");

    stage.setScene(scene);
    stage.show();
  }

  /**
   * The main() method is ignored in correctly deployed JavaFX application.
   * main() serves only as fallback in case the application can not be launched
   * through deployment artifacts, e.g., in IDEs with limited FX support.
   * NetBeans ignores main().
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
