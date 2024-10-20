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
package com.aeonium.javafx.behaviour;

import com.aeonium.javafx.actions.FXActionManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javafx.scene.control.Button;
import org.aeonium.fxunit.FXUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.TestBehaviour;
import utilities.TestControllerWithBehaviour;

/**
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultFXBehaviourHandlerTest {
  
  public DefaultFXBehaviourHandlerTest() {
  }
  
  @BeforeAll
  public static void setUpClass() {
    FXUnit.init();
  }
  
  @AfterAll
  public static void tearDownClass() {
  }
  
  @BeforeEach
  public void setUp() {
  }
  
  @AfterEach 
  public void tearDown() {
  }

  /**
   * Test of handle method, of class DefaultFXBehaviourHandler.
   */
  @Test
  public void testCTR() {
    System.out.println("ctr");
    Object controller = null;
    Field field = null;
    FXBehaviour action = null;
    DefaultFXBehaviourHandler instance = new DefaultFXBehaviourHandler();
    assertNotNull(instance);
  }
  
  @Test
  public void testHandle() throws Exception {
    System.out.println("handle");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestControllerWithBehaviour controller = new TestControllerWithBehaviour();
    final Button button1 = new Button();
    final Field field = controller.getClass().getDeclaredField("button1");
    controller.setButton1(button1);
    field.setAccessible(true);
    FXBehaviour action = new FXBehaviour() {
      @Override
      public Class<? extends FXAbstractBehaviour> behaviour() {
        return TestBehaviour.class;
      }

      @Override
      public FXAbstractBehaviour.Mode assignmentMode() {
        return FXAbstractBehaviour.Mode.ASSIGN;
      }

      @Override
      public boolean useSharedInstance() {
        return false;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return FXBehaviour.class;
      }
    };
    DefaultFXBehaviourHandler instance = new DefaultFXBehaviourHandler(fxActionManager);
    
    instance.handle(controller, field, action);
  }
  
  @Test
  public void testHandle_silentNPE() throws Exception {
    System.out.println("handle_silentNPE");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestControllerWithBehaviour controller = new TestControllerWithBehaviour();
    final Button button1 = new Button();
    final Field field = controller.getClass().getDeclaredField("button1");
    field.setAccessible(true);
    FXBehaviour action = new FXBehaviour() {
      @Override
      public Class<? extends FXAbstractBehaviour> behaviour() {
        return TestBehaviour.class;
      }

      @Override
      public FXAbstractBehaviour.Mode assignmentMode() {
        return FXAbstractBehaviour.Mode.ASSIGN;
      }

      @Override
      public boolean useSharedInstance() {
        return false;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return FXBehaviour.class;
      }
    };
    DefaultFXBehaviourHandler instance = new DefaultFXBehaviourHandler(fxActionManager);
    
    instance.handle(controller, field, action);
  }
  
}
