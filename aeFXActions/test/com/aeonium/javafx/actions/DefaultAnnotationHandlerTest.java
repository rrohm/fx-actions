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
package com.aeonium.javafx.actions;

import utilities.DummyAction;
import utilities.TestController;
import com.aeonium.javafx.actions.annotations.FXAction;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javafx.scene.control.Button;
import org.aeonium.fxunit.FXUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultAnnotationHandlerTest {

  public DefaultAnnotationHandlerTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    FXUnit.init();
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
   * Test of handle method, of class DefaultAnnotationHandler.
   *
   * @throws java.lang.Exception any
   */
  @Test
  public void testHandle() throws Exception {
    System.out.println("handle");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestController controller = new TestController();
    final Button button1 = new Button();
    final Field field = controller.getClass().getDeclaredField("button1");
    final FXAction fxAction = DummyAction.createDummyAction(true, true, true, true, null);
    field.setAccessible(true);
    controller.setButton1(button1);
    DefaultAnnotationHandler instance = new DefaultAnnotationHandler(fxActionManager);
    instance.handle(controller, field, fxAction);

    DummyAction action = fxActionManager.getAction(DummyAction.class);

  }

}
