/*
 * Copyright (C) 2019 Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;.
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
package com.aeonium.javafx.actions.exceptions;

import java.lang.reflect.Field;
import javafx.scene.control.TextInputControl;

/**
 * This runtime exception indicates that a control or object needs to be an
 * instance of javafx.scene.control.TextInputControl or a descending type.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class TextInputControlRequiredException extends RuntimeException {

  public TextInputControlRequiredException() {
    super("An instance of type " + TextInputControl.class.getName() + " is required.");
  }

  public TextInputControlRequiredException(String fieldName, Class actualClass) {
    super("Field " + fieldName + " is of type " + actualClass.getName() + ". Type " + TextInputControl.class.getName() + " or a descendant is required.");
  }

  public TextInputControlRequiredException(Field field) {
    super("Field " + field.getName() + " is of type " + field.getType().getName() + ". Type " + TextInputControl.class.getName() + " or a descendant is required.");
  }
}
