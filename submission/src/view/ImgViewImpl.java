package view;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is an implementation of the ImgView interface for displaying status messages
 * related to image operations.
 */
public class ImgViewImpl implements ImgView {

  private final Map<String, Command> commands = new HashMap<>();

  /**
   * Constructor for ImgViewImpl that initializes values.
   */
  public ImgViewImpl() {
    commands.put("load", new LoadCommand());
    commands.put("brighten", new BrightenCommand());
    commands.put("vertical-flip", new FlipCommand("vertical"));
    commands.put("horizontal-flip", new FlipCommand("horizontal"));
    commands.put("rgb-split", new SplitCommand());
    commands.put("rgb-combine", new CombineCommand());
    commands.put("greyscale", new GreyscaleCommand());
    commands.put("blur", new BlurCommand());
    commands.put("sharpen", new SharpenCommand());
    commands.put("dither", new DitherCommand());
    commands.put("greyscaleTransform", new GreyscaleTransformCommand());
    commands.put("sepia", new SepiaCommand());
    commands.put("save", new SaveCommand());
    commands.put("run", new RunCommand());
  }

  @Override
  public void displayStatus(String image, String imageName) {
    Command command = commands.get(imageName);
    if (command != null) {
      command.execute(image);
    }
  }

  /**
   * The Command interface represents a command that can be executed on an image.
   * It contains a single method, execute, which takes in the name of the image to be processed.
   */
  private interface Command {
    /**
     * Executes a command on the specified image.
     *
     * @param imageName the name of the image to be processed.
     */
    void execute(String imageName);
  }

  private class LoadCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " Loaded Successfully");
    }
  }

  private class BrightenCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " Brightened Successfully");
    }
  }

  private class FlipCommand implements Command {
    private final String direction;

    public FlipCommand(String direction) {
      this.direction = direction;
    }

    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " flipped " + direction + " Successfully");
    }
  }

  private class SplitCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " Split Successfully");
    }
  }

  private class GreyscaleCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("greyscale " + imageName + " Created Successfully");
    }
  }

  private class CombineCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " Combined Successfully");
    }
  }

  private class BlurCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " Blurred Successfully");
    }
  }

  private class SharpenCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " Sharpened Successfully");
    }
  }

  private class SepiaCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " transformed to Sepia Successfully");
    }
  }

  private class GreyscaleTransformCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " GreyScaled using transformation Successfully");
    }
  }

  private class DitherCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " Dithered Successfully");
    }
  }

  private class SaveCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Image " + imageName + " Saved Successfully");
    }
  }

  private class RunCommand implements Command {
    @Override
    public void execute(String imageName) {
      System.out.println("Script " + imageName + " Ran Successfully");
    }
  }
}