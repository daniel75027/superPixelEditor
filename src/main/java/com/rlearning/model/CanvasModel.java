package model;

import controller.SuperPixelEditorController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CanvasModel implements Serializable {
  //gets RGB color value from controller which is gotten from view
  private Color currentViewPixelColor = SuperPixelEditorController.currentViewColorSelected();

  // using Java naming convention for final objects.
  // Gets Systems home directory path independent of platform/OS.
  private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
  private final File HOME_DIRECTORY = fileSystemView.getHomeDirectory();
  private final File SUPER_PIXEL_EDITOR_DIRECTORY = new File(HOME_DIRECTORY + File.separator + "Super Pixel Editor");
  private final File CANVAS_MODEL_DIRECTORY = new File(SUPER_PIXEL_EDITOR_DIRECTORY + File.separator + "Canvas Model");

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

 private void createSuperPixelEditorDirectory(){
   if (!SUPER_PIXEL_EDITOR_DIRECTORY.exists()) SUPER_PIXEL_EDITOR_DIRECTORY.mkdir();
 }

  private void createCanvasModelDirectory(){
      if (!CANVAS_MODEL_DIRECTORY.exists()) CANVAS_MODEL_DIRECTORY.mkdir();
  }

  // Serializes CanvasModel data. Needs a file name including extension. Example: "Canvas_model.txt"
  // Done this way for testing purposes in case someone needs to save the model in various states with different names.
  // uses CanvasModel.class for testing purposes. You can call this from any class and it serializes the Canvas Model.
  // Alternatively change "CanvasModel.class" to "this" to serialize class it is being called from. Said class must implement Serializable.
  public void serializeCanvasModel(String fileName) {
    createSuperPixelEditorDirectory();
    createCanvasModelDirectory();
    File modelFile = new File(CANVAS_MODEL_DIRECTORY + File.separator + fileName);
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(modelFile);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(CanvasModel.class);
      objectOutputStream.close();
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
