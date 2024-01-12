package util;

import model.ImageImpl;
import model.Image;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Utility class for reading and writing image files in various formats.
 */
public class ImgUtil {
  private static int height;
  private static int width;
  private static int maxValue;
  private static int[][][] image;
  private Image newImage;

  /**
   * Converts a 3-dimensional array of RGB values to a BufferedImage.
   *
   * @param rgb the 3-dimensional array of RGB values to be converted
   * @return a BufferedImage object representing the RGB values in the input array
   * @throws IOException if there is an error creating the BufferedImage object
   */
  public static BufferedImage getBufferedImage(int[][][] rgb) throws IOException {
    BufferedImage output = new BufferedImage(rgb[0].length, rgb.length, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < rgb.length; i++) {
      for (int j = 0; j < rgb[0].length; j++) {
        int r = rgb[i][j][0];
        int g = rgb[i][j][1];
        int b = rgb[i][j][2];
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    return output;
  }

  /**
   * Returns the height of the image.
   *
   * @return the height of the image
   */
  public int getHeight() {
    return height;
  }

  /**
   * Returns the width of the image.
   *
   * @return the width of the image
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the maximum value of the image.
   *
   * @return the maximum value of the image
   */
  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Reads a PPM image file and returns an Image object.
   *
   * @param filename the filename of the PPM file to be read
   * @return an Image object representing the PPM file
   * @throws IOException      if an I/O error occurs while reading the file
   * @throws RuntimeException if the file is not a valid PPM file
   */
  public Image readPPM(String filename)
          throws IOException, RuntimeException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Error: File " + filename + " not found!");
    }
    try {
      StringBuilder strBuilder = new StringBuilder();
      if (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          strBuilder.append(s).append(System.lineSeparator());
        }
        while (sc.hasNextLine()) {
          s = sc.nextLine();
          if (s.charAt(0) != '#') {
            strBuilder.append(s).append(System.lineSeparator());
          }
        }
      }

      sc = new Scanner(strBuilder.toString());
      String token;
      token = sc.next();
      if (!token.equals("P3")) {
        System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException();
    } catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException();
    }
    width = sc.nextInt();
    height = sc.nextInt();
    maxValue = sc.nextInt();
    image = new int[height][width][3];
    int i = 0;
    while (i < height) {
      int j = 0;
      while (j < width) {
        image[i][j][0] = sc.nextInt(); // red component
        image[i][j][1] = sc.nextInt(); // green component
        image[i][j][2] = sc.nextInt(); // blue component
        j++;
      }
      i++;
    }
    int[][][] result = new int[height][width][3];
    for (int k = 0; k < height; k++) {
      for (int l = 0; l < width; l++) {
        for (int m = 0; m < 3; m++) {
          result[k][l][m] = image[k][l][m];
        }
      }
    }

    newImage = new ImageImpl(height, width, maxValue, result);
    return newImage;
  }

  /**
   * Writes an image in the PPM format to a file at the specified file path.
   *
   * @param filePath the file path where the PPM file will be written
   * @param newImage the image to be written to the PPM file
   */
  public void writePPM(String filePath, Image newImage) {
    try {
      int[][][] outputPPM = newImage.getImage();
      FileWriter fileWriter = new FileWriter(filePath);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write("P3\n");
      bufferedWriter.write(outputPPM[0].length + " " + outputPPM.length + "\n");
      bufferedWriter.write("255\n");
      int i = 0;
      while (i < outputPPM.length) {
        int j = 0;
        while (j < outputPPM[0].length) {
          bufferedWriter.write(
                  outputPPM[i][j][0] + " " + outputPPM[i][j][1] + " "
                          + outputPPM[i][j][2] + "\n");
          j++;
        }
        i++;
      }
      bufferedWriter.close();
    } catch (IOException e) {
      System.err.println("Error writing the PPM file: " + e.getMessage());
    }
  }

  /**
   * Reads an image from a file at the specified file path.
   *
   * @param filename the file path of the image to be read
   * @return the Image object representing the image that was read from the file
   * @throws IOException if there is an error reading the image file
   */
  public Image readImage(String filename) throws IOException {
    BufferedImage input;
    input = ImageIO.read(new FileInputStream(filename));
    //System.out.println(input.getHeight()+""+input.getWidth());
    int[][][] result = new int[input.getHeight()][input.getWidth()][3];
    width = input.getWidth();
    height = input.getHeight();
    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        int color = input.getRGB(j, i);
        Color c = new Color(color);
        result[i][j][0] = c.getRed();
        result[i][j][1] = c.getGreen();
        result[i][j][2] = c.getBlue();
      }
    }
    this.maxValue = 255;
    newImage = new ImageImpl(height, width, maxValue, result);
    return newImage;
  }

  /**
   * Writes an image to a file at the specified file path in format determined by file extension.
   *
   * @param filePath the file path where the image will be written
   * @param newImage the image to be written to the file
   * @throws IOException if there is an error writing the image file
   */
  public void writeImage(String filePath, Image newImage)
          throws IOException {
    int[][][] outputFormat = newImage.getImage();
    BufferedImage output = new BufferedImage(
            outputFormat[0].length,
            outputFormat.length,
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < outputFormat.length; i++) {
      for (int j = 0; j < outputFormat[0].length; j++) {
        int r = outputFormat[i][j][0];
        int g = outputFormat[i][j][1];
        int b = outputFormat[i][j][2];
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    String extension = filePath.substring(filePath.indexOf(".") + 1);
    ImageIO.write(output, extension, new File(filePath));
  }
}