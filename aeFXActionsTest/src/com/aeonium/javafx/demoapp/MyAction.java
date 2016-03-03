/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aeonium.javafx.demoapp;

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
    this.setImage(new Image("com/aeonium/javafx/demoapp/ae_icons_16_task.png"));
    
// does not throw an exception, but does not show the image either. (?):
//    this.setImage(new Image("file:ae_icons_16_task.png")); 
  }

  
  
  @FXML
  @Override
  public void onAction(Event event){
    System.out.println("!!!!!!!!!!!!!!!!!!!! " + event);
  }
}
