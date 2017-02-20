package controller;

import java.io.BufferedWriter;
import java.io.File;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ColorPaletteController {
  private static final String CONFIG_FILE = "resources/config";
  private static String recentSaveFileDir = null;
  private static BufferedWriter bw = null;
  private static FileWriter fw = null;

  public static void saveColorPalette(ColorPicker colorPicker) {
    FileChooser fileChooser = new FileChooser();
    Stage saveFileStage = new Stage();
    if (recentSaveFileDir == null) {
      recentSaveFileDir = getRecentSavedDir();
    }

    List<Color> customColors = colorPicker.getCustomColors();

    PrintWriter pw = null;
    try {
//    pw = new PrintWriter(new File("resources/custom_palette"));
      fileChooser.setInitialDirectory(new File(recentSaveFileDir));
      File fileToSave = fileChooser.showSaveDialog(saveFileStage);
      if (fileToSave != null) {
        pw = new PrintWriter(fileToSave);
        recentSaveFileDir = fileToSave.getParent();
        saveToConfig();
        for (Color color : customColors)
          pw.println(String.valueOf(color.getRed()) + " " +
              String.valueOf(color.getGreen()) + " " +
              String.valueOf(color.getBlue()) + " " +
              String.valueOf(color.getOpacity()));

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (pw != null) {
        pw.close();
      }
    }
  }

  public static void loadColorPalette(ColorPicker colorPicker) {
    FileChooser fileChooser = new FileChooser();
    Stage openFileStage = new Stage();
    if (recentSaveFileDir == null) {
      recentSaveFileDir = getRecentSavedDir();
    }

    List<Color> customColors = colorPicker.getCustomColors();

    //Remove Colors from old palette
    customColors.clear();

    Scanner scanner = null;
    try {
//      scanner = new Scanner(new File("resources/custom_palette"));
      fileChooser.setInitialDirectory(new File(recentSaveFileDir));
      File fileToLoad = fileChooser.showOpenDialog(openFileStage);
      if (fileToLoad != null) {
        scanner = new Scanner(fileToLoad);
        while (scanner.hasNextLine()) {
          String[] colorParamsStr = scanner.nextLine().split(" ");
          double[] colorParams = Arrays.stream(colorParamsStr)
              .mapToDouble(Double::parseDouble)
              .toArray();
          if (colorParams.length == 4 && (1 - colorParams[0] >= 0) &&
              (1 - colorParams[2] >= 0) && (1 - colorParams[2] >= 0) && (1 - colorParams[3] >= 0)) {
            colorPicker.getCustomColors().add(new Color(colorParams[0], colorParams[1], colorParams[2], colorParams[3]));
          }
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }

  public static void saveToConfig() {
    try {
      fw = new FileWriter(CONFIG_FILE);
      bw = new BufferedWriter(fw);
      bw.write(recentSaveFileDir);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bw != null) {
          bw.close();
        }
        if (fw != null) {
          fw.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  // Setters
  public static void setRecentSavedDir(String sd) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new File("recources/config"));
      pw.write(sd);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (pw != null) {
        pw.close();
      }
    }
  }

  // Getters
  public static String getRecentSavedDir() {
    Scanner sc = null;
    try {
      sc = new Scanner(new File("resources/config"));
      if (sc.hasNextLine()) {
        return sc.nextLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (sc != null) {
        sc.close();
      }
    }

    return recentSaveFileDir;
  }
}
