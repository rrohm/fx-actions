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

import javafx.scene.control.Tab;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link TextInputControlRequiredException}.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class TextInputControlRequiredExceptionTest {

  public TextInputControlRequiredExceptionTest() {
  }


  @Test
  public void testCTR() {
    TextInputControlRequiredException instance = new TextInputControlRequiredException();
    assertFalse(instance.getMessage().isEmpty());
  }

  @Test
  public void testCTR_Message() {
    final String TEST = "TEST";
    TextInputControlRequiredException instance = new TextInputControlRequiredException(TEST, Tab.class);
    assertFalse(instance.getMessage().isEmpty());
  }

}
