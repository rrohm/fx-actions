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

package com.aeonium.javafx.actions;

import com.aeonium.javafx.actions.annotations.AnnotationHandler;
import com.aeonium.javafx.actions.annotations.FXAManager;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultFXManagerHandler implements AnnotationHandler<FXAManager> {
  private FXActionManager manager;

  public DefaultFXManagerHandler() {
  }

  DefaultFXManagerHandler(FXActionManager manager) {
    this.manager = manager;
  }

  @Override
  public void handle(Object controller, Field field, FXAManager fxAction) {
    try {
      field.set(controller, this.manager);

    } catch (IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(DefaultFXManagerHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
