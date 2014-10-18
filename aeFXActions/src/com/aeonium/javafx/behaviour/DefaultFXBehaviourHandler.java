/*
 * Copyright (C) 2014 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

package com.aeonium.javafx.behaviour;

import com.aeonium.javafx.actions.FXActionManager;
import com.aeonium.javafx.actions.annotations.AnnotationHandler;
import com.aeonium.javafx.behaviour.annotations.FXBehaviour;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;

/**
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultFXBehaviourHandler implements AnnotationHandler<FXBehaviour> {

  private FXActionManager manager;

  public DefaultFXBehaviourHandler() {
  }

  public DefaultFXBehaviourHandler(FXActionManager manager) {
    this.manager = manager;
  }

  @Override
  public void handle(Object controller, Field field, FXBehaviour action) {
    try {
      System.out.println("DefaultFXBehaviourHandler " + controller + ", " + field.getName() + " " + action.getClass().getName());

      FXBehaviour fxBehaviour = field.getAnnotation(FXBehaviour.class);
      String name = fxBehaviour.behaviour().getName();

      FXAbstractBehaviour behaviour = this.manager.getBehaviour((Class<FXAbstractBehaviour>) Class.forName(name), fxBehaviour.useSharedInstance());

      Object control = field.get(controller);
      behaviour.bind((Node) control, behaviour.getAssignmentMode());

    } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
      Logger.getLogger(DefaultFXBehaviourHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
