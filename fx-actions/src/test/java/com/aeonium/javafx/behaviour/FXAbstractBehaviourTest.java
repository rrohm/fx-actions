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
package com.aeonium.javafx.behaviour;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.aeonium.fxunit.FXUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utilities.TestBehaviour;
import utilities.TestEmptyBehaviour;

/**
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class FXAbstractBehaviourTest {
  
  public FXAbstractBehaviourTest() {
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

  @Test(expected = NullPointerException.class)
  public void testBind_Node_NPE() {
    System.out.println("bind NPE");
    Node node = null;
    TestBehaviour instance = new TestBehaviour();
    instance.setAssignmentMode(FXAbstractBehaviour.Mode.ASSIGN);
    instance.bind(node);
    
    assertEquals(instance.getOnKeyPressed(), node.getOnKeyPressed());
    assertEquals(instance.getOnKeyReleased(), node.getOnKeyReleased());
    assertEquals(instance.getOnKeyTyped(), node.getOnKeyTyped());
  }
  /**
   * Test of bind method, of class FXAbstractBehaviour.
   */
  @Test
  public void testBind_Node_Assign() {
    System.out.println("bind assign");
    Node node = new Button();
    TestBehaviour instance = new TestBehaviour();
    instance.setAssignmentMode(FXAbstractBehaviour.Mode.ASSIGN);
    instance.bind(node);
    
    assertEquals(instance.getOnKeyPressed(), node.getOnKeyPressed());
    assertEquals(instance.getOnKeyReleased(), node.getOnKeyReleased());
    assertEquals(instance.getOnKeyTyped(), node.getOnKeyTyped());
    
    assertEquals(instance.getOnDragDetected(), node.getOnDragDetected());
    assertEquals(instance.getOnDragDone(), node.getOnDragDone());
    assertEquals(instance.getOnDragDropped(), node.getOnDragDropped());
    assertEquals(instance.getOnDragEntered(), node.getOnDragEntered());
    assertEquals(instance.getOnDragExited(), node.getOnDragExited());
    assertEquals(instance.getOnDragOver(), node.getOnDragOver());

    assertEquals(instance.getOnMouseClicked(), node.getOnMouseClicked());
    assertEquals(instance.getOnMouseDragEntered(), node.getOnMouseDragEntered());
    assertEquals(instance.getOnMouseDragExited(), node.getOnMouseDragExited());
    assertEquals(instance.getOnMouseDragOver(), node.getOnMouseDragOver());
    assertEquals(instance.getOnMouseDragReleased(), node.getOnMouseDragReleased());
    assertEquals(instance.getOnMouseDragged(), node.getOnMouseDragged());
    assertEquals(instance.getOnMouseEntered(), node.getOnMouseEntered());
    assertEquals(instance.getOnMouseExited(), node.getOnMouseExited());
    assertEquals(instance.getOnMouseMoved(), node.getOnMouseMoved());
    assertEquals(instance.getOnMousePressed(), node.getOnMousePressed());
    assertEquals(instance.getOnMouseReleased(), node.getOnMouseReleased());
  }

  @Test(expected = NullPointerException.class)
  public void testBind_Node_FXAbstractBehaviourMode_NPE() {
    System.out.println("bind NPE");
    Node node = null;
    FXAbstractBehaviour.Mode assignmentMode = FXAbstractBehaviour.Mode.ASSIGN;
    TestBehaviour instance = new TestBehaviour();
    instance.bind(node, assignmentMode);
    
    assertEquals(instance.getOnKeyPressed(), node.getOnKeyPressed());
    assertEquals(instance.getOnKeyReleased(), node.getOnKeyReleased());
    assertEquals(instance.getOnKeyTyped(), node.getOnKeyTyped());
    
  }
  
  /**
   * Test of bind method, of class FXAbstractBehaviour.
   */
  @Test
  public void testBind_Node_FXAbstractBehaviourMode_ASSIGN() {
    System.out.println("bind Mode_ASSIGN");
    Node node = new Button();
    FXAbstractBehaviour.Mode assignmentMode = FXAbstractBehaviour.Mode.ASSIGN;
    TestBehaviour instance = new TestBehaviour();
    instance.bind(node, assignmentMode);
    
    assertEquals(instance.getOnKeyPressed(), node.getOnKeyPressed());
    assertEquals(instance.getOnKeyReleased(), node.getOnKeyReleased());
    assertEquals(instance.getOnKeyTyped(), node.getOnKeyTyped());
    
    assertEquals(instance.getOnDragDetected(), node.getOnDragDetected());
    assertEquals(instance.getOnDragDone(), node.getOnDragDone());
    assertEquals(instance.getOnDragDropped(), node.getOnDragDropped());
    assertEquals(instance.getOnDragEntered(), node.getOnDragEntered());
    assertEquals(instance.getOnDragExited(), node.getOnDragExited());
    assertEquals(instance.getOnDragOver(), node.getOnDragOver());

    assertEquals(instance.getOnMouseClicked(), node.getOnMouseClicked());
    assertEquals(instance.getOnMouseDragEntered(), node.getOnMouseDragEntered());
    assertEquals(instance.getOnMouseDragExited(), node.getOnMouseDragExited());
    assertEquals(instance.getOnMouseDragOver(), node.getOnMouseDragOver());
    assertEquals(instance.getOnMouseDragReleased(), node.getOnMouseDragReleased());
    assertEquals(instance.getOnMouseDragged(), node.getOnMouseDragged());
    assertEquals(instance.getOnMouseEntered(), node.getOnMouseEntered());
    assertEquals(instance.getOnMouseExited(), node.getOnMouseExited());
    assertEquals(instance.getOnMouseMoved(), node.getOnMouseMoved());
    assertEquals(instance.getOnMousePressed(), node.getOnMousePressed());
    assertEquals(instance.getOnMouseReleased(), node.getOnMouseReleased());
  }

  @Test
  public void testBind_Node_FXAbstractBehaviourMode_ASSIGN_empty() {
    System.out.println("bind Mode_ASSIGN_emtpy");
    Node node = new Button();
    FXAbstractBehaviour.Mode assignmentMode = FXAbstractBehaviour.Mode.ASSIGN;
    TestEmptyBehaviour instance = new TestEmptyBehaviour();
    instance.bind(node, assignmentMode);
    // does nothing? better do test for not-replacing existing handlers.
    assertEquals(null, node.getOnKeyPressed());
    assertEquals(null, node.getOnKeyReleased());
    assertEquals(null, node.getOnKeyTyped());
    
    assertEquals(null, node.getOnDragDetected());
    assertEquals(null, node.getOnDragDone());
    assertEquals(null, node.getOnDragDropped());
    assertEquals(null, node.getOnDragEntered());
    assertEquals(null, node.getOnDragExited());
    assertEquals(null, node.getOnDragOver());

    assertEquals(null, node.getOnMouseClicked());
    assertEquals(null, node.getOnMouseDragEntered());
    assertEquals(null, node.getOnMouseDragExited());
    assertEquals(null, node.getOnMouseDragOver());
    assertEquals(null, node.getOnMouseDragReleased());
    assertEquals(null, node.getOnMouseDragged());
    assertEquals(null, node.getOnMouseEntered());
    assertEquals(null, node.getOnMouseExited());
    assertEquals(null, node.getOnMouseMoved());
    assertEquals(null, node.getOnMousePressed());
    assertEquals(null, node.getOnMouseReleased());
  }
    
  @Test
  public void testBind_Node_FXAbstractBehaviourMode_ADD_keyPress() {
    System.out.println("bind Mode_ADD_keyPress");
    Button node = new Button();
    FXAbstractBehaviour.Mode assignmentMode = FXAbstractBehaviour.Mode.ADD;
    TestBehaviour instance = new TestBehaviour();
    instance.bind(node, assignmentMode);
    
    node.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, null, "", KeyCode.ENTER, true, true, true, true));
    assertTrue(instance.isKeyPressed());
    assertFalse(instance.isKeyReleased());
    assertFalse(instance.isKeyTyped());
  }
  @Test
  public void testBind_Node_FXAbstractBehaviourMode_ADD_keyRelease() {
    System.out.println("bind Mode_ADD_keyRelease");
    Button node = new Button();
    FXAbstractBehaviour.Mode assignmentMode = FXAbstractBehaviour.Mode.ADD;
    TestBehaviour instance = new TestBehaviour();
    instance.bind(node, assignmentMode);
    
    node.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, null, "", KeyCode.ENTER, true, true, true, true));
    assertFalse(instance.isKeyPressed());
    assertTrue(instance.isKeyReleased());
    assertFalse(instance.isKeyTyped());
  }
  @Test
  public void testBind_Node_FXAbstractBehaviourMode_ADD_keyTyped() {
    System.out.println("bind Mode_ADD_keyTyped");
    final Button node = new Button();
    final BooleanProperty handlerFired = new SimpleBooleanProperty(false);
    FXAbstractBehaviour.Mode assignmentMode = FXAbstractBehaviour.Mode.ADD;
    final TestBehaviour instance = new TestBehaviour();
    instance.bind(node, assignmentMode);
    
    node.addEventHandler(KeyEvent.KEY_TYPED, (event) -> {
      handlerFired.set(true);
    });
    
    node.fireEvent(new KeyEvent(KeyEvent.KEY_TYPED, null, "", KeyCode.ENTER, true, true, true, true));
    assertFalse(instance.isKeyPressed());
    assertFalse(instance.isKeyReleased());
    assertTrue(instance.isKeyTyped());
    assertTrue(handlerFired.get());
  }

  @Test
  public void testBind_Node_FXAbstractBehaviourMode_FILTER_keyTyped() {
    System.out.println("bind Mode_FILTER_keyTyped");
    final Button node = new Button();
    final BooleanProperty handlerFired = new SimpleBooleanProperty(false);
    FXAbstractBehaviour.Mode assignmentMode = FXAbstractBehaviour.Mode.FILTER;
    final TestBehaviour instance = new TestBehaviour();
    instance.bind(node, assignmentMode);
    
    node.addEventHandler(KeyEvent.KEY_TYPED, (event) -> {
      handlerFired.set(true);
    });
    
    node.fireEvent(new KeyEvent(KeyEvent.KEY_TYPED, null, "", KeyCode.ENTER, true, true, true, true));
    assertFalse(instance.isKeyPressed());
    assertFalse(instance.isKeyReleased());
    assertTrue(instance.isKeyTyped());
    assertFalse(handlerFired.get());
  }

  @Test
  public void testBind_Node_FXAbstractBehaviourMode_FILTER_Empty() {
    System.out.println("bind Mode__FILTER_Empty");
    final Button node = new Button();
    final BooleanProperty handlerFired = new SimpleBooleanProperty(false);
    FXAbstractBehaviour.Mode assignmentMode = FXAbstractBehaviour.Mode.FILTER;
    final TestEmptyBehaviour instance = new TestEmptyBehaviour();
    instance.bind(node, assignmentMode);
    
    node.addEventHandler(KeyEvent.KEY_TYPED, (event) -> {
      handlerFired.set(true);
    });
    
    node.fireEvent(new KeyEvent(KeyEvent.KEY_TYPED, null, "", KeyCode.ENTER, true, true, true, true));
    assertTrue(handlerFired.get());
  }

  
}
