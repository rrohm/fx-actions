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
import com.aeonium.javafx.actions.annotations.FXKeyEventAction;
import com.aeonium.javafx.actions.annotations.FXKeyEventActions;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default handler for FXKeyEventAction annotations.
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultFXKeyEventsActionHandler implements AnnotationHandler<FXKeyEventActions> {

  private static final Logger LOG = Logger.getLogger(DefaultFXKeyEventsActionHandler.class.getName());

  private FXActionManager manager;

  {
    // 
  }

  public DefaultFXKeyEventsActionHandler() {
  }

  public DefaultFXKeyEventsActionHandler(FXActionManager manager) {
    this.manager = manager;
  }

  @Override
  public void handle(Object controller, Field field, FXKeyEventActions annotation) {
    LOG.log(Level.FINEST, "DefaultFXKeyEventsActionHandler.handle {0}", annotation);
    
    FXKeyEventAction[] keyeventActions = annotation.value();
    for (FXKeyEventAction keyeventAction : keyeventActions) {
      try {
        String name = keyeventAction.action().getName();
        final FXAbstractAction action = (FXAbstractAction) this.manager.getAction((Class<FXAbstractAction>) Class.forName(name));

        DefaultFXKeyEventActionHandler.applyFXActionEvent(field.get(controller), action, keyeventAction);

      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
        Logger.getLogger(DefaultFXKeyEventsActionHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

//  private void applyFXActionEvent(Object control, final FXAbstractAction action, FXKeyEventAction annotation) {
//
//    if (control instanceof Node) {
//      Node node = (Node) control;
//
//      node.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
//
////        System.out.printf(
////                "applyFXActionEvent > EventHandler(KeyEvent.KEY_RELEASED '%s' '%s' \n",
////                event.getCode().name(),
////                annotation.keycode());
//
//        if (event.getCode().name().equals(annotation.keycode())) {
//          if (annotation.altDown()) {
//            if (!event.isAltDown()) {
//              return;
//            }
//          }
//          if (annotation.ctrlDown()) {
//            if (!event.isControlDown()) {
//              return;
//            }
//          }
//          if (annotation.shiftDown()) {
//            if (!event.isShiftDown()) {
//              return;
//            }
//          }
//          action.onAction(event);
//        }
//      });
//
//    }
//  }
}
