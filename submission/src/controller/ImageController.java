package controller;

import exception.ValidCheckedException;

import java.io.IOException;
import java.io.Reader;

/**
 * The ImageController interface provides a contract for classes that will control
 * the processing and management of images. This interface includes
 * two methods, start() and startScript(Reader), which initiates the processing of the images.
 */
public interface ImageController {

  /**
   * Starts the image processing and management.
   *
   * @throws IOException           if an input or output exception occurs during image processing.
   * @throws ValidCheckedException if the input image is not valid and cannot be processed.
   */
  void start() throws IOException, ValidCheckedException;

  /**
   * Starts the image processing and management using an input Reader object.
   *
   * @param imgInput the input Reader object containing the image data to be processed.
   * @throws ValidCheckedException if the input image is not valid and cannot be processed.
   */
  void startScript(Reader imgInput) throws IOException, ValidCheckedException;
}
