/*
 * Copyright (C) 2015 Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;.
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

package com.aeonium.javafx.actions.util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;

/**
 * The ProgressCollector listens on a list of tasks and provides a collective
 * progress message and a progress property covering the state of the whole
 * tasks list.
 * Currently this class is rather experimental.
 * Behaviour is:
 * <ol>
 *   <li>If the list has 0 tasks: message is "OK.", and the progress is unbound.</li>
 *   <li>If the list has 1 task: message is bound to the one task's message
 *      property, and the progress is bound to the one task's progress
 *      property</li>
 *   <li>If the list has more than 1 tasks: message shows the number of tasks,
 *      and the progress is set to infinite.</li>
 * </ol>
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class ProgressCollector implements ListChangeListener<Task> {

  private final DoubleProperty progress = new SimpleDoubleProperty();

  private final StringProperty message = new SimpleStringProperty();

  public ProgressCollector() {
//    this.progress.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//    });
  }



  @Override
  public void onChanged(Change<? extends Task> c) {

    while (c.next()) {
//      System.out.println("ProgressCollector: " + c.getList().size());

      switch (c.getList().size()) {
        case 0:
          progress.unbind();
          message.unbind();
          message.set("OK.");
          break;
        case 1:
          progress.unbind();
          message.unbind();
          Task task = c.getList().get(0);
          progress.bind(task.progressProperty());
          message.bind(task.messageProperty());
          break;
        default:
          progress.unbind();
          message.unbind();

          progress.set(-1);
          message.set(c.getList().size()  + " Tasks ...");
          break;
      }
    }
  }

  public double getProgress() {
    return progress.get();
  }

  public DoubleProperty progressProperty() {
    return progress;
  }

  public String getMessage() {
    return message.get();
  }

  public StringProperty messageProperty() {
    return message;
  }

}
