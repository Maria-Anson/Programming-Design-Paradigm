package model;

import exception.ValidCheckedException;

import java.io.IOException;
import java.util.Map;

/**
 * This interface represents a model for image processing. It defines a set of methods for applying
 * various image manipulation operations, as well as loading and checking the validity
 * of image files.
 */
public interface ImgModel {

  /**
   * Reads an image file and returns a deep copy of the image data as a 3D array.
   *
   * @param filename the name of the image file to read
   * @return a deep copy of the image data as a 3D array
   * @throws IllegalArgumentException if the file cannot be read or the alias is invalid
   */
  int[][][] deepCopy(String filename) throws IllegalArgumentException;

  /**
   * Saves the image data as a PPM file with the given file path and name.
   *
   * @param filePath the file path to save the image file to
   * @param filename the name of the image file to save
   * @throws IOException           Thrown if an IO error occurs during loading.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  Image savePPM(String filePath, String filename) throws IOException, ValidCheckedException;

  /**
   * Brightens the image by the given value and saves the modified image as a new PPM file.
   *
   * @param value       the amount to brighten the image by (can be negative for darkening)
   * @param filename    the name of the original image file
   * @param newFilename the name of the new image file to save the modified image to
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void applyBrighten(int value, String filename, String newFilename) throws ValidCheckedException;

  /**
   * Flips the image vertically and saves the modified image as a new PPM file.
   *
   * @param filename    the name of the original image file
   * @param newFilename the name of the new image file to save the modified image to
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void applyVFlip(String filename, String newFilename) throws ValidCheckedException;

  /**
   * Flips the image horizontally and saves the modified image as a new PPM file.
   *
   * @param filename    the name of the original image file
   * @param newFilename the name of the new image file to save the modified image to
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void applyHFlip(String filename, String newFilename) throws ValidCheckedException;

  /**
   * Applies the greyscale filter to a specified component of the image.
   *
   * @param component   The color component to be turned to greyscale.
   * @param filename    The name of the image file to be processed.
   * @param newFilename The name of the new image file to be created with the greyscale filter.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void applyGreyscaleComponent(String component, String filename, String newFilename)
          throws ValidCheckedException;

  /**
   * Splits the image into its red, green, and blue color components.
   *
   * @param filename   The name of the image file to be split.
   * @param redImage   The name of the new image file to be created with only the red component.
   * @param greenImage The name of the new image file to be created with only the green component.
   * @param blueImage  The name of the new image file to be created with only the blue component.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void applyRGBSplit(String filename, String redImage, String greenImage, String blueImage)
          throws ValidCheckedException;

  /**
   * Combines separate red, green, and blue color component images into a single color image.
   *
   * @param filename   The name of the new image file to be created with the combined components.
   * @param redImage   The name of the image file with the red color component.
   * @param greenImage The name of the image file with the green color component.
   * @param blueImage  The name of the image file with the blue color component.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void applyRGBCombine(String filename, String redImage, String greenImage, String blueImage)
          throws ValidCheckedException;

  /**
   * Loads an image file into memory.
   *
   * @param filename The name of the image file to be loaded.
   * @param alias    An optional alias to be associated with the loaded image.
   * @throws IOException           Thrown if an IO error occurs during loading.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void load(String filename, String alias, Image newImage) throws IOException,
          ValidCheckedException;

  /**
   * Applies a blur effect to the image and saves the result with the given alias.
   *
   * @param fileName the name of the file to read the image from
   * @param alias    the alias to use for the output file
   * @throws IllegalArgumentException if the file cannot be read or the alias is invalid
   * @throws ValidCheckedException    Thrown if the provided parameters are invalid.
   */
  void applyBlur(String fileName, String alias) throws IllegalArgumentException,
          ValidCheckedException;

  /**
   * Applies a sharpened effect to the image and saves the result with the given alias.
   *
   * @param fileName the name of the file to read the image from
   * @param alias    the alias to use for the output file
   * @throws ValidCheckedException    Thrown if the provided parameters are invalid.
   * @throws IllegalArgumentException if the file cannot be read or the alias is invalid
   */
  void applySharpen(String fileName, String alias) throws IllegalArgumentException,
          ValidCheckedException;

  /**
   * Transforms the image to greyscale and saves the result with the given alias.
   *
   * @param fileName the name of the file to read the image from
   * @param alias    the alias to use for the output file
   * @throws ValidCheckedException if the file cannot be read or the alias is invalid
   */
  void transformGreyscale(String fileName, String alias) throws ValidCheckedException;

  /**
   * Applies a sepia effect to the image and saves the result with the given alias.
   *
   * @param fileName the name of the file to read the image from
   * @param alias    the alias to use for the output file
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void sepiaTransform(String fileName, String alias) throws ValidCheckedException;

  /**
   * Applies a dither effect to the image and saves the result with the given alias.
   *
   * @param fileName the name of the file to read the image from
   * @param alias    the alias to use for the output file
   * @throws ValidCheckedException if the file cannot be read or the alias is invalid
   */
  void ditherTransform(String fileName, String alias) throws ValidCheckedException;

  /**
   * This method creates a histogram for a given image file and saves it in the form of a Map.
   *
   * @param fileName the name of the image file.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  void createHistogram(String fileName) throws ValidCheckedException;

  /**
   * This method retrieves the image map.
   *
   * @return a Map containing the image data.
   */
  Map<String, int[][][]> getImageMap();

  /**
   * This method retrieves the histogram image map.
   *
   * @return a Map containing the histogram data.
   */
  Map<String, int[][]> getHistImage();

  /**
   * This method retrieves the maximum value in the histogram.
   *
   * @return the maximum value in the histogram.
   */
  int getMaxValue();
}
