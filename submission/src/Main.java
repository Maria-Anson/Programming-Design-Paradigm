import controller.ImageControllerGuiImpl;
import exception.ValidCheckedException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import controller.ImageController;

import controller.ImageControllerImpl;
import model.ImgModel;
import model.ImgModelImpl;
import view.ImgView;
import view.ImgViewImpl;
import view.ImgViewInteractive;
import view.ImgViewInteractiveImpl;

/**
 * The Main class is the entry point for the program. It handles the initialization of the Model,
 * View, and Controller objects based on the command line arguments passed to the program.
 * The program can be run in three modes:
 * -file mode: takes a file as input and executes the commands in the file.
 * -text mode: prompts the user to enter commands in the console and executes them.
 * default mode: starts the graphical user interface (GUI) for the program.
 */
public class Main {

  /**
   * The main method is the entry point for the program. It initializes the Model, View,
   * and Controller, objects based on the command line arguments passed to the program and
   * starts the appropriate mode.
   *
   * @param args the command line arguments passed to the program
   *             -file: runs the program in file mode
   *             -text: runs the program in text mode
   *             (default): runs the program in GUI mode
   * @throws IOException           if there is an I/O error while reading the file in file mode.
   * @throws ValidCheckedException if there is an error in the syntax of the commands in file mode.
   */
  public static void main(String[] args) throws IOException, ValidCheckedException {
    if (args.length != 0 && args[0].equals("-file")) {
      Reader input = new FileReader(args[1]);
      ImgView view = new ImgViewImpl();
      ImgModel model = new ImgModelImpl();
      ImageController controller = new ImageControllerImpl(model, view);
      controller.startScript(input);
    } else if (args.length != 0 && args[0].equals("-text")) {
      ImgModel model = new ImgModelImpl();
      ImgView view = new ImgViewImpl();
      ImageController controller = new ImageControllerImpl(model, view);
      controller.start();
    } else {
      ImgModel model = new ImgModelImpl();
      ImgViewInteractive view = new ImgViewInteractiveImpl();
      ImageController controller = new ImageControllerGuiImpl(model, view);
      controller.start();
    }
  }
}