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
package com.aeonium.javafx.behaviour.annotations;

import com.aeonium.javafx.behaviour.FXAbstractBehaviour;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field with a custom behaviour class - this is the "swiss army knife"
 * of the behaviour annotations.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 * @deprecated Experimental - do not use yet!
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
  public FXAbstractBehaviour.BehaviourAssignmentMode assignmentMode() default FXAbstractBehaviour.BehaviourAssignmentMode.ASSIGN;

  /**
   * Whether to use a shared behaviour instance for all nodes that use this
   * behaviour.
   *
   * @return Whether to use a shared behaviour instance or create multiple
   * instances.
   * @deprecated Experimental! Better make the Behaviour implpementation
   * responsible for deciding whether to use a singleton or not. This should
   * then only server for overriding that default. ... but check whether this
   * makes sense at all!
   */
  public boolean useSharedInstance() default false;
}
