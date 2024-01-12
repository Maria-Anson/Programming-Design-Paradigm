package controller;

import exception.ValidCheckedException;
import model.ImgModel;
import util.ImgUtil;
import view.ImgView;

import javax.imageio.ImageIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The ImageControllerImpl class is an implementation of the ImageController interface.
 * It controls the processing and management of images based on the input commands from the user.
 */
public class ImageControllerImpl implements ImageController {

  private final ImgModel model;
  private final ImgView view;
  private final Map<String, Command> commands;
  private final StringBuilder sb;
  private final ImgUtil util;

  /**
   * Constructs an ImageControllerImpl object with the given ImgModel and ImgView objects.
   * Initializes the ImgUtil, commands map, and StringBuilder.
   *
   * @param model the ImgModel object used for image processing and management.
   * @param view  the ImgView object used for displaying image information and status.
   */
  public ImageControllerImpl(ImgModel model, ImgView view) {
    this.model = model;
    this.view = view;
    this.util = new ImgUtil();
    this.commands = new HashMap<>();
    this.sb = new StringBuilder();
    this.commands.put("load", new LoadCommand());
    this.commands.put("brighten", new BrightenCommand());
    this.commands.put("vertical-flip", new VFlipCommand());
    this.commands.put("horizontal-flip", new HFlipCommand());
    this.commands.put("rgb-split", new SplitRGBCommand());
    this.commands.put("rgb-combine", new CombineRGBCommand());
    this.commands.put("greyscale", new GreyscaleCommand());
    this.commands.put("save", new SaveCommand());
    this.commands.put("quit", new QuitCommand());
    this.commands.put("blur", new BlurCommand());
    this.commands.put("sharpen", new SharpenCommand());
    this.commands.put("sepia", new SepiaTransformCommand());
    this.commands.put("dither", new DitherCommand());
    this.commands.put("run", new RunCommand());
  }

