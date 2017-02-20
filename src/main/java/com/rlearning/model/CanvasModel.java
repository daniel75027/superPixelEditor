// TODO - Rename the package to suit with the whole Java skeleton thingy on github.
package model;
import controller.SuperPixelEditorController;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CanvasModel {
  private static Color viewPixelColor = SuperPixelEditorController.currentViewColorSelected();

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
