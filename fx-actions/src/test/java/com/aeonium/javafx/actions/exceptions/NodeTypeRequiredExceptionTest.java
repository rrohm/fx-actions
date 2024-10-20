/*
 * Copyright (C) 2021 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

import javafx.scene.control.Tab;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for {@link NodeTypeRequiredException}.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class NodeTypeRequiredExceptionTest {

  public NodeTypeRequiredExceptionTest() {
  }

  @Test
  public void testCTR() {
    NodeTypeRequiredException instance = new NodeTypeRequiredException();
    assertFalse(instance.getMessage().isEmpty());
  }

  @Test
  public void testCTR_Message() {
    final String TEST = "TEST";
    NodeTypeRequiredException instance = new NodeTypeRequiredException(TEST, Tab.class);
    assertFalse(instance.getMessage().isEmpty());
  }

}
