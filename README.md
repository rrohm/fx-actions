fx-actions
==========

A small but versatile framework for speeding up development of JavaFX and FXML
applications: use a declarative syntax for binding actions to the UI controls,
for a clean separation of logic and presentation and more fun with JavaFX.


About the project
=================

Goal of the fx-actions project is to provide Java developers with a easy-to-use
framework for implementing application logic in action classes and declaratively
wiring them to an FXML UI, i.e., you create the UI with FXML, deklare the UI
controls in the controller class and simply annotate them with your action
class. No more writing lots of lines for creating event handlers!
The project is open source, the code is made available under LGPL 2.1.

How it works
===============

At first, consider some standard FXML UI and it's controller. There would be an fx:id in 
the FXML, containing MenuItem and Button instance that we want to wire the action logic to:

```xml
<AnchorPane ... fx:controller="my.app.FXMLDocumentController">
  <children>
    <MenuBar>
      <menus>
        <Menu>
          <items>
            <MenuItem fx:id="miAction" />
          </items>
        </Menu>
      </menus>
    </MenuBar>
    <Button fx:id="button" />
    ...
```
Note: you do not need to declare any handler methods in the FXML! Action logic should be 
encapsulated, and not tied to a controller class. Also, action logic is a part of the 
controller tie, not a part of the UI. So, the controller would need only fields for 
referencing the button and the menu item. 

Next, you put your logic in action classes. Actions should be executed asynchronously, 
so we would need take care of multithreading, e.g., with JavaFX tasks or services. With
fx-actions, you do not need to handle this yourself, all you need to do is to extend the 
base class FXAbstractAction:

```java
public class MyAction extends FXAbstractAction{

  public MyAction() {
    this.setText("Go!");
  }

  @Override
  public void onAction(Event event){
    System.out.println("... and here goes the logic!");
  }
}
```

After describing the UI with FXML and implementing the action, let's put it together.
The wiring is done declaratively, only with annotations in the controller. You simple
annotate any button or menu item (also labels!) that should trigger the action with 
@FXAction and the action class: 

```java
public class FXMLDocumentController implements Initializable {

    @FXML
    @FXAction(action = MyAction.class)
    private Button button;

    @FXML
    @FXAction(action = MyAction.class)
    private MenuItem miAction;
    
    // ...
}
```

OK, at last there needs to be shed some light on the necessary initialization. 
The controllers need to be scanned for the annotations. The fx-actions framework currently
is initialized with two additional lines of code and a small modification in the FXMLLoader 
invocation. An FXActionManager needs to be instantiated and used as a controller factory
when loading the FXML UI. After loading the UI, the action manager initializes the actions.

```java

    myActionControllerFactory = new FXActionManager();
    
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("FXMLDocument.fxml"));
    fxmlLoader.setControllerFactory(myActionControllerFactory);
    Parent root = (Parent) fxmlLoader.load();
    
    myActionControllerFactory.initActions();
```
That's it! Soon there will be more information in the wiki pages, e.g. on
* Setting action properties like caption, tooltips and icons
* enabling and disabling actions with bindings
* binding actions to keyboard shortcuts
* ...


Project History
===============

2015-05-04   New project description online, minor updates in code and javadocs.

2014-09-16   License decision made, it is  LGPL 2.1.

2014-07-14   The repository has been just created. Code follows!
