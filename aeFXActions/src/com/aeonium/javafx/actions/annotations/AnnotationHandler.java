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

package com.aeonium.javafx.actions.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Interface for all annotation handlers.
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 * @param <T> The type of annotation that shall be handled by an implementation
 *            of this imterface.
 */
public interface AnnotationHandler<T extends Annotation> {

  /**
   *
   * @param controller
   * @param field
   * @param annotation
   */
  public void handle(Object controller, Field field, T annotation);

}