  @Override
  public void start() throws ValidCheckedException {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      String strLines = sc.nextLine();
      executeStart(strLines);
    }

  }

  @Override
  public void startScript(Reader imgInput) throws IOException, ValidCheckedException {
    BufferedReader read = new BufferedReader(imgInput);
    String strLines = read.readLine();

    while (strLines != null) {
      sb.append(strLines);
      sb.append("\n");
      strLines = read.readLine();
    }
    String[] txt = sb.toString().split("\n");

    for (String s : txt) {
      executeStart(s);
    }
  }

  /**
   * Executes a command with the given input string.
   *
   * @param input the input string to execute the command with
   */
  private void executeStart(String input) {
    try {
      String[] strLine = input.split("\\s+");
      char c = strLine[0].charAt(0);
      if (c == '#') {
        return;
      }
      Command command = commands.get(strLine[0]);
      if (command == null) {
        throw new ValidCheckedException("Error: Not a Valid Command! " +
                "Try again with valid command");
      }
      command.execute(model, view, strLine);
    } catch (ValidCheckedException | NullPointerException e) {
      System.err.println("Error executing command: " + e.getMessage());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * A private interface Command provides a contract for classes that will execute commands.
   * The interface includes a single method, execute(), which takes an ImgModel, an ImgView,
   * and an array of arguments as parameters and throws ValidCheckedException and IOException.
   */
  interface Command {

    /**
     * Executes the load command using the given ImgModel, ImgView, and arguments.
     * Determines the file type of the input image and loads it into the model.
     * Displays the status of the loaded image in the view.
     *
     * @param model the ImgModel object used for image processing and management.
     * @param view  the ImgView object used for displaying image information and status.
     * @param args  the array of arguments for the load command being executed.
     * @throws ValidCheckedException if the input image is not valid and cannot be processed.
     */
    void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException,
            IOException;
  }

  private class LoadCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException,
            IOException {
      if (!isValidFile(args[1])) {
        throw new ValidCheckedException("invalid file");
      }
      if (args.length < 3) {
        throw new ValidCheckedException("Incomplete Command");
      }
      String extension = args[1].substring(args[1].lastIndexOf(".") + 1);
      if (Arrays.asList(ImageIO.getReaderFileSuffixes()).contains(extension)) {
        ImageControllerImpl.this.model.load(args[1], args[2], util.readImage(args[1]));
        ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
      } else if (extension.equalsIgnoreCase("ppm")) {
        ImageControllerImpl.this.model.load(args[1], args[2], util.readPPM(args[1]));
        ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
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

  private class BrightenCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      if (args.length < 4) {
        throw new ValidCheckedException("Invalid Command");
      }
      ImageControllerImpl.this.model.applyBrighten(Integer.parseInt(args[1]), args[2], args[3]);
      ImageControllerImpl.this.view.displayStatus(args[3], args[0]);
    }
  }

  private class VFlipCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      if (args.length < 3) {
        throw new ValidCheckedException("Incomplete Command");
      }
      ImageControllerImpl.this.model.applyVFlip(args[1], args[2]);
      ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
    }
  }

  private class HFlipCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      if (args.length < 3) {
        throw new ValidCheckedException("Incomplete Command");
      }
      ImageControllerImpl.this.model.applyHFlip(args[1], args[2]);
      ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
    }
  }

  private class SplitRGBCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      if (args.length < 5) {
        throw new ValidCheckedException("Incomplete Command");
      }
      ImageControllerImpl.this.model.applyRGBSplit(args[1], args[2], args[3], args[4]);
      ImageControllerImpl.this.view.displayStatus(args[1], args[0]);
    }
  }

  private class CombineRGBCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      if (args.length < 5) {
        throw new ValidCheckedException("Incomplete Command");
      }
      ImageControllerImpl.this.model.applyRGBCombine(args[1], args[2], args[3], args[4]);
      ImageControllerImpl.this.view.displayStatus(args[1], args[0]);
    }
  }

  private class GreyscaleCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      if (args.length < 3) {
        throw new ValidCheckedException("Incomplete Command");
      }
      if (args.length == 3) {
        ImageControllerImpl.this.model.transformGreyscale(args[1], args[2]);
        ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
      } else {
        ImageControllerImpl.this.model.applyGreyscaleComponent(args[1], args[2], args[3]);
        ImageControllerImpl.this.view.displayStatus(args[3], args[0]);
      }

    }
  }

  private class SaveCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException,
            IOException {
      if (args.length < 3) {
        throw new ValidCheckedException("Incomplete Command");
      }
      String extension = args[1].substring(args[1].lastIndexOf(".") + 1);
      if (Arrays.asList(ImageIO.getReaderFileSuffixes()).contains(extension)) {
        util.writeImage(args[1], ImageControllerImpl.this.model.savePPM(args[1], args[2]));
      } else if (extension.equalsIgnoreCase("ppm")) {
        util.writePPM(args[1], ImageControllerImpl.this.model.savePPM(args[1], args[2]));
      } else {
        throw new ValidCheckedException("invalid file, accepted formats are ppm, jpg, " +
                "jpeg, png, bmp");
      }
      ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
    }
  }

  private class RunCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException,
            IOException {
      if (args.length < 2) {
        throw new ValidCheckedException("Incomplete Command");
      }
      Reader imgInput = new FileReader(args[1]);
      startScript(imgInput);
      ImageControllerImpl.this.view.displayStatus(args[1], args[0]);
    }
  }

  private class BlurCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      ImageControllerImpl.this.model.applyBlur(args[1], args[2]);
      ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
    }
  }

  private class SharpenCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      ImageControllerImpl.this.model.applySharpen(args[1], args[2]);
      ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
    }
  }

  private class SepiaTransformCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      ImageControllerImpl.this.model.sepiaTransform(args[1], args[2]);
      ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
    }
  }

  private class DitherCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) throws ValidCheckedException {
      ImageControllerImpl.this.model.ditherTransform(args[1], args[2]);
      ImageControllerImpl.this.view.displayStatus(args[2], args[0]);
    }
  }

  private class QuitCommand implements Command {
    @Override
    public void execute(ImgModel model, ImgView view, String[] args) {
      System.exit(0);
    }
  }

}