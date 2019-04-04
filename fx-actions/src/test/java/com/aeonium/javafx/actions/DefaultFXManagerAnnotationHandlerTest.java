/*
 * Copyright (C) 2019 Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;.
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

import com.aeonium.javafx.actions.annotations.FXAManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utilities.TestController;

/**
 * Test cases for {@link DefaultFXManagerAnnotationHandler}.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultFXManagerAnnotationHandlerTest {

  public DefaultFXManagerAnnotationHandlerTest() {
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
   * Test of handle method, of class DefaultFXManagerAnnotationHandler.
   */
  @Test
  public void testHandle() throws Exception {
    System.out.println("handle");
    final FXActionManager actionManager = new FXActionManager();
    final TestController controller = new TestController();
    final Field field = TestController.class.getDeclaredField("actionManager");
    field.setAccessible(true);
    FXAManager fxAction = new FXAManager() {
      @Override
      public Class<? extends Annotation> annotationType() {
        return FXAManager.class;
      }
    };
    DefaultFXManagerAnnotationHandler instance = new DefaultFXManagerAnnotationHandler(actionManager);
    instance.handle(controller, field, fxAction);

    assertNotNull(controller.getActionManager());
  }

  @Test
  public void testHandle_notInjectable() throws Exception {
    System.out.println("handle_notInjectable");
    final FXActionManager actionManager = new FXActionManager();
    final TestController controller = new TestController();
    final Field field = TestController.class.getDeclaredField("actionManager");
    field.setAccessible(false);
    FXAManager fxAction = new FXAManager() {
      @Override
      public Class<? extends Annotation> annotationType() {
        return FXAManager.class;
      }
    };
    DefaultFXManagerAnnotationHandler instance = new DefaultFXManagerAnnotationHandler(actionManager);
    instance.handle(controller, field, fxAction);

    assertNull(controller.getActionManager());
  }

}
