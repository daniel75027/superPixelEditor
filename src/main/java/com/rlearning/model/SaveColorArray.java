package model;

import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 * TODO: complete me
 */
public class SaveColorArray {
  
  private Color[] colorArray;
  private String fileDir;
  private BufferedWriter bufferedWriter;
  private FileWriter fileWriter;
  private String fileContent;
  
  // Gets the color array and directory and set it to the FileWriter and BufferWriter.
  // Added exception errors.
  // Call this method first to save the color array to file.
  // TODO: Why is this method not a constructor?
  public void saveArray(Color[] color, String dir) {
    // Set file directory
    this.fileDir = dir;
    
    // Set color array
    for (int i = 0; i < color.length; i++) {
      this.colorArray[i] = color[i];
    }
    
    // Setup content to be put in the file. Check 'setupFileContent' method for more details.
    setupFileContent();
    
    try {
      // Open FileWriter and BufferWriter streams
      this.fileWriter = new FileWriter(this.fileDir);
      this.bufferedWriter = new BufferedWriter(this.fileWriter);
      this.bufferedWriter.write(this.fileContent);
    } catch (IOException e) {
      // If this happens, I'm moving to C#
      e.printStackTrace();
    } finally {
      try {
        // If the BufferWriter or the FileWriter streams are still open, close them
        if (this.bufferedWriter != null) {
          bufferedWriter.close();
        }
        if (this.fileWriter != null) {
          fileWriter.close();
        }
      } catch (IOException ex) {
        // Uhhh, I don't know why they are closed. Something happened.
        ex.printStackTrace();
      }
    }
  }
  
  private void setupFileContent() {
    for (int i = 0; i < this.colorArray.length; i++) {
      // Format of each line in the file:
      // R-value, G-value, B-value, A-value
      // Each line would represent each pixel "Color"
      this.fileContent = this.fileContent + this.colorArray[i].getRed() + ", " + this.colorArray[i].getGreen() +
              ", " + this.colorArray[i].getBlue() + ", " + this.colorArray[i].getOpacity() + "\n";
    }
  }
  
  // Getters and Setters
  public Color[] getColorArray() {
    return colorArray;
  }
  
  public void setColorArray(Color[] colorArray) {
    this.colorArray = colorArray;
  }
  
  public String getFileDir() {
    return fileDir;
  }
  
  public void setFileDir(String fileDir) {
    this.fileDir = fileDir;
  }
  
  public BufferedWriter getBufferedWriter() {
    return bufferedWriter;
  }
  
  public void setBufferedWriter(BufferedWriter bufferedWriter) {
    this.bufferedWriter = bufferedWriter;
  }
  
  public FileWriter getFileWriter() {
    return fileWriter;
  }
  
  public void setFileWriter(FileWriter fileWriter) {
    this.fileWriter = fileWriter;
  }
  
  public String getFileContent() {
    return fileContent;
  }
  
  public void setFileContent(String fileContent) {
    this.fileContent = fileContent;
  }
}
