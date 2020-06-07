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

import com.aeonium.javafx.actions.annotations.FXKeyEventAction;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.aeonium.fxunit.FXUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utilities.DummyKeyAction;
import utilities.DummyKeySyncAction;
import utilities.TestKeyEventController;
import utilities.TestKeyEventSyncController;

/**
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class DefaultKeyEventAnnotationHandlerTest {
  
  public DefaultKeyEventAnnotationHandlerTest() {
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
   * Test of handle method, of class DefaultKeyEventAnnotationHandler.
   * @throws java.lang.Exception any
   */
  @Test
  public void testHandle_KEY_PRESSED() throws Exception {
    System.out.println("handle_KEY_PRESSED");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestKeyEventController controller = new TestKeyEventController();
    final Button button = new Button();
    final Field field = TestKeyEventController.class.getDeclaredField("button1");
    field.setAccessible(true);
    controller.setButton1(button);
    FXKeyEventAction fxAction = new FXKeyEventAction() {
      @Override
      public FXKeyEventAction.EventType type() {
        return EventType.KEY_PRESSED;
      }

      @Override
      public String keycode() {
        return "CTRL+C";
      }

      @Override
      public Class<? extends FXAbstractAction> action() {
        return DummyKeyAction.class;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return FXKeyEventAction.class;
      }
    };
    DefaultKeyEventAnnotationHandler instance = new DefaultKeyEventAnnotationHandler(fxActionManager);
    instance.handle(controller, field, fxAction);
    
    button.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "c", "c", KeyCode.C, false, true, false, false));
  }

  @Test
  public void testHandle_KEY_PRESSED_sync() throws Exception {
    System.out.println("handle_KEY_PRESSED");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestKeyEventSyncController controller = new TestKeyEventSyncController();
    final Button button = new Button();
    final Field field = TestKeyEventSyncController.class.getDeclaredField("button1");
    field.setAccessible(true);
    controller.setButton1(button);
    FXKeyEventAction fxAction = new FXKeyEventAction() {
      @Override
      public FXKeyEventAction.EventType type() {
        return EventType.KEY_PRESSED;
      }

      @Override
      public String keycode() {
        return "CTRL+C";
      }

      @Override
      public Class<? extends FXAbstractAction> action() {
        return DummyKeySyncAction.class;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return FXKeyEventAction.class;
      }
    };
    DefaultKeyEventAnnotationHandler instance = new DefaultKeyEventAnnotationHandler(fxActionManager);
    instance.handle(controller, field, fxAction);
    
    button.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "c", "c", KeyCode.C, false, true, false, false));
  }
  
  @Test
  public void testHandle_KEY_RELEASED() throws Exception {
    System.out.println("handle_KEY_RELEASED");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestKeyEventSyncController controller = new TestKeyEventSyncController();
    final Button button = new Button();
    final Field field = TestKeyEventSyncController.class.getDeclaredField("button1");
    field.setAccessible(true);
    controller.setButton1(button);
    FXKeyEventAction fxAction = new FXKeyEventAction() {
      @Override
      public FXKeyEventAction.EventType type() {
        return EventType.KEY_RELEASED;
      }

      @Override
      public String keycode() {
        return "CTRL+C";
      }

      @Override
      public Class<? extends FXAbstractAction> action() {
        return DummyKeySyncAction.class;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return FXKeyEventAction.class;
      }
    };
    DefaultKeyEventAnnotationHandler instance = new DefaultKeyEventAnnotationHandler(fxActionManager);
    instance.handle(controller, field, fxAction);
    
    button.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "c", "c", KeyCode.C, false, true, false, false));
  }
  @Test
  public void testHandle_KEY_TYPED() throws Exception {
    System.out.println("handle_KEY_TYPED");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestKeyEventSyncController controller = new TestKeyEventSyncController();
    final Button button = new Button();
    final Field field = TestKeyEventSyncController.class.getDeclaredField("button1");
    field.setAccessible(true);
    controller.setButton1(button);
    FXKeyEventAction fxAction = new FXKeyEventAction() {
      @Override
      public FXKeyEventAction.EventType type() {
        return EventType.KEY_TYPED;
      }

      @Override
      public String keycode() {
        return "CTRL+C";
      }

      @Override
      public Class<? extends FXAbstractAction> action() {
        return DummyKeySyncAction.class;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return FXKeyEventAction.class;
      }
    };
    DefaultKeyEventAnnotationHandler instance = new DefaultKeyEventAnnotationHandler(fxActionManager);
    instance.handle(controller, field, fxAction);
    
    button.fireEvent(new KeyEvent(KeyEvent.KEY_TYPED, "c", "c", KeyCode.C, false, true, false, false));
  }
  
}
