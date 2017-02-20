package model;

import controller.SuperPixelEditorController;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.*;
import java.nio.file.*;
import java.nio.file.FileSystem;

public class CanvasModel implements Serializable {
  //gets RGB color value from controller which is gotten from view
  private Color currentViewPixelColor = SuperPixelEditorController.currentViewColorSelected();

  // Group - container to hold the whole canvas.
  private Group canvasGroup;
  private Scene canvasScene;
  private Canvas canvasObject;
  private GraphicsContext canvasGraphics;

  // Not sure if we're using these variables yet...
  private Image canvasImage;
  private ImageView canvasImageView;

  // Simple method to setup the whole canvas. (Without graphics editing at the moment despite having GraphicsContext variable.)
  // Call this method first to create canvas.
  public void CreateCanvas(int WindowWidth, int WindowHeight, int CanvasWidth, int CanvasHeight, Color backgroundColor, Image image) {
    this.canvasGroup = new Group();
    this.canvasScene = new Scene(canvasGroup, WindowWidth, WindowHeight, backgroundColor);
    this.canvasObject = new Canvas(CanvasWidth, CanvasHeight);
    assignImage(image);
  }

  // Checking if image was sent through the CreateCanvas method.
  private void assignImage(Image image) {
    if (image != null) {
      this.canvasImage = image;
    } else {
      // Consider throwing an exception to be handled higher up
      // throw new InputMismatchException("An image must be provided to the canvas.");
    }
  }

  // Serializes model data. Needs a file name including extension. Example: "Canvas_model.txt"
  // Done this way for testing purposes in case someone else needs to save the model in various states with different names.
  public static void serializeModel(String fileName) {
    FileSystem fileSystemDefaultDirectory = FileSystems.getDefault();
    String fileDestination = fileSystemDefaultDirectory + fileName;
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(fileDestination);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  // reworked getters and setters for standard naming convention in Java

  public Group getCanvasGroup() {
    return canvasGroup;
  }

  public void setCanvasGroup(Group canvasGroup) {
    this.canvasGroup = canvasGroup;
  }

  public Scene getCanvasScene() {
    return canvasScene;
  }

  public void setCanvasScene(Scene canvasScene) {
    this.canvasScene = canvasScene;
  }

  public Canvas getCanvasObject() {
    return canvasObject;
  }

  public void setCanvasObject(Canvas canvasObject) {
    this.canvasObject = canvasObject;
  }

  public GraphicsContext getCanvasGraphics() {
    return canvasGraphics;
  }

  public void setCanvasGraphics(GraphicsContext canvasGraphics) {
    this.canvasGraphics = canvasGraphics;
  }

  public Image getCanvasImage() {
    return canvasImage;
  }

  public void setCanvasImage(Image canvasImage) {
    this.canvasImage = canvasImage;
  }

  public ImageView getCanvasImageView() {
    return canvasImageView;
  }

  public void setCanvasImageView(ImageView canvasImageView) {
    this.canvasImageView = canvasImageView;
  }
}
