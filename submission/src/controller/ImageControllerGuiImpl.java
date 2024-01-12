package controller;

import exception.ValidCheckedException;
import model.Image;
import model.ImgModel;
import model.ImgModelAdapterImpl;
import model.ImgModelAdaptive;
import util.ImgUtil;
import view.ImgViewInteractive;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The ImageControllerGuiImpl class implements the ImageController interface and
 * provides a controller for the Image Processing application's graphical user interface (GUI).
 * It maintains an instance of ImgModel and ImgViewInteractive, and a collection of Command objects.
 * It processes user input, updates the model, and refreshes the view accordingly.
 */
public class ImageControllerGuiImpl implements ImageController {

  private final ImgModel model;
  private final ImgViewInteractive view;
  private final ImgUtil util;
  private final Map<String, Command> commands;
  private Image img;
  private int[][] histImage;
  private ImgModelAdaptive mdl;
  private String previous;
  private String combineGreen;
  private String combineBlue;

  /**
   * Constructs a new ImageControllerGuiImpl object with the specified ImgModel and
   * ImgViewInteractive objects. It also initializes an ImgUtil object and a HashMap
   * object that contains a collection of Command objects.
   *
   * @param model an ImgModel object
   * @param view  an ImgViewInteractive object
   */
  public ImageControllerGuiImpl(ImgModel model, ImgViewInteractive view) {
    this.model = model;
    this.view = view;
    this.util = new ImgUtil();
    this.commands = new HashMap<>();
    this.commands.put("load", new LoadCommand());
    this.commands.put("brighten", new BrightenCommand());
    this.commands.put("vertical-flip", new VFlipCommand());
    this.commands.put("horizontal-flip", new HFlipCommand());
    this.commands.put("rgb-split", new SplitRGBCommand());
    this.commands.put("rgb-combine", new CombineRGBCommand());
    this.commands.put("greyscale", new GreyscaleCommand());
    this.commands.put("blur", new BlurCommand());
    this.commands.put("sharpen", new SharpenCommand());
    this.commands.put("sepia", new SepiaTransformCommand());
    this.commands.put("dither", new DitherCommand());
    this.commands.put("save", new SaveCommand());
    this.commands.put("clear", new ClearCommand());
  }

  @Override
  public void start() {
    this.view.getViewInput(this::input);
    this.view.makeVisible();
  }

  @Override
  public void startScript(Reader imgInput) {
    // Added for later implementation of batch inputs.
  }

  /**
   * This method receives an image input as a String and parses it line by line.
   * Each line is split into an array of Strings using whitespace as the delimiter.
   * The first element of the array is used to retrieve the corresponding
   * Command object from a map of commands. If the Command object is not found,
   * an IllegalArgumentException is thrown.
   * Otherwise, the Command's execute method is called with the model and
   * the array of Strings as arguments.
   *
   * @param imgInput a String representing the image input
   * @throws IOException           if there is an I/O exception while reading the input
   * @throws ValidCheckedException if there is an error in the input that violates expected format
   */
  public void guiInput(String imgInput) throws IOException, ValidCheckedException {
    Scanner sc = new Scanner(imgInput);
    while (sc.hasNextLine()) {
      {
        String strLines = sc.nextLine();
        String[] strLine = strLines.split("\\s+");
        Command command = commands.get(strLine[0]);
        if (command == null) {
          throw new IllegalArgumentException("Error: Not a Valid Command");
        }
        command.execute(model, strLine);
      }
    }
  }

  /**
   * This method receives input from the user and processes it.
   * It displays any errors encountered during the process.
   *
   * @param input a String representing the input from the user
   */
  public void input(String input) {
    try {
      guiInput(input);
    } catch (Exception exception) {
      view.displayErrors(exception.getMessage());
    }
    if (previous != null) {
      view.setImage(img.getImage());
      view.setHistogram(histImage);
      view.refresh();
    }
  }

  /**
   * A private interface Command provides a contract for classes that will execute commands.
   */
  interface Command {

    /**
     * Executes the load command using the given ImgModel, ImgView, and arguments.
     * Determines the file type of the input image and loads it into the model.
     * Displays the status of the loaded image in the view.
     *
     * @param model the ImgModel object used for image processing and management.
     * @param args  the array of arguments for the load command being executed.
     * @throws ValidCheckedException if the input image is not valid and cannot be processed.
     */
    void execute(ImgModel model, String[] args) throws ValidCheckedException,
            IOException;
  }

