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

import javafx.event.ActionEvent;
import org.aeonium.fxunit.FXUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Test cases for {@link DefaultActionHandler}.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultActionHandlerTest {

  @Mock
  private FXActionManager actionManager;

  @BeforeClass
  public static void setUpClass() {
    FXUnit.init();
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    this.actionManager = new FXActionManager();
  }

  @After
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

    assertTrue("Event is consumed", event.isConsumed());
  }

}
