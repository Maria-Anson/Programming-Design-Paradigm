package view;

import java.util.function.Consumer;

/**
 * The ImgViewInteractive interface represents interactive view for image processing application.
 * It provides methods for making objects visible in the view, getting input from user, displaying
 * error messages, setting images and histograms in the view, and refreshing the view.
 */
public interface ImgViewInteractive {

  /**
   * This method makes objects visible in the view.
   * It uses built-in JFrame method called setVisible to make things like panels visible on screen.
   */
  void makeVisible();

  /**
   * This method provides the view with a callback option to process a command.
   *
   * @param callback An object that represents the command to be executed.
   */
  void getViewInput(Consumer<String> callback);


  /**
   * This method creates a pop-out error message for handling exceptions thrown through the view.
   * Each message is pulled from the controller/model.
   *
   * @param error The error that is thrown.
   */
  void displayErrors(String error);

  /**
   * This method is used by the controller to set the image displayed in the view to the current
   * image that is stored within the controller's model. It uses a method within ImageUtil called
   * getBufferedImage() that passes this view an image that can be displayed.
   *
   * @param image An integer array of pixels.
   */
  void setImage(int[][][] image);

  /**
   * This method is used to set the histogram image in the view.
   *
   * @param histImage An integer 2D array that represents the histogram image.
   */
  void setHistogram(int[][] histImage);

  /**
   * This method is used to refresh view so that images and other objects can be reset and updated
   * without having to save and load images multiple times.
   */
  void refresh();

}



