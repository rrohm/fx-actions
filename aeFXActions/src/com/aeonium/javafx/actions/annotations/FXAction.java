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

package com.aeonium.javafx.actions.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.aeonium.javafx.actions.FXAbstractAction;
import java.lang.annotation.Repeatable;

/**
 * Marks a field with an action class.
 * 
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(FXActions.class)
public @interface FXAction {

  /**
   * The class of the action.
   * @return The action type
   */
  public Class<? extends FXAbstractAction> action();

  /**
   * Whether mnemonic parsing should be applied for labeled controls (defaults
   * to true).
   *
   * @return Whether mnemonic parsing should be applied for labeled controls.
   */
  public boolean doMnemonicParsing() default true;

  /**
   * Whether to show the action title.
   * Use this to override the display mode for single controls.
   * Default is <code>true</code>.
   * @return Whether to show the action title.
   */
  public boolean doShowText() default true;

  /**
   * Whether to show the action graphic, if any is provided.
   * Use this to override the display mode for single controls.
   * Default is <code>true</code>.
   *
   * @return Whether to show the action graphic, if any is provided.
   */
  public boolean doShowGraphic() default true;

  /**
   * Whether the action should be executed asynchronous.
   * Default is <code>true</code>.
   *
   * @return Whether the action should be executed asynchronous.
   */
  public boolean doAsync() default true;
}
