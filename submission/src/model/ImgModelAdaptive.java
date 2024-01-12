package model;

import exception.ValidCheckedException;

import java.io.IOException;

/**
 * This interface defines the methods that must be implemented by classes that represent
 * an adaptive image model.
 */
public interface ImgModelAdaptive {

  /**
   * Saves the image as a PPM file in the specified file path with the given filename.
   *
   * @param filePath The file path where the image will be saved.
   * @param filename The name of the file to be saved.
   * @return The saved image as an Image object.
   * @throws IOException           If an I/O error occurs while writing the file.
   * @throws ValidCheckedException If the file path or filename is invalid.
   */
  Image savePPM(String filePath, String filename) throws IOException, ValidCheckedException;


  /**
   * Applies a brightening effect to the image with the specified value,
   * and saves it as a new file with the given filename.
   *
   * @param value       The value of the brightening effect to be applied.
   * @param filename    The name of the original file to be transformed.
   * @param newFilename The name of the new file to be saved.
   * @return The transformed image as an Image object.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  Image applyBrighten(int value, String filename, String newFilename) throws ValidCheckedException;

  /**
   * Applies a vertical flip to the image, and saves it as a new file with the given filename.
   *
   * @param filename    The name of the original file to be transformed.
   * @param newFilename The name of the new file to be saved.
   * @return The transformed image as an Image object.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  Image applyVFlip(String filename, String newFilename) throws ValidCheckedException;


  /**
   * Applies a vertical flip to the image, and saves it as a new file with the given filename.
   *
   * @param filename    The name of the original file to be transformed.
   * @param newFilename The name of the new file to be saved.
   * @return The transformed image as an Image object.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  Image applyHFlip(String filename, String newFilename) throws ValidCheckedException;

  /**
   * Applies a greyscale transformation to the specified component of the image, and saves
   * it as a new file with the given filename.
   *
   * @param component   The component of the image to be transformed to greyscale.
   * @param filename    The name of the original file to be transformed.
   * @param newFilename The name of the new file to be saved.
   * @return The transformed image as an Image object.
   * @throws ValidCheckedException If the specified component is invalid.
   */
  Image applyGreyscaleComponent(String component, String filename, String newFilename)
          throws ValidCheckedException;

  /**
   * Splits the image into its RGB components, and saves each component as a
   * separate file with the given filenames.
   *
   * @param filename   The name of the original file to be split.
   * @param redImage   The name of the file to save the red component of the image.
   * @param greenImage The name of the file to save the green component of the image.
   * @param blueImage  The name of the file to save the blue component of the image.
   * @return The transformed image as an Image object.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  Image applyRGBSplit(String filename, String redImage, String greenImage, String blueImage)
          throws ValidCheckedException;


  /**
   * Applies an RGB color channel combination to an image using
   * separate red, green, and blue images.
   *
   * @param filename   the filename of the original image
   * @param redImage   the filename of the red image
   * @param greenImage the filename of the green image
   * @param blueImage  the filename of the blue image
   * @return an Image object with the combined RGB channels
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  Image applyRGBCombine(String filename, String redImage, String greenImage, String blueImage)
          throws ValidCheckedException;


  /**
   * Loads an image from a file with the given filename and alias, and returns a new Image object.
   *
   * @param filename the filename of the image to load
   * @param alias    the alias to give to the loaded image
   * @param newImage an optional new Image object to use for the loaded image
   * @return the loaded Image object
   * @throws IOException           if there is an I/O error while loading the image
   * @throws ValidCheckedException if the loaded image is not valid
   */
  Image load(String filename, String alias, Image newImage) throws IOException,
          ValidCheckedException;


  /**
   * Applies a blur filter to an image with the given filename and alias.
   *
   * @param fileName the filename of the image to apply the filter to
   * @param alias    the alias to give to the filtered image
   * @return the filtered Image object
   * @throws IllegalArgumentException if the image is not valid
   * @throws ValidCheckedException    Thrown if the provided parameters are invalid.
   */
  Image applyBlur(String fileName, String alias)
          throws IllegalArgumentException, ValidCheckedException;


  /**
   * Applies a sharpening filter to an image with the given filename and alias.
   *
   * @param fileName the filename of the image to apply the filter to
   * @param alias    the alias to give to the filtered image
   * @return the filtered Image object
   * @throws IllegalArgumentException if the image is not valid
   * @throws ValidCheckedException    Thrown if the provided parameters are invalid.
   */
  Image applySharpen(String fileName, String alias)
          throws IllegalArgumentException, ValidCheckedException;


  /**
   * Transforms an image to greyscale with the given filename and alias.
   *
   * @param fileName the filename of the image to transform
   * @param alias    the alias to give to the transformed image
   * @return the transformed Image object
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  Image transformGreyscale(String fileName, String alias) throws ValidCheckedException;


  /**
   * Transforms an image to sepia with the given filename and alias.
   *
   * @param fileName the filename of the image to transform
   * @param alias    the alias to give to the transformed image
   * @return the transformed Image object
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  Image sepiaTransform(String fileName, String alias) throws ValidCheckedException;

  /**
   * Applies a dithering effect to an image with the given filename and alias.
   *
   * @param fileName the filename of the image to apply the effect to
   * @param alias    the alias to give to the transformed image
   * @return the transformed Image object
   * @throws ValidCheckedException if the image is not valid
   */
  Image ditherTransform(String fileName, String alias) throws ValidCheckedException;

  /**
   * Returns a two-dimensional array representing the histogram of the image.
   *
   * @param filename the name of the file containing the image.
   * @return a two-dimensional integer array representing the histogram of the image.
   * @throws ValidCheckedException Thrown if the provided parameters are invalid.
   */
  int[][] getHistogram(String filename) throws ValidCheckedException;
}
