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

package com.aeonium.javafx.actions.util;

import java.util.HashMap;
import java.util.Map;

/**
 * A generic map for holding one instance per class.
 * <br>
 * Cf. http://stackoverflow.com/questions/10194855/java-map-key-class-value-instance-of-that-class.
 *
 * @author robert rohm
 */
public class InstanceMap<T> {

  private Map<Class<?>, Object> map = new HashMap<>();

  public <T> void put(Class<T> type, T instance) {
    if (type == null) {
      throw new NullPointerException("Type is null");
    }
    map.put(type, instance);
  }

  public <T> T get(Class<T> type) {
    return type.cast(map.get(type));
  }
}
