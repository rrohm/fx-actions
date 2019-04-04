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
package com.aeonium.javafx.actions.progress;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class SimpleProgressTextAdapterTest {

  public SimpleProgressTextAdapterTest() {
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
   * Test of getProgressText method, of class SimpleProgressTextAdapter.
   */
  @Test
  public void testGetProgressText() {
    System.out.println("getProgressText");
    final String expResult = "TEST";
    SimpleProgressTextAdapter instance = new SimpleProgressTextAdapter();
    instance.progressTextProperty().set(expResult);
    String result = instance.getProgressText();
    assertEquals(expResult, result);
  }

  /**
   * Test of setProgressText method, of class SimpleProgressTextAdapter.
   */
  @Test
  public void testSetProgressText() {
    System.out.println("setProgressText");
    String value = "TEST";
    SimpleProgressTextAdapter instance = new SimpleProgressTextAdapter();
    instance.setProgressText(value);
    String result = instance.getProgressText();
    assertEquals(value, result);
  }

  /**
   * Test of onChanged method, of class SimpleProgressTextAdapter.
   */
  @Test
  public void testOnChanged() {
    System.out.println("onChanged");
    ObservableList<Task> list = FXCollections.observableArrayList();
    list.add(new Task() {
      @Override
      protected Object call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    });
    ListChangeListener.Change<? extends Task> change = new ChangeImpl(list);
    SimpleProgressTextAdapter instance = new SimpleProgressTextAdapter();
    instance.onChanged(change);
  }

  private class ChangeImpl extends ListChangeListener.Change<Task> {

    public ChangeImpl(ObservableList<Task> list) {
      super(list);
    }
    private int count = 0;

    @Override
    public boolean next() {
      return count++ == 0;
    }

    @Override
    public void reset() {
    }

    @Override
    public int getFrom() {
      return 0;
    }

    @Override
    public int getTo() {
      return 1;
    }

    @Override
    public List<Task> getRemoved() {
      final ArrayList<Task> list = new ArrayList<>();
      list.add(new Task() {
        @Override
        protected Object call() throws Exception {
          throw new UnsupportedOperationException("Not supported yet.");
        }
      });
      return list;
    }

    @Override
    protected int[] getPermutation() {
      return new int[0];
    }
  }

}
