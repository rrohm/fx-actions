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
package com.aeonium.javafx.actions.progress;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
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
 * Test cases for {@link ProgressCollector).
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class ProgressCollectorTest {

  public ProgressCollectorTest() {
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
   * Test of onChanged method, of class ProgressCollector.
   */
  @Test
  public void testOnChanged_emptyList() {
    System.out.println("onChanged_emptyList");
    ObservableList<Task> list = FXCollections.observableArrayList();
    ListChangeListener.Change<? extends Task> c = new ChangeImpl(list);
    
    ProgressCollector instance = new ProgressCollector();
    instance.onChanged(c);
    assertEquals("OK.", instance.getMessage());
    assertEquals(0, instance.getProgress(), 0.001);
  }

  @Test
  public void testOnChanged_1() {
    System.out.println("onChanged_1");
    ObservableList<Task> list = FXCollections.observableArrayList();
    ListChangeListener.Change<? extends Task> c = new ChangeImpl(list);
    list.add(new Task() {
      @Override
      protected Object call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    });
    ProgressCollector instance = new ProgressCollector();
    instance.onChanged(c);
    assertEquals("", instance.getMessage());
    assertEquals(-1.0d, instance.getProgress(), 0.001);
  }

  @Test
  public void testOnChanged_2() {
    System.out.println("onChanged_2");
    ObservableList<Task> list = FXCollections.observableArrayList();
    ListChangeListener.Change<? extends Task> c = new ChangeImpl(list);
    list.add(new Task() {
      @Override
      protected Object call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
      }
    });
    list.add(new Task() {
      @Override
      protected Object call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
      }
    });
    ProgressCollector instance = new ProgressCollector();
    instance.onChanged(c);
    assertEquals("2 Tasks ...", instance.getMessage());
    assertEquals(-1.0d, instance.getProgress(), 0.001);
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
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTo() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Task> getRemoved() {
      return new ArrayList<>();
    }

    @Override
    protected int[] getPermutation() {
      return new int[0];
    }
  }


}
