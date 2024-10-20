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
package com.aeonium.javafx.actions;

import com.aeonium.javafx.actions.annotations.AnnotationHandler;
import com.aeonium.javafx.actions.annotations.FXKeyEventAction;
import com.aeonium.javafx.actions.annotations.FXKeyEventActions;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default handler for FXKeyEventActions annotations.
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultKeyEventsAnnotationHandler implements AnnotationHandler<FXKeyEventActions> {

  private static final Logger LOG = Logger.getLogger(DefaultKeyEventsAnnotationHandler.class.getName());

  private FXActionManager manager;

  public DefaultKeyEventsAnnotationHandler() {
    // no op
  }

  public DefaultKeyEventsAnnotationHandler(FXActionManager manager) {
    this.manager = manager;
  }

  @Override
  public void handle(Object controller, Field field, FXKeyEventActions annotation) {
    LOG.log(Level.INFO, "handle {0}", annotation.value().length);
    
    FXKeyEventAction[] keyeventActions = annotation.value();
    for (FXKeyEventAction keyeventAction : keyeventActions) {
      try {
        String name = keyeventAction.action().getName();
        final Class<FXAbstractAction> fxActionClass = (Class<FXAbstractAction>) Class.forName(name);
        
        final FXAbstractAction action;
        if (fxActionClass.getDeclaringClass() != null) {
          action = (FXAbstractAction) this.manager.getAction(fxActionClass, controller);
        } else {
          action = (FXAbstractAction) this.manager.getAction(fxActionClass);
        }

        DefaultKeyEventAnnotationHandler.applyFXActionEvent(field.get(controller), action, keyeventAction);

      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
        Logger.getLogger(DefaultKeyEventsAnnotationHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
