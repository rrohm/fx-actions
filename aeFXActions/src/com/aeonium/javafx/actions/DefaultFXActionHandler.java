/*
 * Copyright (C) 2015 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

/**
 * Default handler for the FXAction annotations.
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultFXActionHandler implements AnnotationHandler<FXAction> {

  private FXActionManager manager;

  public DefaultFXActionHandler() {
  }

  public DefaultFXActionHandler(FXActionManager manager) {
    this.manager = manager;
  }

  @Override
  public void handle(Object controller, Field field, FXAction fxAction) {
    try {
      String name = fxAction.action().getName();
      final FXAbstractAction action = (FXAbstractAction) this.manager.getAction((Class<FXAbstractAction>) Class.forName(name));

      this.applyFXAction(field.get(controller), action, fxAction);

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
      Logger.getLogger(DefaultFXActionHandler.class.getName()).log(Level.SEVERE, null, ex);
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

//            labeled.setOnMouseClicked(new EventHandler<MouseEvent>() {
    // needed for headers in titledPane - TitlePane collapses onRelease, not onCLick, so we must consume onRelease
    labeled.setOnMouseReleased(new com.aeonium.javafx.actions.DefaultActionHandler<>(action, !fxAction.doAsync()));

    // doMnemonicParsing?
    labeled.setMnemonicParsing(fxAction.doMnemonicParsing());

    // doShowGraphic?
    if (!fxAction.doShowGraphic()) {
      labeled.setContentDisplay(ContentDisplay.TEXT_ONLY);
    } else if (action.getImage() != null) {
      ImageView imageView = new ImageView(action.getImage());
      labeled.setGraphic(imageView);
    }
  }

  private void initMenuItem(MenuItem menuItem, final FXAbstractAction action, FXAction fxAction) {

    menuItem.setOnAction(new com.aeonium.javafx.actions.DefaultActionHandler<>(action, !fxAction.doAsync()));

    menuItem.textProperty().bind(action.textProperty());
    // needs to be set explicitly, binding on node level (see above) does not do the trick!!!
    // MenuItem is not a Node descendant!
    menuItem.disableProperty().bind(action.disableProperty());
    menuItem.acceleratorProperty().bind(action.keyCombinationProperty());

    if (action.getImage() != null && fxAction.doShowGraphic()) {
      // action.image needs to be wrapped in a new node, since we cannot place
      // something like a common image node twice in the scene graph ...
      ImageView imageView = new ImageView(action.getImage());
      menuItem.setGraphic(imageView);
    }
  }

  private void initButtonBase(ButtonBase buttonBase, final FXAbstractAction action, FXAction fxAction) {

    buttonBase.setOnAction(new com.aeonium.javafx.actions.DefaultActionHandler<>(action, !fxAction.doAsync()));
//    buttonBase.addEventHandler(MouseEvent.MOUSE_CLICKED, new DefaultActionHandler(action));

    if (!fxAction.doShowGraphic()) {
      buttonBase.setContentDisplay(ContentDisplay.TEXT_ONLY);

    } else if (action.getImage() != null) {
      ImageView imageView = new ImageView(action.getImage());
      buttonBase.setGraphic(imageView);
    }

    if (fxAction.doShowText()) {
      buttonBase.textProperty().bind(action.textProperty());
    } else {
      buttonBase.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }
  }
}
