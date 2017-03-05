/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.rlearning.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import main.java.com.rlearning.controller.CanvasResizeController;
import main.java.com.rlearning.controller.ColorPaletteController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * @author Andrew
 */
public class SuperPixelEditorView extends Application {
  private static Color pixelColor;

  public static Color getPixelColor() {
    return pixelColor;
  }

  public static void setPixelColor(Color pixelColor) {
    SuperPixelEditorView.pixelColor = pixelColor;
  }


  @Override
  public void start(Stage primaryStage) {


    //new window opens after btnNewWindow is clicked
    Stage newWindowStage = new Stage();
    StackPane newWindowLayout = new StackPane();
    Scene newWindowScene = new Scene(newWindowLayout, 620, 480);
    Button btnNewWindow = new Button("Open a window with this color");
    Button btnColorReset = new Button("Reset Window Color");
    Button btn = new Button("Color Picker");
    double sceneWidth = 400;
    double sceneHeight = 400;

    newWindowStage.setScene(newWindowScene);
    newWindowStage.setTitle("");
   //Create menu bar
        MenuBar menuBar = new MenuBar();
        //Creates menu separator
        SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem3 = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem4 = new SeparatorMenuItem();
        //Creates menus
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuHelp = new Menu("Help");
        //File menu items
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem saveColorItem = new MenuItem("Save Color Palette");
        MenuItem loadColorItem = new MenuItem("Load Color Palette");
        MenuItem optionsItem = new MenuItem("Options...");
        MenuItem exitItem = new MenuItem("Exit");
        //Edit menu items
        MenuItem undoItem = new MenuItem("Undo");
        MenuItem redoItem = new MenuItem("Redo");
        //Help menu items
        MenuItem controlsItem = new MenuItem("Controls");
        MenuItem aboutItem = new MenuItem("About");
        //Adds menu items to the menus
        menuFile.getItems().addAll(newItem,openItem,separatorMenuItem1,saveItem,saveAsItem,separatorMenuItem2,saveColorItem,loadColorItem,separatorMenuItem3,optionsItem,separatorMenuItem4,exitItem);
        menuEdit.getItems().addAll(undoItem,redoItem);
        menuHelp.getItems().addAll(controlsItem,aboutItem);
        //Adds all the menus to the menu bar
        menuBar.getMenus().addAll(menuFile,menuEdit,menuHelp);

    Stage colorPickerStage = new Stage();
    VBox colorPickerLayout = new VBox();
    Scene colorPickerScene = new Scene(colorPickerLayout, 300, 150);
    ColorPicker colorPicker = new ColorPicker();

    colorPickerLayout.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
    colorPickerLayout.setSpacing(10.0);
    colorPickerLayout.alignmentProperty().setValue(Pos.CENTER);
    colorPickerLayout.getChildren().addAll(colorPicker, btnNewWindow, btnColorReset);

    colorPickerStage.setTitle("Color Picker");
    colorPickerStage.setScene(colorPickerScene);

    //new canvas window
    Stage canvasCreateStage = new Stage();
    canvasCreateStage.setTitle("Canvas size");
    GridPane canvasCreateLayout = new GridPane();
    TextField textFieldWidth = new TextField();
    TextField textFieldHeight = new TextField();
    textFieldWidth.setPromptText("Width");
    textFieldHeight.setPromptText("Height");
    Button createButton = new Button("Create");

    textFieldWidth.setMaxWidth(50);
    textFieldHeight.setMaxWidth(50);

    canvasCreateLayout.add(textFieldWidth,0,0);
    canvasCreateLayout.add(textFieldHeight,0,1);

    canvasCreateLayout.add(createButton,0,3);
    Scene canvasCreateScene = new Scene(canvasCreateLayout,200,100);
    canvasCreateStage.setScene(canvasCreateScene);

    //Setting up the canvas with a default of 0 height and width
    Canvas canvas = new Canvas(0,0);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    //Popup window
    HBox hbButtons = new HBox();
    Stage popupStage = new Stage();
    popupStage.setTitle("Popup Dialog");
    StackPane popupLayout = new StackPane();
    Scene popupScene = new Scene(popupLayout, 300, 50);
    Text popupText = new Text("A window is already open.");

    popupLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
    popupStage.setScene(popupScene);
    popupLayout.getChildren().addAll(popupText);

    //handlers - may be better suited in control
    colorPicker.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        newWindowLayout.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
        setPixelColor(colorPicker.getValue());
      }
    });

    btnColorReset.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        newWindowLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        colorPicker.setValue(Color.WHITE);
        setPixelColor(Color.WHITE);
      }
    });

    btnNewWindow.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (!newWindowStage.isShowing()) {
          newWindowStage.show();
          newWindowLayout.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), null, null)));
        } else popupStage.show();
      }
    });

    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        colorPickerStage.show();
      }
    });

    Button saveBtn = new Button();
    saveBtn.setText("Save Custom Colors");
    saveBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        ColorPaletteController.saveColorPalette(colorPicker);
      }

    });

    Button loadBtn = new Button();
    loadBtn.setText("Load Custom Colors");
    loadBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        ColorPaletteController.loadColorPalette(colorPicker);
      }

    });

    // Menu Item Functions

    saveColorItem.setOnAction(new EventHandler<ActionEvent>(){
    	@Override
    	public void handle(ActionEvent event){
    		ColorPaletteController.saveColorPalette(colorPicker);
    	}
    });

    loadColorItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        ColorPaletteController.loadColorPalette(colorPicker);
      }

    });

    exitItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.exit(0);
      }

    });

    newItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        canvasCreateStage.show();
        createButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            double canvasWidth = Double.parseDouble(textFieldWidth.getText());
            double canvasHeight = Double.parseDouble(textFieldHeight.getText());

            BorderPane canvasPane = new BorderPane();

            canvas.setHeight(canvasHeight);
            canvas.setWidth(canvasWidth);

            gc.setFill(Color.WHITE);
            gc.fillRect(0, 52, canvasWidth,canvasHeight);

            canvasPane.getChildren().add(canvas);
            canvasPane.setTop(menuBar);
            canvasPane.setCenter(hbButtons);
            Scene canvasScene = new Scene(canvasPane,canvasWidth,canvasHeight);
            primaryStage.setScene(canvasScene);
            primaryStage.show();
            canvasCreateStage.close();
          }
        });

      }
    });



    //Primary layout
    BorderPane root = new BorderPane();

    root.setTop(menuBar);
    root.setCenter(hbButtons);

    hbButtons.getChildren().add(btn);
    hbButtons.getChildren().add(saveBtn);
    hbButtons.getChildren().add(loadBtn);

    Scene scene = new Scene(root, 400, 250);

    primaryStage.setTitle("Super Pixel Art Editor");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
