/*
 * Copyright (C) 2024 Robert Rohm  &lt;r.rohm@aeonium-systems.de&gt;.
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

/**
 * <div>The main package, contains the abstract base classes of the FXActions
 * framework and the FXActionManager.
 *
 *
 * <pre>
 *
 * {@literal public void start(Stage stage) throws Exception {
    FXActionManager myActionControllerFactory = new FXActionManager();

    // Shortcut, if you have a fxml without stylesheets
    //Parent root = FXMLLoader.load(
    //        getClass().getResource("FXMLDocument.fxml"),
    //        null,
    //        null, myActionControllerFactory);

    // use this approach if the fxml uses stylesheets:
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("FXMLDocument.fxml"));
    fxmlLoader.setControllerFactory(myActionControllerFactory);
    Parent root = (Parent) fxmlLoader.load();

    myActionControllerFactory.initActions();

    // optional: get the action by its type and parametrize it:
    MyAction action = myActionControllerFactory.getAction(MyAction.class);
    action.setText("Erste Aktion");

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
  }
 }
 * </pre>
 *
 * <p>As the example shows, the <code>.getAction()</code>-Method is used as a
 * universal factory method for the action instances. You may create actions
 * yourself, but this is not the recommended way.
 * </p>
 * <p><strong>Please note: </strong>This approach implies that your actions should be thought of as "quasi-singletons",
 * i.e., the FXActionManager only knows one instance of each action class. This
 * is a design constraint that should be taken care of.
 * </p>
 * </div>
 */
package com.aeonium.javafx.actions;
