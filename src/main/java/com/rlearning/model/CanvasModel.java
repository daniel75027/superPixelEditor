package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javax.swing.filechooser.FileSystemView;

import controller.SuperPixelEditorController;


/**
 * TODO: complete me
 */
public class CanvasModel implements Serializable {
  //gets RGB color value from controller which is gotten from view
  //private Color currentViewPixelColor = SuperPixelEditorController.currentViewColorSelected();

  // using Java naming convention for final objects.
  // Gets Systems home directory path independent of platform/OS.
  private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
  private final File homeDirectory = fileSystemView.getHomeDirectory();
  private final File superPixelEditorDirectory = new File(homeDirectory + File.separator + "Super Pixel Editor");
  private final File canvasModelDirectory = new File(superPixelEditorDirectory + File.separator + "Canvas Model");


  //Method to reset canvas
  public void resetCanvas(){
    this.canvasObject = new Canvas(600, 400); // TODO: replace values with variables for default canvas size or make a popup asking for canvas size
  }

  //Method to reset the graphics context
  public void resetGraphicsContext() {
    this.canvasGraphics = canvasObject.getGraphicsContext2D();
  }

  // Method to reset the Canvas Model
  // Wasn't sure whether to reset all the variables so I played it safe and just reset the canvas and the graphics context
  // I have a feeling that the Image and Image viewer would need to be reset but wasn't sure what to do with it as it isn't inpmemented yet
  public void resetCanvasModel(){
    resetCanvas();
    resetGraphicsContext();
  }

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
  // TODO: Reduce duplicated code amongst these 2
  public void createCanvas(int windowWidth, int windowHeight, int canvasWidth, int canvasHeight, Color backgroundColor, Image image) {
    this.canvasGroup = new Group();
    this.canvasScene = new Scene(canvasGroup, windowWidth, windowHeight, backgroundColor);
    this.canvasObject = new Canvas(canvasWidth, canvasHeight);
    assignImage(image);
  }

  public void createCanvas(int windowWidth, int windowHeight, int canvasWidth, int canvasHeight, Image image) {
      this.canvasGroup = new Group();
      this.canvasScene = new Scene(canvasGroup, windowWidth, windowHeight, Color.WHITE);
      this.canvasObject = new Canvas(canvasWidth, canvasHeight);
      assignImage(image);
  }

  // Checking if image was sent through the createCanvas method.
  private void assignImage(Image image) {
    if (image != null) {
      this.canvasImage = image;
    } else {
      // TODO: Consider throwing an exception to be handled higher up
      // throw new InputMismatchException("An image must be provided to the canvas.");
    }
  }

 private void createSuperPixelEditorDirectory(){
   if (!superPixelEditorDirectory.exists()) {
     superPixelEditorDirectory.mkdir();
   }
 }

  private void createCanvasModelDirectory(){
    if (!canvasModelDirectory.exists()) {
      canvasModelDirectory.mkdir();
    }
  }

  // Serializes CanvasModel data. Needs a file name including extension. Example: "Canvas_model.txt"
  // Done this way for testing purposes in case someone needs to save the model in various states with different names.
  // uses CanvasModel.class for testing purposes. You can call this from any class and it serializes the Canvas Model.
  // Alternatively change "CanvasModel.class" to "this" to serialize class it is being called from. Said class must implement Serializable.
  public void serializeCanvasModel(String fileName) {
    createSuperPixelEditorDirectory();
    createCanvasModelDirectory();
    File modelFile = new File(canvasModelDirectory + File.separator + fileName);
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
