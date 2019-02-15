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
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utilities.AbstractTestController;
import utilities.TestController;

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
    FXActionManager instance = new FXActionManager();
    instance.initActions();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of initActions method, of class FXActionManager.
   */
  @Test
  public void testInitActions_Object() {
    System.out.println("initActions");
    Object controller = null;
    FXActionManager instance = new FXActionManager();
    instance.initActions(controller);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAction method, of class FXActionManager.
   */
  @Test
  public void testGetAction() throws Exception {
    System.out.println("getAction");
    FXActionManager instance = new FXActionManager();
    FXAbstractAction expResult = null;
    FXAbstractAction result = instance.getAction(null);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBehaviour method, of class FXActionManager.
   */
  @Test
  public void testGetBehaviour_Class_boolean() throws Exception {
    System.out.println("getBehaviour");
    FXActionManager instance = new FXActionManager();
    FXAbstractBehaviour expResult = null;
    FXAbstractBehaviour result = instance.getBehaviour(null);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBehaviour method, of class FXActionManager.
   */
  @Test
  public void testGetBehaviour_Class() throws Exception {
    System.out.println("getBehaviour");
    FXActionManager instance = new FXActionManager();
    FXAbstractBehaviour expResult = null;
    FXAbstractBehaviour result = instance.getBehaviour(null);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of currentTasksProperty method, of class FXActionManager.
   */
  @Test
  public void testCurrentTasksProperty() {
    System.out.println("currentTasksProperty");
    FXActionManager instance = new FXActionManager();
    ListProperty<Task> expResult = null;
    ListProperty<Task> result = instance.currentTasksProperty();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isDoActionsAsync method, of class FXActionManager.
   */
  @Test
  public void testIsDoActionsAsync() {
    System.out.println("isDoActionsAsync");
    FXActionManager instance = new FXActionManager();
    boolean expResult = false;
    boolean result = instance.isDoActionsAsync();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setDoActionsAsync method, of class FXActionManager.
   */
  @Test
  public void testSetDoActionsAsync() {
    System.out.println("setDoActionsAsync");
    boolean value = false;
    FXActionManager instance = new FXActionManager();
    instance.setDoActionsAsync(value);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of doActionsAsyncProperty method, of class FXActionManager.
   */
  @Test
  public void testDoActionsAsyncProperty() {
    System.out.println("doActionsAsyncProperty");
    FXActionManager instance = new FXActionManager();
    BooleanProperty expResult = null;
    BooleanProperty result = instance.doActionsAsyncProperty();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
