/*
 * Copyright (C) 2018 Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;.
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

import com.aeonium.javafx.behaviour.FXAbstractBehaviour;
import javafx.scene.Node;

/**
 * This (unchecked!) exception is thrown if a behaviour is used on some target
 * node that is not supported by the behaviour class, e.g., if a behaviour can
 * only get applied to a TextArea instance and you try to use it on a TextField,
 * it should throw this exception.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class UnsupportedBehaviourTargetException extends RuntimeException {

  public UnsupportedBehaviourTargetException() {
    super();
  }

  public UnsupportedBehaviourTargetException(Node node, String message) {
    super(((node != null) ?node.toString() : "null" ) + " is not an appropriate target. " + message);
  }

  public UnsupportedBehaviourTargetException(Node node, FXAbstractBehaviour behaviour) {
    super(((node != null) ?node.toString() : "null" ) + " is not an appropriate target for " + behaviour);
  }
  
  public UnsupportedBehaviourTargetException(Node node, FXAbstractBehaviour behaviour, String message) {
    super(((node != null) ?node.toString() : "null" ) + " is not an appropriate target for " + behaviour + "\n" + message);
  }

}
