/*
 * Copyright (C) 2020 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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
package com.aeonium.javafx.actions;

import com.aeonium.javafx.actions.annotations.AnnotationHandler;
import com.aeonium.javafx.actions.annotations.FXAction;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Default handler for the FXAction annotations.
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultAnnotationHandler implements AnnotationHandler<FXAction> {

  private static final Logger LOG = Logger.getLogger(DefaultAnnotationHandler.class.getName());

  private FXActionManager manager;
  
  private final static Map<String, Image> IMAGEMAP = new HashMap<>();

  public DefaultAnnotationHandler() {
  }

  public DefaultAnnotationHandler(FXActionManager manager) {
    this.manager = manager;
  }

  @Override
  public void handle(Object controller, Field field, FXAction fxAction) {
    try {
      String name = fxAction.action().getName();
      final FXAbstractAction action = (FXAbstractAction) this.manager.getAction((Class<FXAbstractAction>) Class.forName(name));

      this.applyFXAction(field.get(controller), action, fxAction);

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
      Logger.getLogger(DefaultAnnotationHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void applyFXAction(Object control, final FXAbstractAction action, FXAction fxAction) {
    if (control instanceof Node) {
      Node node = (Node) control;
      node.disableProperty().bind(action.disableProperty());
    }

    if (control instanceof ButtonBase) {
      initButtonBase((ButtonBase) control, action, fxAction);

    } else if (control instanceof MenuItem) {
      initMenuItem((MenuItem) control, action, fxAction);

    } else if (control instanceof Labeled) {
      initLabeled((Labeled) control, fxAction, action);
    }

    if (control instanceof Control) {
      Control control1 = (Control) control;
      control1.tooltipProperty().bind(action.tooltipProperty());
    }
  }

  private Image getImage(String url){
    Image icon = IMAGEMAP.get(url);
    if (icon == null) {
      try {
        icon = new Image(url);
      } catch (Exception e) {
        LOG.log(Level.SEVERE, "Invalid image URL: {0}", url);
        LOG.throwing(this.getClass().getName(), "getImage", e);
      }
      IMAGEMAP.put(url, icon);
    }
    
    return icon;   
  }
  
  /**
   * Bind an action to a Labeled node or a descendant of Labeled.
   *
   * @param control
   * @param fxAction
   * @param action
   */
  private void initLabeled(Labeled labeled, FXAction fxAction, final FXAbstractAction action) {

    if (fxAction.doShowText()) {
      labeled.textProperty().bind(action.textProperty());
    } else {
      labeled.textProperty().set(null);
    }

    // needed for headers in titledPane - TitlePane collapses onRelease,
    // not onCLick, so we must consume onRelease
    labeled.setOnMouseReleased(new DefaultActionHandler<>(action, !fxAction.doAsync()));

    // doMnemonicParsing?
    labeled.setMnemonicParsing(fxAction.doMnemonicParsing());

    // doShowGraphic?
    if (!fxAction.doShowGraphic()) {
      labeled.setContentDisplay(ContentDisplay.TEXT_ONLY);
      
    } else {
      if (fxAction.imageURL() != null && !fxAction.imageURL().isEmpty()) {
        ImageView imageView = new ImageView(getImage(fxAction.imageURL()));
        labeled.setGraphic(imageView);

      } else if (action.getImage() != null) {
        ImageView imageView = new ImageView(action.getImage());
        labeled.setGraphic(imageView);
      }
    } 
      
  }

  private void initMenuItem(MenuItem menuItem, final FXAbstractAction action, FXAction fxAction) {

    menuItem.setOnAction(new DefaultActionHandler<>(action, !fxAction.doAsync()));

    menuItem.textProperty().bind(action.textProperty());
    // needs to be set explicitly, binding on node level (see above) does not do the trick!!!
    // MenuItem is not a Node descendant!
    menuItem.disableProperty().bind(action.disableProperty());
    menuItem.acceleratorProperty().bind(action.keyCombinationProperty());

    if (fxAction.doShowGraphic()) {
      if (fxAction.imageURL() != null && !fxAction.imageURL().isEmpty()) {
        // action.image needs to be wrapped in a new node, since we cannot place
        // something like a common image node twice in the scene graph ...
        ImageView imageView = new ImageView(getImage(fxAction.imageURL()));
        menuItem.setGraphic(imageView);
      } else if (action.getImage() != null){
        ImageView imageView = new ImageView(action.getImage());
        menuItem.setGraphic(imageView);
      }
    }
  }

  private void initButtonBase(ButtonBase buttonBase, final FXAbstractAction action, FXAction fxAction) {

    buttonBase.setOnAction(new DefaultActionHandler<>(action, !fxAction.doAsync()));

    if (!fxAction.doShowGraphic()) {
      buttonBase.setContentDisplay(ContentDisplay.TEXT_ONLY);

    } else {
      if (fxAction.imageURL() != null && !fxAction.imageURL().isEmpty()) {
        ImageView imageView = new ImageView(getImage(fxAction.imageURL()));
        buttonBase.setGraphic(imageView);

      } else if (action.getImage() != null) {
        ImageView imageView = new ImageView(action.getImage());
        buttonBase.setGraphic(imageView);
      }
    } 
      

    if (fxAction.doShowText()) {
      buttonBase.textProperty().bind(action.textProperty());
    } else {
      buttonBase.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }
  }
}
