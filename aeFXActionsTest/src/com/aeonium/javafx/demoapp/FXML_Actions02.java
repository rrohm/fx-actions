/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeonium.javafx.demoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import com.aeonium.javafx.actions.FXActionManager;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * A simple proof-of-concept application. Have a look at it's source code to 
 * get an overview on the main concepts.
 * 
 * 
 * @author robert rohm 
 */
public class FXML_Actions02 extends Application {
  
  private static final Logger LOG = Logger.getLogger(FXML_Actions02.class.getName());
  
  private FXActionManager myActionControllerFactory;

  @Override
  public void start(Stage stage) throws Exception {
    
    LogManager.getLogManager().readConfiguration(FXML_Actions02.class.getResourceAsStream("logging.properties"));
    LOG.entering(this.getClass().getName(), "start");
    
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
    LOG.exiting(this.getClass().getName(), "start");
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
