package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import util.ImgUtil;

/**
 * This class represents a graphical user interface (GUI) for an image viewer and processor.
 * It extends the JFrame class and implements the ImgViewInteractive interface.
 * It contains many components such as buttons, labels and panels to display and manipulate images.
 * The class also includes methods for building the GUI, loading images, and generating histograms.
 */
public class ImgViewInteractiveImpl extends JFrame implements ImgViewInteractive {

  private static final int BINS = 256;
  private final JMenuBar menu = new JMenuBar();
  private JFileChooser fileChooser;
  private JButton commandButton;
  private JPanel featurePanel;
  private JLabel imageLabel;
  private JLabel imageLabelHist;
  private boolean isImageLoaded = false;
  private boolean isSplit = false;
  private Consumer<String> viewInput;
  private int[][] histImage;
  private int[] redHistogram;
  private int[] greenHistogram;
  private int[] blueHistogram;
  private int[] intensityHistogram;

  /**
   * Constructs an instance of ImgViewInteractiveImpl with default properties.
   * The method initializes the GUI and sets its size, title, and layout.
   */
  public ImgViewInteractiveImpl() {
    super();
    this.setBackground(Color.GRAY);
    this.setTitle("Image Processor (Priyanshu and Ronhit)");
    this.setSize(1200, 1200);
    this.setMinimumSize(new Dimension(1200, 600));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    buildImageDisplay();
    buildHistDisplay();
    buildButtons();
    viewInput = null;
    this.pack();
  }

  /**
   * This helper method builds the image display. The image display is set to the dimensions of 600
   * by 600, and includes a scroll pane (this allows for images that are larger than the 600 by 600
   * frame to be scrollable).
   */
  private void buildImageDisplay() {
    imageLabel = new JLabel();
    imageLabel.setMinimumSize(new Dimension(10, 10));
    JScrollPane scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(600, 600));
    this.add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Builds and adds a histogram display panel to the current panel.
   * The histogram display panel contains an image label and a scroll pane.
   * The image label displays the histogram image and the scroll pane allows
   * the user to scroll through the image if it exceeds the panel size.
   * The panel is added to the right side of the current panel using the BorderLayout.
   * The minimum size of the image label is set to 10x10 and the preferred size of
   * the scroll pane is set to 600x600.
   */
  private void buildHistDisplay() {
    imageLabelHist = new JLabel();
    imageLabelHist.setMinimumSize(new Dimension(10, 10));
    JScrollPane scrollPaneHist = new JScrollPane(imageLabelHist);
    scrollPaneHist.setPreferredSize(new Dimension(600, 600));
    this.add(scrollPaneHist, BorderLayout.EAST);
  }

  /**
   * This method builds the button panel and it's contents. It contains a title to help the user
   * understand what to do, a text area for the user to enter input, and five buttons: run, save
   * batch, undo, redo, quit.
   */
  private void buildButtons() {
    buildMenuBarFile();
    buildMenuBarAbout();
    buildButtonsJPanel();
    buildLoadButton();
    buildClearButton();
    buildQuitButton();
  }

  /**
   * Builds the "File" menu for a graphical user interface.
   * This menu includes options to load and save images, and to quit the program.
   */
  private void buildMenuBarFile() {
    JMenu fileSubmenu = new JMenu("File");
    JMenuItem load = new JMenuItem("Load Picture");
    final JFileChooser loader =
            new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    load.addActionListener((ActionEvent e) -> {
      FileFilter image = new FileNameExtensionFilter("Image files",
              ImageIO.getReaderFileSuffixes());
      loader.setFileFilter(image);
      int returnValue = loader.showOpenDialog(null);
      if (viewInput != null && returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = loader.getSelectedFile();
        viewInput.accept("load " + selectedFile.toString());
      }
    });
    fileSubmenu.add(load);

    JMenuItem save = new JMenuItem("Save Image");
    save.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        int returnVal = fileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          String filePath = file.getAbsolutePath();
          if (viewInput != null) {
            viewInput.accept("save " + filePath);
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });

