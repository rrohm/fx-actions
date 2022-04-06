/*
 * Copyright (C) 2021 Robert Rohm  &lt;r.rohm@aeonium-systems.de&gt;.
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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field with a custom behaviour class - this is the "swiss army knife"
 * for declarative behaviour.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FXBehaviour {

  /**
   * The class of the action.
   *
   * @return The action type
   */
  public Class<? extends FXAbstractBehaviour> behaviour();

  /**
   * Whether the behaviour should be added to the existing event handlers or
   * assigned in order to replace them.
   *
   * @return The assignement type.
   */
  public FXAbstractBehaviour.Mode assignmentMode() default FXAbstractBehaviour.Mode.ASSIGN;

//  /**
//   * Whether to apply the behaviour on this node as event filter and not as
//   * event handler (only applicable, if the behaviour is used with assignment
//   * mode <code>FXAbstractBehaviour.Mode.ASSIGN</code>. Per default, a behaviour
//   * is always applied as an event handler.
//   *
//   * @return Whether to apply the behaviour on this node as event filter and not
//   * as event handler.
//   */
//  public boolean asFilter() default false;

  /**
   * Whether to use a shared behaviour instance for all nodes that use this
   * behaviour.
   *
   * @return Whether to use a shared behaviour instance or create multiple
   * instances.
   * @deprecated Experimental! Better make the Behaviour implpementation
   * responsible for deciding whether to use a singleton or not. This should
   * then only serve for overriding that default. ... but check whether this
   * makes sense at all!
   */
  public boolean useSharedInstance() default false;
}
