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
package com.aeonium.javafx.actions;

import utilities.TestAction;
import javafx.event.ActionEvent;
import org.aeonium.fxunit.FXUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test cases for {@link DefaultActionHandler}.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
@ExtendWith(MockitoExtension.class)
public class DefaultActionHandlerTest {

  @Mock
  private FXActionManager actionManager;

  @BeforeAll
  public static void setUpClass() {
    FXUnit.init();
  }

  @AfterAll
  public static void tearDownClass() {
  }

  @BeforeEach
  public void setUp() {
    this.actionManager = new FXActionManager();
  }

  @AfterEach
  public void tearDown() {
  }

  /**
   * Test of handle method, of class DefaultActionHandler.
   */
  @Test
  public void testHandle_default() {
    System.out.println("handle_default");
    TestAction testAction = new TestAction();
    TestAction spyedAction = Mockito.spy(testAction);
    spyedAction.setManager(actionManager);
    ActionEvent event = new ActionEvent();
    DefaultActionHandler instance = new DefaultActionHandler(spyedAction);
    instance.handle(event);

    verify(spyedAction).start();
  }

  @Test
  public void testHandle_doExecAsyncFalse() {
    System.out.println("handle_doExecAsyncFalse");
    TestAction testAction = new TestAction();
    testAction.setDoExecuteAsync(false);

    TestAction spyedAction = Mockito.spy(testAction);
    spyedAction.setManager(actionManager);
    ActionEvent event = new ActionEvent();
    DefaultActionHandler instance = new DefaultActionHandler(spyedAction);
    instance.handle(event);

    verify(spyedAction).onAction(event);
    verify(spyedAction, never()).start();
  }

  @Test
  public void testHandle_exitOnDisabled() {
    System.out.println("handle_doExecAsyncFalse");
    TestAction testAction = new TestAction();
    testAction.setDoExecuteAsync(false);
    testAction.setDisabled(true);

    TestAction spyedAction = Mockito.spy(testAction);
    spyedAction.setManager(actionManager);
    ActionEvent event = new ActionEvent();
    DefaultActionHandler instance = new DefaultActionHandler(spyedAction);
    instance.handle(event);

    verify(spyedAction, never()).onAction(event);
    verify(spyedAction, never()).start();
  }

  @Test
  public void testHandle_syncOnly() {
    System.out.println("handle_doExecAsyncFalse");
    TestAction testAction = new TestAction();
    TestAction spyedAction = Mockito.spy(testAction);
    spyedAction.setManager(actionManager);
    ActionEvent event = new ActionEvent();
    DefaultActionHandler instance = new DefaultActionHandler(spyedAction, true);
    instance.handle(event);

    verify(spyedAction).onAction(event);
  }

  @Test
  public void testHandle_consumes() {
    System.out.println("handle_consumes");
    TestAction testAction = new TestAction();
    testAction.setManager(actionManager);
    ActionEvent event = new ActionEvent();
    DefaultActionHandler instance = new DefaultActionHandler(testAction);

    instance.handle(event);

    assertTrue(event.isConsumed(), "Event is consumed");
  }

}
