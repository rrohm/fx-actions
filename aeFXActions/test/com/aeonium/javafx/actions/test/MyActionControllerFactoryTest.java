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


package com.aeonium.javafx.actions.test;

import com.aeonium.javafx.actions.FXActionManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author robert
 */
public class MyActionControllerFactoryTest {

  public MyActionControllerFactoryTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of call method, of class FXActionManager.
   */
  @Test
  public void testCall() {
    System.out.println("call");
    Class p = null;
    FXActionManager instance = new FXActionManager();
    Object expResult = null;
    Object result = instance.call(p);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of initActions method, of class FXActionManager.
   */
  @Test
  public void testInitActions() {
    System.out.println("initActions");
    FXActionManager instance = new FXActionManager();
    instance.initActions();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
