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

import utilities.DummyAction;
import com.aeonium.javafx.actions.annotations.AnnotationHandler;
import com.aeonium.javafx.actions.annotations.FXAction;
import com.aeonium.javafx.behaviour.FXAbstractBehaviour;
import java.lang.annotation.Annotation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import utilities.AbstractTestController;
import utilities.TestBehaviour;
import utilities.TestController;
import utilities.TestControllerNoInstanceWithAction.InnerAction2;
import utilities.TestControllerWithAction;
import utilities.TestControllerWithAction.InnerAction;

/**
 * Test cases for {@link FXActionManager}.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class FXActionManagerTest {

  public FXActionManagerTest() {
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
  
  public static class ControllerClass {
    
    @FXML
    @FXAction(action = DummyAction.class)
    private Button button1;
  }

  /**
   * Test of call method, of class FXActionManager.
   */
  @Test
  public void testCall() {
    System.out.println("call");
    Class controllerClass = TestController.class;
    FXActionManager instance = new FXActionManager();
    Object result = instance.call(controllerClass);
    
    assertTrue(result instanceof TestController);
    assertEquals(instance, ((TestController)result).getActionManager());
  }
  @Test
  public void testCall_abstractClass() {
    System.out.println("call_abstractClass");
    Class controllerClass = AbstractTestController.class;
    FXActionManager instance = new FXActionManager();
    Object result = instance.call(controllerClass);
    
    assertNull(result);
  }

  /**
   * Test of initActions method, of class FXActionManager.
   */
  @Test
  public void testInitActions_0args() {
    System.out.println("initActions");
    final Class controllerClass = TestController.class;
    final FXActionManager instance = new FXActionManager();
    final Object result = instance.call(controllerClass);
    instance.initActions();
  }

  /**
   * Test of initActions method, of class FXActionManager.
   */
  @Test
  public void testInitActions_Object() {
    System.out.println("initActions");
    TestController controller = new TestController();
    FXActionManager instance = new FXActionManager();
    instance.initActions(controller);
  }

  /**
   * Test of getAction method, of class FXActionManager.
   */
  @Test
  public void testGetAction_InnerAction_withProperCTR_getInstance() throws Exception {
    System.out.println("getAction");
    FXActionManager instance = new FXActionManager();
    InnerAction result = instance.getAction(InnerAction.class);
    
    assertNotNull(result);
    assertEquals(instance, result.getManager());
  }
  
  @Test
  public void testGetAction_InnerAction_withProperCTR_nogetInstance() throws Exception {
    System.out.println("getAction");
    FXActionManager instance = new FXActionManager();
    InnerAction2 result = instance.getAction(InnerAction2.class);
    
    assertNotNull(result);
    assertEquals(instance, result.getManager());
  }

  /**
   * Test of getBehaviour method, of class FXActionManager.
   */
  @Test
  public void testGetBehaviour_Class_boolean_true() throws Exception {
    System.out.println("getBehaviour");
    FXActionManager instance = new FXActionManager();
    FXAbstractBehaviour result1 = instance.getBehaviour(TestBehaviour.class, true);
    FXAbstractBehaviour result2 = instance.getBehaviour(TestBehaviour.class, true);
    assertNotNull(result1);
    assertNotNull(result2);
    assertSame(result1, result2);
  }
  
  @Test
  public void testGetBehaviour_Class_boolean_false() throws Exception {
    System.out.println("getBehaviour");
    FXActionManager instance = new FXActionManager();
    FXAbstractBehaviour expResult = null;
    FXAbstractBehaviour result1 = instance.getBehaviour(TestBehaviour.class, true);
    FXAbstractBehaviour result2 = instance.getBehaviour(TestBehaviour.class, false);
    assertNotNull(result1);
    assertNotNull(result2);
    assertNotSame(result1, result2);
  }

  /**
   * Test of getBehaviour method, of class FXActionManager.
   */
  @Test
  public void testGetBehaviour_Class() throws Exception {
    System.out.println("getBehaviour");
    FXActionManager instance = new FXActionManager();
    FXAbstractBehaviour result = instance.getBehaviour(TestBehaviour.class);
    assertNotNull(result);
  }

  /**
   * Test of getCurrentTasks method, of class FXActionManager.
   */
  @Test
  public void testGetCurrentTasks() {
    System.out.println("getCurrentTasks");
    FXActionManager instance = new FXActionManager();
    ObservableList<Task> expResult = null;
    ObservableList<Task> result = instance.getCurrentTasks();
    assertNotNull(result);
  }

  /**
   * Test of addHandler method, of class FXActionManager.
   */
  @Test
  public void testAddHandler() {
    System.out.println("addHandler");
    Class<? extends Annotation> actionAnnotation = null;
    AnnotationHandler handler = null;
    FXActionManager instance = new FXActionManager();
    instance.addHandler(actionAnnotation, handler);
  }

  /**
   * Test of currentTasksProperty method, of class FXActionManager.
   */
  @Test
  public void testCurrentTasksProperty() {
    System.out.println("currentTasksProperty");
    FXActionManager instance = new FXActionManager();
    ListProperty<Task> result = instance.currentTasksProperty();
    assertNotNull(result);
  }

  /**
   * Test of isDoActionsAsync method, of class FXActionManager.
   */
  @Test
  public void testIsDoActionsAsync() {
    System.out.println("isDoActionsAsync");
    FXActionManager instance = new FXActionManager();
    boolean result = instance.isDoActionsAsync();
    assertTrue(result);
  }

  /**
   * Test of setDoActionsAsync method, of class FXActionManager.
   */
  @Test
  public void testSetDoActionsAsync() {
    System.out.println("setDoActionsAsync");
    FXActionManager instance = new FXActionManager();
    instance.setDoActionsAsync(false);
    BooleanProperty result = instance.doActionsAsyncProperty();
    assertFalse(result.get());
  }
}
