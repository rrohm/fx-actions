/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aeonium.javafx.demoapp;

import com.aeonium.javafx.actions.FXAbstractAction;
import javafx.fxml.FXML;
import javafx.event.Event;

/**
 * Another dummy action, for testing purposes.
 * 
 * @author robert
 */
public class MySecondAction extends FXAbstractAction{

  @FXML
  @Override
  public void onAction(Event event){
    System.out.println("That was me, the second action.");
  }
}
