/*
 * Copyright (C) 2014 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;

/**
 * This class listens on a list of Task objects and produces a text message
 * roughly describing the state of the task list.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 * @deprecated Experimental - do not use.
 */
public class SimpleProgressTextAdapter implements ListChangeListener<Task> {

  private final StringProperty progressText = new SimpleStringProperty();

  public String getProgressText() {
    return progressText.get();
  }

  public void setProgressText(String value) {
    progressText.set(value);
  }

  public StringProperty progressTextProperty() {
    return progressText;
  }

  @Override
  public void onChanged(ListChangeListener.Change<? extends Task> change) {
    while (change.next()) {
      if (change.wasAdded()) {
        for (Task t : change.getAddedSubList()) {

          System.out.println(t.getTitle() + " ...");
          progressText.set(t.getTitle() + " ...");
        }
      }
      if (change.wasRemoved()) {
        System.out.println("Tasks removed: " + change.getRemovedSize());
        for (Task t : change.getRemoved()) {
          System.out.println(t.getTitle() + " OK.");
          progressText.set(t.getTitle() + " OK.");
        }
      }
    }
  }
}