  /**
   * A command that loads an image file into the ImgModel and updates the view.
   */
  private class LoadCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException,
            IOException {
      if (args.length > 2) {
        StringBuilder sb = new StringBuilder();
        sb.append(args[1]);
        for (int i = 2; i < args.length; i++) {
          sb.append(" ").append(args[i]);
        }
        args[1] = sb.toString();
      }
      if (!isValidFile(args[1])) {
        throw new ValidCheckedException("invalid file");
      }
      String imageName = args[1].substring(0, args[1].lastIndexOf("."));
      previous = imageName;
      String extension = args[1].substring(args[1].lastIndexOf(".") + 1);
      if (Arrays.asList(ImageIO.getReaderFileSuffixes()).contains(extension)) {
        img = util.readImage(args[1]);
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.load(args[1], imageName, img);
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);

      } else if (extension.equalsIgnoreCase("ppm")) {
        img = util.readPPM(args[1]);
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.load(args[1], imageName, img);
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);

      } else {
        throw new ValidCheckedException("invalid file, accepted formats are ppm, " +
                "jpg, jpeg, png, bmp");
      }
    }

    /**
     * Checks if the given file is a valid image file.
     *
     * @param filename The name of the file to be checked.
     * @return True if the file is a valid image file, false otherwise.
     */
    private Boolean isValidFile(String filename) {
      if (null != filename && !"".equals(filename)) {
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        if (!"".equals(ext)) {
          return "ppm".equals(ext) || "jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext)
                  || "bmp".equals(ext);
        }
      }
      return true;
    }
  }

  /**
   * A command that brightens an image file into the ImgModel and updates the view.
   */
  private class BrightenCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.applyBrighten(Integer.parseInt(args[1]),
                previous, "bright");
        previous = "bright";
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that flips an image vertical into the ImgModel and updates the view.
   */
  private class VFlipCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.applyVFlip(previous, "v-flip");
        previous = "v-flip";
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that flips an image horizontal file into the ImgModel and updates the view.
   */
  private class HFlipCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.applyHFlip(previous, "h-flip");
        previous = "h-flip";
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that splits an image file into the ImgModel and updates the view.
   */
  private class SplitRGBCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.applyRGBSplit(previous, "red",
                "green", "blue");
        previous = "red";
        combineGreen = "green";
        combineBlue = "blue";

        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(args[1]);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that combine an image file into the ImgModel and updates the view.
   */
  private class CombineRGBCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.applyRGBCombine("combined",
                previous, combineGreen, combineBlue);
        System.out.println(previous + combineBlue + combineGreen);
        previous = "combined";
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that greyscale an image file into the ImgModel and updates the view.
   */
  private class GreyscaleCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        if (args.length == 3) {
          mdl = new ImgModelAdapterImpl(model);
          img = ImageControllerGuiImpl.this.mdl.transformGreyscale(previous, "luma");
          previous = "luma";
          histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);

        } else {
          mdl = new ImgModelAdapterImpl(model);
          img = ImageControllerGuiImpl.this.mdl.applyGreyscaleComponent(args[1],
                  previous, "grey");
          previous = "grey";
          histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);

        }
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }

    }
  }

  /**
   * A command that blur an image file into the ImgModel and updates the view.
   */
  private class BlurCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.applyBlur(previous, "blur");
        previous = "blur";
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that sharpen an image file into the ImgModel and updates the view.
   */
  private class SharpenCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.applySharpen(previous, "sharp");
        previous = "sharp";
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that sepia an image file into the ImgModel and updates the view.
   */
  private class SepiaTransformCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.sepiaTransform(previous, "sepia");
        previous = "sepia";
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that dither an image file into the ImgModel and updates the view.
   */
  private class DitherCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException {
      if (previous != null) {
        mdl = new ImgModelAdapterImpl(model);
        img = ImageControllerGuiImpl.this.mdl.ditherTransform(previous, "dither");
        previous = "dither";
        histImage = ImageControllerGuiImpl.this.mdl.getHistogram(previous);
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that saves an image file into the ImgModel and updates the view.
   */
  private class SaveCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) throws ValidCheckedException,
            IOException {
      if (previous != null) {
        if (args.length > 2) {
          StringBuilder sb = new StringBuilder();
          sb.append(args[1]);
          for (int i = 2; i < args.length; i++) {
            sb.append(" ").append(args[i]);
          }
          args[1] = sb.toString();
        }
        String extension = args[1].substring(args[1].lastIndexOf(".") + 1);
        if (Arrays.asList(ImageIO.getReaderFileSuffixes()).contains(extension)) {
          img = ImageControllerGuiImpl.this.mdl.savePPM(args[1], previous);
          util.writeImage(args[1], img);
        } else if (extension.equalsIgnoreCase("ppm")) {
          img = ImageControllerGuiImpl.this.mdl.savePPM(args[1], previous);
          util.writePPM(args[1], img);
        } else {
          throw new ValidCheckedException("invalid file, accepted formats are ppm, jpg, " +
                  "jpeg, png, bmp");
        }
      } else {
        throw new IndexOutOfBoundsException("Image Not Loaded");
      }
    }
  }

  /**
   * A command that clears an image file into the ImgModel and updates the view.
   */
  private class ClearCommand implements Command {
    @Override
    public void execute(ImgModel model, String[] args) {
      previous = null;
    }
  }
}

