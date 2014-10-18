/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeonium.javafx.demoapp;

import com.aeonium.javafx.actions.FXActionManager;
import com.aeonium.javafx.actions.annotations.FXAManager;
import com.aeonium.javafx.actions.annotations.FXAction;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;

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
    @FXAction(action = MyAction.class, doShowText = false, doShowGraphic = true)
    private Button button1;

    @FXML
    @FXAction(action = MySecondAction.class)
    private Button meTooButton;
    
    @FXML
    @FXAction(action = MyAction.class)
    @FXAction(action = MySecondAction.class)
    private Button doubleActionButton;

    // optional: get the actions manager in order to bind action properties
    @FXAManager
    private FXActionManager actionManager;

    @FXML
    private CheckBox cb;

    @FXML
    private Label statusLabel;

    @FXML
    private ProgressBar progressBar;

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

        // Listening on the Managers current tasks list: 
        this.actionManager.currentTasksProperty().addListener(new ListChangeListener<Task>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Task> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("Tasks added: " + change.getAddedSize());
                        for (Task t : change.getAddedSubList()) {
                            statusLabel.setText(t.getTitle() + " ...");
                        }
                    }
                    if (change.wasRemoved()) {
                        System.out.println("Tasks removed: " + change.getRemovedSize());
                        for (Task t : change.getRemoved()) {
                            statusLabel.setText(t.getTitle() + " OK.");
                        }
                    }
                }
            }
        });

        // binding disabledProperty for controlling action state: 
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
