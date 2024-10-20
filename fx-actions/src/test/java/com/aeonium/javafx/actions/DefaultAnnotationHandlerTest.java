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
package com.aeonium.javafx.actions;

import utilities.DummyAction;
import utilities.TestController;
import com.aeonium.javafx.actions.annotations.FXAction;
import java.lang.reflect.Field;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.aeonium.fxunit.FXUnit;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utilities.TestLabelController;
import utilities.TestMenuItemController;

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
  public void testHandle_Button() throws Exception {
    System.out.println("handle_Button");
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
    
    assertFalse(button1.disabledProperty().get());
    action.disableProperty().set(true);
    
    assertTrue(button1.disabledProperty().get());
  }
  @Test
  public void testHandle_Button_noDeco() throws Exception {
    System.out.println("handle_Button_noDeco");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestController controller = new TestController();
    final Button button1 = new Button();
    final Field field = controller.getClass().getDeclaredField("button1");
    final FXAction fxAction = DummyAction.createDummyAction(false, false, false, false, null);
    field.setAccessible(true);
    controller.setButton1(button1);
    DefaultAnnotationHandler instance = new DefaultAnnotationHandler(fxActionManager);
    instance.handle(controller, field, fxAction);

    DummyAction action = fxActionManager.getAction(DummyAction.class);
    
    assertFalse(button1.disabledProperty().get());
    action.disableProperty().set(true);
    
    assertTrue(button1.disabledProperty().get());
  }
  
  @Test
  public void testHandle_Label() throws Exception {
    System.out.println("handle_Label");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestLabelController controller = new TestLabelController();
    final Label label = new Label();
    final Field field = controller.getClass().getDeclaredField("label1");
    final FXAction fxAction = DummyAction.createDummyAction(true, true, true, true, null);
    field.setAccessible(true);
    controller.setLabel1(label);
    DefaultAnnotationHandler instance = new DefaultAnnotationHandler(fxActionManager);
    instance.handle(controller, field, fxAction);

    DummyAction action = fxActionManager.getAction(DummyAction.class);
    
    assertFalse(label.disabledProperty().get());
    action.disableProperty().set(true);
    
    assertTrue(label.disabledProperty().get());
  }
  
  @Test
  public void testHandle_Label_noDeco() throws Exception {
    System.out.println("handle_Label_noDeco");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestLabelController controller = new TestLabelController();
    final Label label = new Label();
    final Field field = controller.getClass().getDeclaredField("label1");
    final FXAction fxAction = DummyAction.createDummyAction(false, false, false, false, null);
    field.setAccessible(true);
    controller.setLabel1(label);
    DefaultAnnotationHandler instance = new DefaultAnnotationHandler(fxActionManager);
    instance.handle(controller, field, fxAction);

    DummyAction action = fxActionManager.getAction(DummyAction.class);
    
    assertFalse(label.disabledProperty().get());
    action.disableProperty().set(true);
    
    assertTrue(label.disabledProperty().get());
  }
  
  @Test
  public void testHandle_MenuItem() throws Exception {
    System.out.println("handle_MenuItem");
    final FXActionManager fxActionManager = new FXActionManager();
    final TestMenuItemController controller = new TestMenuItemController();
    final MenuItem menuItem = new MenuItem();
    final Field field = controller.getClass().getDeclaredField("menuItem1");
    final FXAction fxAction = DummyAction.createDummyAction(true, true, true, true, null);
    field.setAccessible(true);
    
    controller.setMenuItem1(menuItem);
    DefaultAnnotationHandler instance = new DefaultAnnotationHandler(fxActionManager);
    DummyAction action = fxActionManager.getAction(DummyAction.class);
//    action.imageProperty().set(new Image(""));
    instance.handle(controller, field, fxAction);

    
    assertFalse(menuItem.disableProperty().get());
    action.disableProperty().set(true);
    
    assertTrue(menuItem.disableProperty().get());
  }

}
