/*
 * Copyright (C) 2024 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

import javafx.scene.Node;

/**
 * This runtime exception type indicates that you tried to attach an action or
 * behaviour to a controle or any other object that is not an instance of
 * <code>javafx.scene.Node</code> or a descendant. It is used by the framework
 * itself, but you are encouraged to use it in custom impoementations, too.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class NodeTypeRequiredException extends RuntimeException {

  public NodeTypeRequiredException() {
    super("An instance of type " + Node.class.getName() + " is required.");
  }

  public NodeTypeRequiredException(String fieldName, Class actualClass) {
    super("Field " + fieldName + " is of type " + actualClass.getName() + ". Type " + Node.class.getName() + " or a descendant is required.");
  }

}
