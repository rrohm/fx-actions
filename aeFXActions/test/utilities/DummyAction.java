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
package utilities;

import com.aeonium.javafx.actions.FXAbstractAction;
import com.aeonium.javafx.actions.annotations.FXAction;
import java.lang.annotation.Annotation;

/**
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class DummyAction extends FXAbstractAction {

  public static FXAction createDummyAction(boolean doMnemonic, boolean doShowText, boolean doShowGraphic, boolean doAsync, String imageURL) {
    return new FXAction() {
      @Override
      public Class<? extends FXAbstractAction> action() {
        return DummyAction.class;
      }

      @Override
      public boolean doMnemonicParsing() {
        return doMnemonic;
      }

      @Override
      public boolean doShowText() {
        return doShowText;
      }

      @Override
      public boolean doShowGraphic() {
        return doShowGraphic;
      }

      @Override
      public boolean doAsync() {
        return doAsync;
      }

      @Override
      public String imageURL() {
        return imageURL;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return FXAction.class;
      }
    };
  }
  
  public DummyAction() {
    // no op.
  }
  
}
