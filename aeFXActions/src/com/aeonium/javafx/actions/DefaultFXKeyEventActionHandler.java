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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * Default handler for FXKeyEventAction annotations.
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultFXKeyEventActionHandler implements AnnotationHandler<FXKeyEventAction> {

  private static final Logger LOG = Logger.getLogger(MultiKeyHandler.class.getName());

  /**
   * 
   */
  private static final Map<Node, MultiKeyHandler> handlers = new HashMap<>();

  private FXActionManager manager;

  public DefaultFXKeyEventActionHandler() {
  }

  public DefaultFXKeyEventActionHandler(FXActionManager manager) {
    this.manager = manager;
  }

  @Override
  public void handle(Object controller, Field field, FXKeyEventAction fxAction) {
    try {
      String name = fxAction.action().getName();
      final FXAbstractAction action = (FXAbstractAction) this.manager.getAction((Class<FXAbstractAction>) Class.forName(name));

      DefaultFXKeyEventActionHandler.applyFXActionEvent(field.get(controller), action, fxAction);

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
      Logger.getLogger(DefaultFXKeyEventActionHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void applyFXActionEvent(Object control, final FXAbstractAction action, FXKeyEventAction annotation) {

    if (control instanceof Node) {
      Node node = (Node) control;

      MultiKeyHandler handler = handlers.get(node);
      if (handler == null) {
        handler = new MultiKeyHandler();
        handlers.put(node, handler);
        node.addEventHandler(KeyEvent.KEY_RELEASED, handler);
      }

      KeyCombination keyCombination = KeyCodeCombination.keyCombination(annotation.keycode());
      handler.register(action, keyCombination);
//
//      node.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
//
//        public void handle(KeyEvent event) {
//          if (event.getCode().name().equals(annotation.keycode())) {
//            if (annotation.altDown()) {
//              if (!event.isAltDown()) {
//                return;
//              }
//            }
//            if (annotation.ctrlDown()) {
//              if (!event.isControlDown()) {
//                return;
//              }
//            }
//            if (annotation.shiftDown()) {
//              if (!event.isShiftDown()) {
//                return;
//              }
//            }
//            action.onAction(event);
//          }
//        }
//      });

    }
  }

  /**
   * The default key event handler.
   */
  public static class MultiKeyHandler implements EventHandler<KeyEvent> {

    private final Map<KeyCombination, FXAbstractAction> actions = new HashMap<>();

    @Override
    public void handle(KeyEvent event) {

      Set<KeyCombination> keySet = this.actions.keySet();
      for (KeyCombination keys : keySet) {
        if (keys.match(event)) {
          final FXAbstractAction action = actions.get(keys);

          if (action.isRunning() || action.isDisabled()) {
            return;
          }

          if (action.isDoExecuteAsync()) {
            LOG.finest("MultiKeyHandler exec async");
            action.setLastEvent(event);
            if (!action.stateProperty().get().equals(Worker.State.READY)) {
              action.reset();
            }
            action.start();
          } else {
            LOG.finest("MultiKeyHandler exec sync");
            action.onAction(event);
          }
          return;
        }
      }
    }

    private void register(FXAbstractAction action, KeyCombination keyCombination) {
      this.actions.put(keyCombination, action);
    }

  }
}