    fileSubmenu.add(save);
    JMenuItem quit = new JMenuItem("Quit");
    quit.addActionListener((ActionEvent e) -> {
      if (viewInput != null) {
        System.exit(0);
      }
    });
    fileSubmenu.add(quit);
    menu.add(fileSubmenu);
  }

  /**
   * Builds and sets the "About" section of the menu bar for the
   * Java-based image processing application.
   * The section includes an "About Program" option and a "Help" option, which display information
   * about the application and instructions for using it, respectively, when clicked.
   */
  private void buildMenuBarAbout() {
    JMenu fileSubmenu = new JMenu("About");
    JMenuItem about = new JMenuItem("About Program");
    about.addActionListener((ActionEvent e) -> {
      String message = "<html><div style='width: 350px;'>" +
              "Our Java-based image processing application offers " +
              "a wide range of functionalities to help " +
              "users " + "manipulate their images with ease. Our application " +
              "supports several popular image " +
              "file formats, " +
              "including JPG, PNG, PPM, and BMP.<br><br>" +
              "Users can use our application to load their images and then " +
              "apply a range of filters to enhance" +
              " or " + "alter the image, including " +
              "brightening, darkening, adding sepia tones, blurring, " +
              "sharpening, " + "and converting to greyscale. In addition, our " +
              "application includes features " +
              "to flip images both " +
              "vertically and horizontally, as well as apply dithering effects " +
              "to the images.<br><br>" +
              "As the user applies filters, our application displays the image's " +
              "histogram in real-time, " +
              "providing valuable insights into the changes being made. " +
              "The application's intuitive and " +
              "interactive " +
              "GUI makes it easy for users to navigate the various " +
              "features and filters and view the changes " +
              "they " +
              "make to their images in real-time.<br><br>" +
              "Whether you're a professional photographer or simply " +
              "looking to enhance your personal photos, " +
              "our Java-based image processing application provides a powerful " +
              "yet user-friendly toolset to " +
              "help you achieve your goals." +
              "</div></html>";
      JOptionPane.showMessageDialog(null, message, "About Image Processor",
              JOptionPane.INFORMATION_MESSAGE);
    });
    fileSubmenu.add(about);

    JMenuItem help = new JMenuItem("Help");
    help.addActionListener((ActionEvent e) -> {
      String message = "<html><div style='width: 350px;'>"
              + "Welcome to the help section of the Java Image Processing Application. " +
              "Our application provides" +
              " various image processing features such as loading, " +
              "brightening, darkening, sepia, blurring, " +
              "sharpening, grayscale, saving, flipping vertically, " +
              "flipping horizontally, and dithering for " +
              "image files in JPG, PNG, PPM, and BMP format.<br><br>"
              + "To get started, please follow these instructions:<br><br>"
              + "<b>Loading an image:</b> Click on the \"Load Image from Files\" button " +
              "and select the image " +
              "file you want to load from your computer.<br><br>"
              + "<b>Image processing:</b> Select the desired image " +
              "processing feature from the available" +
              " options such as brighten, darken, sepia, blur, sharpen, grayscale, " +
              "flip vertically, flip" +
              " horizontally, " +
              "and dither. You can apply these filters to the " +
              "loaded image to modify it according to " +
              "your needs.<br><br>"
              + "<b>Histogram:</b> Our application also provides a histogram feature, " +
              "which shows the changes " +
              "in the pixel values of the image as you " +
              "apply the filters. The histogram can help you " +
              "understand " + "the impact of the filters on the image.<br><br>"
              + "<b>Saving the image:</b> Once you have applied the " +
              "desired filters to your image, you can save" +
              " it by clicking on the \"Save Image\" button.<br><br>"
              + "For further information, please refer to the " +
              "README and USEME files in the codebase. If you " +
              "encounter any issues or need further assistance, " +
              "please contact us at neema.r@northeastern.edu " +
              "and srivastava.pri@northeastern.edu.<br><br>"
              + "We hope you find our application user-friendly " +
              "and helpful for all your image processing " +
              "needs.</div></html>";
      JOptionPane.showMessageDialog(null, message, "Help",
              JOptionPane.INFORMATION_MESSAGE);
    });
    fileSubmenu.add(help);
    menu.add(fileSubmenu);
    this.setJMenuBar(menu);
  }

  /**
   * This helper method creates the initial button panel to which buttons for this program will be
   * added. It uses a simple box layout, with a set border on the left side of the screen.
   */
  private void buildButtonsJPanel() {
    featurePanel = new JPanel();
    featurePanel.setLayout(new GridLayout(0, 1, 10, 10));
    featurePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JLabel label = new JLabel("            LOAD AN IMAGE TO GET STARTED");
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    featurePanel.add(label);
    this.add(featurePanel, BorderLayout.WEST);
  }


  /**
   * This helper method creates a text entry area for entering batch commands line by line in the
   * view. It uses a JTextArea object to create the entry area, which is added to scrollable panel
   * so that the user can enter as much text as they like. The text area is added to the button
   * panel. It then creates a run button, that sends input to the controller so that it can process
   * the text into commands to be applied to the model.
   */
  private void buildLoadButton() {
    JLabel instructions = new JLabel("");
    instructions.setText("Use the buttons below to perform image manipulations");
    featurePanel.add(instructions);
    commandButton = new JButton("LOAD IMAGE FROM FILES");
    fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png", "bmp", "ppm");
    fileChooser.setFileFilter(filter);
    commandButton.addActionListener((ActionEvent e) -> {
      int result = fileChooser.showOpenDialog(ImgViewInteractiveImpl.this);
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();
        if (viewInput != null) {
          viewInput.accept("load " + filePath);
          isImageLoaded = true;
        }
      }
    });
    featurePanel.add(commandButton);
    buildALLButtons();
  }

  /**
   * Builds all the buttons for image processing and saving.
   * This method calls individual methods to build each button.
   */
  private void buildALLButtons() {
    buildBrightButton();
    buildVFlipButton();
    buildHFlipButton();
    buildSplitButton();
    buildCombineButton();
    buildGreyscaleButton();
    buildBlurButton();
    buildSepiaButton();
    buildSharpenButton();
    buildDitherButton();
    buildSaveButton();
  }

  /**
   * Builds a "Brighten Image" JButton and adds an ActionListener to it.
   * When clicked, it opens a dialog box asking the user to enter a brightness value.
   * If a valid integer value is entered, the ActionListener will call the "accept()" method
   * of the Consumer object stored in the viewInput field with the string "brighten "
   * followed by the entered brightness value as an argument. If no image is loaded,
   * an error message is displayed. If the entered value is not a valid integer,
   * an error message is displayed.
   */
  private void buildBrightButton() {
    commandButton = new JButton("BRIGHTEN IMAGE");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        JTextField brightnessField = new JTextField();
        Object[] inputs = {"Brightness value:", brightnessField};
        int result = JOptionPane.showConfirmDialog(null, inputs,
                "Enter brightness", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          try {
            int brightnessValue = Integer.parseInt(brightnessField.getText());
            if (viewInput != null) {
              viewInput.accept(("brighten " + brightnessValue));
            }
          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid integer value for brightness.");
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    this.featurePanel.add(commandButton);
  }

  /**
   * Builds a JButton for flipping an image vertically and adds it to the featurePanel.
   * If an image is loaded, the button triggers a vertical flip transformation on the image.
   * If no image is loaded, an error message is displayed.
   */
  private void buildVFlipButton() {
    commandButton = new JButton("FLIP IMAGE VERTICALLY");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        if (viewInput != null) {
          viewInput.accept("vertical-flip");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);

  }

  /**
   * Builds and adds a button to flip the loaded image horizontally.
   */
  private void buildHFlipButton() {
    commandButton = new JButton("FLIP IMAGE HORIZONTALLY");
    // fileChooser.setFileFilter(filter);
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        if (viewInput != null) {
          viewInput.accept("horizontal-flip");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);
  }

  /**
   * Creates and adds a horizontal flip button to the feature panel.
   * When clicked, this button flips the currently loaded image horizontally.
   * If no image is currently loaded, a message dialog is shown.
   */
  private void buildSplitButton() {
    commandButton = new JButton("SPLIT IMAGE INTO RGB");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        Object[] options = {"red", "green", "blue"};
        int result = JOptionPane.showOptionDialog(null,
                "Select the split Image to work on:", "Split Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                null);

        if (result >= 0) {
          String selectedOption = (String) options[result];
          if (viewInput != null) {
            viewInput.accept("rgb-split " + selectedOption);
            isSplit = true;
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);

  }

  /**
   * Builds and adds a "COMBINE IMAGE FROM RGB" button to the feature panel.
   * When the button is clicked, it checks if image has been loaded and split into RGB components.
   * If so, it sends a message to the viewInput object with the command "rgb-combine"
   * to combine the RGB components.
   * If not, it displays an error message.
   */
  private void buildCombineButton() {
    commandButton = new JButton("COMBINE IMAGE FROM RGB");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        if (isSplit) {
          if (viewInput != null) {
            viewInput.accept("rgb-combine");
          }
        } else {
          JOptionPane.showMessageDialog(null, "Image Not Split");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);
  }

  /**
   * Builds a JButton for applying a greyscale filter to an image.
   */
  private void buildGreyscaleButton() {
    commandButton = new JButton("GREYSCALE IMAGE");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        Object[] options = {
            "red-component", "green-component", "blue-component","value-component",
            "intensity-component", "luma-component"
        };
        int result = JOptionPane.showOptionDialog(null,
                "Select a component to apply greyscale filter:", "Greyscale Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);

        if (result >= 0) {
          String selectedOption = (String) options[result];
          if (viewInput != null) {
            viewInput.accept("greyscale " + selectedOption);
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);
  }

  /**
   * This method builds a button to apply blur effect to an image. The button is added to a panel.
   */
  private void buildBlurButton() {
    commandButton = new JButton("BLUR IMAGE");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        if (viewInput != null) {
          viewInput.accept("blur");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);
  }

  /**
   * This method builds button to apply sharpened effect to an image. The button is added to panel.
   */
  private void buildSharpenButton() {
    commandButton = new JButton("SHARPEN IMAGE");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        if (viewInput != null) {
          viewInput.accept("sharpen");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);
  }

  /**
   * This method builds a button to apply sepia effect to an image. The button is added to a panel.
   */
  private void buildSepiaButton() {
    commandButton = new JButton("SEPIA IMAGE");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        if (viewInput != null) {
          viewInput.accept("sepia");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);
  }

  /**
   * This method builds a button to apply dither effect to an image. The button is added to a panel.
   */
  private void buildDitherButton() {

    commandButton = new JButton("DITHER IMAGE");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        if (viewInput != null) {
          viewInput.accept("dither");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);
  }

  /**
   * This method builds a button to save the current image. The button is added to a panel.
   */
  private void buildSaveButton() {
    commandButton = new JButton("SAVE IMAGE");
    commandButton.addActionListener((ActionEvent e) -> {
      if (isImageLoaded) {
        int returnVal = fileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          String filePath = file.getAbsolutePath();
          if (viewInput != null) {
            viewInput.accept("save " + filePath);
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Image Not Loaded");
      }
    });
    featurePanel.add(commandButton);
  }

  /**
   * This method builds a button to clear out the current image. The button is added to a panel.
   */
  private void buildClearButton() {
    commandButton = new JButton("CLEAR IMAGE PANE AND HISTOGRAM");
    commandButton.addActionListener((ActionEvent e) -> {
      if (viewInput != null) {
        viewInput.accept("clear");
        imageLabel.setIcon(null);
        imageLabelHist.setIcon(null);
      }
    });
    featurePanel.add(commandButton);
  }


  /**
   * This helper method creates a quit button. Just like the exit button on the top of the menu,
   * this button allows the user to quit the program by simply pushing a button.
   * It uses system exit with a status of 0 since no error is reported.
   */
  private void buildQuitButton() {
    JButton quitButton = new JButton("QUIT PROGRAM");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    featurePanel.add(quitButton);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void getViewInput(Consumer<String> callback) {
    viewInput = callback;
  }

  @Override
  public void setImage(int[][][] image) {
    try {
      BufferedImage bufferedImage = ImgUtil.getBufferedImage(image);
      ImageIcon icon = new ImageIcon(bufferedImage);
      imageLabel.setIcon(icon);
      imageLabel.setHorizontalAlignment(JLabel.CENTER);
    } catch (IOException e) {
      this.displayErrors(e.toString());
    }
  }

  @Override
  public void setHistogram(int[][] histImage) {
    this.histImage = histImage;
    calculateHistogram();
    display();
  }

  /**
   * Calculates the histogram of the image by extracting the red, green, blue,
   * and intensity histograms from the histImage array and storing them in corresponding variables.
   */
  private void calculateHistogram() {
    redHistogram = histImage[0];
    greenHistogram = histImage[1];
    blueHistogram = histImage[2];
    intensityHistogram = histImage[3];
  }

  /**
   * Displays a histogram of an image's red, green, blue, and intensity values.
   */
  public void display() {
    BufferedImage histogramImage = new BufferedImage(600, 600,
            BufferedImage.TYPE_INT_RGB);
    Graphics2D g = histogramImage.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int panelWidth = 600;
    int panelHeight = 600;
    int barWidth = panelWidth / BINS;
    int w = panelWidth - 100;
    int h = panelHeight - 100;
    int x = 50;
    int y = 50;
    int maxCount = Math.max(
            Math.max(getMax(redHistogram), getMax(greenHistogram)),
            Math.max(getMax(blueHistogram), getMax(intensityHistogram)));

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 600, 600);
    g.setColor(Color.RED);
    drawLineChart(g, redHistogram, barWidth, panelHeight, maxCount, x, y, w, h);
    g.setColor(Color.GREEN);
    drawLineChart(g, greenHistogram, barWidth, panelHeight, maxCount, x, y, w, h);
    g.setColor(Color.BLUE);
    drawLineChart(g, blueHistogram, barWidth, panelHeight, maxCount, x, y, w, h);
    g.setColor(Color.black);
    drawLineChart(g, intensityHistogram, barWidth, panelHeight, maxCount, x, y, w, h);
    g.setColor(Color.BLACK);
    g.drawLine(x, y + h, x + w, y + h);
    g.drawLine(x, y, x, y + h);
    g.drawString("0", x - 5, y + h + 20);
    for (int i = 0; i <= 255; i += 51) {
      int tickX = x + (i * w / 255);
      g.drawLine(tickX, y + h, tickX, y + h + 5);
      g.drawString(String.valueOf(i), tickX - 6, y + h + 20);
    }
    g.drawString("Pixel Values", x + 225, y + h + 35);
    int maxVal = Integer.MIN_VALUE;
    for (int[] ints : histImage) {
      for (int anInt : ints) {
        if (anInt > maxVal) {
          maxVal = anInt;
        }
      }
    }
    int divisionSize = maxVal / 4;
    for (int i = 1; i <= 4; i++) {
      int yPos = h - (i * (h - y) / 4);
      String label = String.valueOf(i * divisionSize);
      g.drawString(label, x - 50, yPos + 5);
    }
    drawLegend(g, 250, y);
    g.rotate(-Math.PI / 2, x, y);
    g.drawString("Frequency", x - 100, y - 5);

    g.dispose();
    imageLabelHist.setIcon(new ImageIcon(histogramImage));
    imageLabelHist.setMinimumSize(new Dimension(10, 10));
  }

  /**
   * Draws a line chart on the given Graphics2D object, representing the given histogram data.
   * The chart will be drawn within the specified rectangle with given bar width and panel height.
   *
   * @param g           The Graphics2D object to draw the chart on.
   * @param histogram   An array of integers representing the histogram data.
   * @param barWidth    The width of each bar in the chart.
   * @param panelHeight The height of the panel to draw the chart on.
   * @param maxCount    The maximum count value in the histogram data.
   * @param x           The x coordinate of the top-left corner of the chart rectangle.
   * @param y           The y coordinate of the top-left corner of the chart rectangle.
   * @param w           The width of the chart rectangle.
   * @param h           The height of the chart rectangle.
   */
  private void drawLineChart(Graphics2D g, int[] histogram, int barWidth,
                             int panelHeight, int maxCount, int x, int y, int w, int h) {
    Stroke oldStroke = g.getStroke();
    g.setStroke(new BasicStroke(2));

    int x1 = x;
    int y1 = y + h - (int) ((double) histogram[0] / maxCount * h);
    for (int i = 1; i < BINS; i++) {
      int x2 = x + i * barWidth;
      int y2 = y + h - (int) ((double) histogram[i] / maxCount * h);
      g.drawLine(x1, y1, x2, y2);
      x1 = x2;
      y1 = y2;
    }
    g.setStroke(oldStroke);
  }

  /**
   * Draws a legend with color boxes and labels on the given Graphics2D object at the
   * given x and y coordinates. The legend consists of color boxes representing colors and
   * corresponding labels next to them. The color boxes are of size 20x20 and are separated
   * from the labels by a margin of 10. The legend starts with the label "Legend:" at the given
   * coordinates and proceeds with the color boxes and labels.
   *
   * @param g the Graphics2D object to draw on
   * @param x the x coordinate of the top left corner of the legend
   * @param y the y coordinate of the top left corner of the legend
   */
  private void drawLegend(Graphics2D g, int x, int y) {
    int colorBoxSize = 20;
    int boxMargin = 5;
    int labelMargin = 10;
    g.setFont(new Font("Arial", Font.PLAIN, 14));
    g.drawString("Legend:", x, y);
    int redBoxX = x;
    int redBoxY = y + boxMargin + g.getFontMetrics().getHeight();
    g.setColor(Color.RED);
    g.fillRect(redBoxX, redBoxY, colorBoxSize, colorBoxSize);
    g.setColor(Color.BLACK);
    g.drawString("Red", redBoxX + colorBoxSize + labelMargin,
            redBoxY + colorBoxSize / 2);
    int greenBoxX = x;
    int greenBoxY = redBoxY + colorBoxSize + boxMargin;
    g.setColor(Color.GREEN);
    g.fillRect(greenBoxX, greenBoxY, colorBoxSize, colorBoxSize);
    g.setColor(Color.BLACK);
    g.drawString("Green", greenBoxX + colorBoxSize + labelMargin,
            greenBoxY + colorBoxSize / 2);
    int blueBoxX = x;
    int blueBoxY = greenBoxY + colorBoxSize + boxMargin;
    g.setColor(Color.BLUE);
    g.fillRect(blueBoxX, blueBoxY, colorBoxSize, colorBoxSize);
    g.setColor(Color.BLACK);
    g.drawString("Blue", blueBoxX + colorBoxSize + labelMargin,
            blueBoxY + colorBoxSize / 2);
    int intensityBoxX = x;
    int intensityBoxY = blueBoxY + colorBoxSize + boxMargin;
    g.setColor(Color.BLACK);
    g.fillRect(intensityBoxX, intensityBoxY, colorBoxSize, colorBoxSize);
    g.setColor(Color.BLACK);
    g.drawString("Intensity", intensityBoxX + colorBoxSize + labelMargin,
            intensityBoxY + colorBoxSize / 2);
  }

  /**
   * Returns the maximum value in the given integer array.
   *
   * @param array the integer array to find the maximum value from
   * @return the maximum value in the array
   */
  private int getMax(int[] array) {
    int max = 0;
    for (int i : array) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void displayErrors(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }
}
