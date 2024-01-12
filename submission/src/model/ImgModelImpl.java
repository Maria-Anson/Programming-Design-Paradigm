package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import exception.ValidCheckedException;
import model.filter.ImgFilter;
import model.filter.ImgFilterImpl;
import model.histogram.ImgHist;
import model.histogram.ImgHistImpl;
import model.transform.ImgTransform;
import model.transform.ImgTransformImpl;
import util.ImgUtil;


/**
 * This class implements the ImgModel interface and provides functionality to work with images.
 * It includes methods to process and manipulate images, as well as
 * apply filters and transformations.
 */
public class ImgModelImpl implements ImgModel {
  private static int height;
  private static int width;
  private static int maxValue;
  private final Map<String, int[][][]> imageMap = new HashMap<>();
  private final Map<String, int[][]> histImage = new HashMap<>();
  private final ImgFilter im = new ImgFilterImpl();
  private final ImgTransform imNew = new ImgTransformImpl();
  private final ImgUtil img = new ImgUtil();
  private Image newImage;

  @Override
  public int getMaxValue() {
    return maxValue;
  }

  @Override
  public Map<String, int[][][]> getImageMap() {
    return imageMap;
  }

  @Override
  public Map<String, int[][]> getHistImage() {
    return histImage;
  }

  @Override
  public int[][][] deepCopy(String filename) {
    int[][][] temp = imageMap.get(filename);
    int[][][] output = new int[temp.length][temp[0].length][3];
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[0].length; j++) {
        output[i][j][0] = imageMap.get(filename)[i][j][0];
        output[i][j][1] = imageMap.get(filename)[i][j][1];
        output[i][j][2] = imageMap.get(filename)[i][j][2];
      }
    }
    return output;
  }

  @Override
  public void applyBrighten(int value, String fileName, String newFilename)
          throws NullPointerException, ValidCheckedException {
    int[][][] source;
    try {
      source = deepCopy(fileName);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + fileName);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
    int row;
    int col;
    int[][][] newResult = new int[source.length][source[0].length][3];
    for (int i = 0; i < source[0].length * source.length; i++) {
      row = i / source[0].length;
      col = i % source[0].length;
      int r = source[row][col][0] + value;
      int g = source[row][col][1] + value;
      int b = source[row][col][2] + value;

      newResult[row][col][0] = r < 0 ? 0 : r > maxValue ? maxValue : r;
      newResult[row][col][1] = g < 0 ? 0 : g > maxValue ? maxValue : g;
      newResult[row][col][2] = b < 0 ? 0 : b > maxValue ? maxValue : b;
    }
    imageMap.put(newFilename, newResult);
  }

  @Override
  public void applyVFlip(String filename, String newFilename) throws ValidCheckedException {
    int[][][] source;
    try {
      source = deepCopy(filename);
      int height = source.length;
      for (int i = 0; i < height / 2; i++) {
        int[][] tempRow = source[i];
        source[i] = source[height - i - 1];
        source[height - i - 1] = tempRow;
      }
      imageMap.put(newFilename, source);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + filename);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
  }

  @Override
  public void applyHFlip(String filename, String newFilename)
          throws IllegalArgumentException, ValidCheckedException {
    int[][][] source;
    try {
      source = deepCopy(filename);
      int height = source.length;
      int width = source[0].length;
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width / 2; j++) {
          int[] tempPixel = source[i][j];
          source[i][j] = source[i][width - j - 1];
          source[i][width - j - 1] = tempPixel;
        }
      }
      imageMap.put(newFilename, source);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + filename);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
  }

  @Override
  public void applyRGBSplit(String filename, String redImage, String greenImage, String blueImage)
          throws ValidCheckedException {
    int[][][] source;
    try {
      source = deepCopy(filename);
      int[][][] redChannel = new int[source.length][source[0].length][3];
      int[][][] greenChannel = new int[source.length][source[0].length][3];
      int[][][] blueChannel = new int[source.length][source[0].length][3];

      for (int i = 0; i < source.length; i++) {
        for (int j = 0; j < source[0].length; j++) {
          int red = source[i][j][0];
          int green = source[i][j][1];
          int blue = source[i][j][2];

          redChannel[i][j] = new int[]{red, red, red};
          greenChannel[i][j] = new int[]{green, green, green};
          blueChannel[i][j] = new int[]{blue, blue, blue};
        }
      }

      imageMap.put(redImage, redChannel);
      imageMap.put(greenImage, greenChannel);
      imageMap.put(blueImage, blueChannel);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + filename);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
  }

  @Override
  public void applyRGBCombine(String filename, String redImage,
                              String greenImage, String blueImage)
          throws IllegalArgumentException, ValidCheckedException {
    int[][][] redSource;
    int[][][] greenSource;
    int[][][] blueSource;
    try {
      redSource = imageMap.get(redImage);
      greenSource = imageMap.get(greenImage);
      blueSource = imageMap.get(blueImage);
      int[][][] rgbImage = new int[redSource.length][redSource[0].length][3];
      for (int i = 0; i < redSource.length; i++) {
        for (int j = 0; j < redSource[0].length; j++) {
          rgbImage[i][j][0] = redSource[i][j][0];
          rgbImage[i][j][1] = greenSource[i][j][1];
          rgbImage[i][j][2] = blueSource[i][j][2];
        }
      }

      imageMap.put(filename, rgbImage);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + filename);
    }
    if (redSource == null || greenSource == null || blueSource == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
  }

  @Override
  public void applyGreyscaleComponent(String component, String filename, String newFilename)
          throws ValidCheckedException {
    int[][][] source;
    try {
      source = deepCopy(filename);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + filename);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
    int[][][] result = new int[source.length][source[0].length][3];

    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        int red = source[i][j][0];
        int green = source[i][j][1];
        int blue = source[i][j][2];

        int grey;
        if (component.equals("red-component")) {
          grey = red;
        } else if (component.equals("green-component")) {
          grey = green;
        } else if (component.equals("blue-component")) {
          grey = blue;
        } else if (component.equals("value-component")) {
          grey = Math.max(Math.max(red, green), blue);
        } else if (component.equals("intensity-component")) {
          grey = (red + green + blue) / 3;
        } else if (component.equals("luma-component")) {
          double luma = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
          grey = (int) luma;
        } else {
          throw new ValidCheckedException("invalid component");
        }
        result[i][j][0] = grey;
        result[i][j][1] = grey;
        result[i][j][2] = grey;
      }
    }

    imageMap.put(newFilename, result);
  }


  @Override
  public void applyBlur(String fileName, String alias)
          throws IllegalArgumentException, ValidCheckedException {
    //checkImage();
    int[][][] source;
    try {
      source = deepCopy(fileName);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + fileName);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
    int[][][] result = new int[source.length][source[0].length][3];
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        result[i][j][0] = source[i][j][0];
        result[i][j][1] = source[i][j][1];
        result[i][j][2] = source[i][j][2];
      }
    }
    double[][] blurFilter = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    result = im.filterApplication(result, blurFilter, imageMap);
    imageMap.put(alias, result);
  }

  @Override
  public void applySharpen(String fileName, String alias)
          throws IllegalArgumentException, ValidCheckedException {
    int[][][] source;
    try {
      source = deepCopy(fileName);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + fileName);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
    int[][][] result = new int[source.length][source[0].length][3];
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        result[i][j][0] = source[i][j][0];
        result[i][j][1] = source[i][j][1];
        result[i][j][2] = source[i][j][2];
      }
    }
    double[][] sharpenFilter = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}
    };
    result = im.filterApplication(result, sharpenFilter, imageMap);
    imageMap.put(alias, result);
  }

  @Override
  public void transformGreyscale(String fileName, String alias) throws ValidCheckedException {
    this.applyGreyscaleComponent("luma-component", fileName, alias);
  }

  @Override
  public void sepiaTransform(String fileName, String alias) throws ValidCheckedException {
    int[][][] source;
    try {
      source = deepCopy(fileName);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + fileName);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
    int[][][] result = new int[source.length][source[0].length][3];
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        result[i][j][0] = source[i][j][0];
        result[i][j][1] = source[i][j][1];
        result[i][j][2] = source[i][j][2];
      }
    }
    double[][] sepia = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    //undoHelper();
    result = imNew.transformImpl(result, sepia, img);
    imageMap.put(alias, result);
  }

  @Override
  public void ditherTransform(String fileName, String alias) throws ValidCheckedException {
    this.applyGreyscaleComponent("luma-component", fileName, alias);
    int[][][] source;
    try {
      source = deepCopy(fileName);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + fileName);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
    int[][][] result = new int[source.length][source[0].length][3];
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        result[i][j][0] = source[i][j][0];
        result[i][j][1] = source[i][j][1];
        result[i][j][2] = source[i][j][2];
      }
    }
    result = im.filterDitherApplication(result, img);
    imageMap.put(alias, result);
  }

  @Override
  public void createHistogram(String fileName) throws ValidCheckedException {
    int[][][] source;
    try {
      source = deepCopy(fileName);
    } catch (NullPointerException e) {
      throw new ValidCheckedException("Error: Cannot find " + fileName);
    }
    if (source == null) {
      throw new ValidCheckedException("Error: Image is null");
    }
    int[][][] result = new int[source.length][source[0].length][3];
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        result[i][j][0] = source[i][j][0];
        result[i][j][1] = source[i][j][1];
        result[i][j][2] = source[i][j][2];
      }
    }
    height = result.length;
    width = result[0].length;
    maxValue = getMaxValue();
    newImage = new ImageImpl(height, width, maxValue, result);
    ImgHist newHist = new ImgHistImpl();
    int[][] hist = newHist.calculateHistogram(newImage);
    histImage.put(fileName, hist);
  }


  @Override
  public void load(String filename, String alias, Image newImage) throws IOException,
          ValidCheckedException {
    this.newImage = newImage;
    height = newImage.getHeight();
    width = newImage.getWidth();
    maxValue = newImage.getMaxValue();
    imageMap.put(alias, newImage.getImage());
  }

  @Override
  public Image savePPM(String filePath, String fileName) {
    int[][][] temp = deepCopy(fileName);
    newImage = new ImageImpl(temp[0].length, temp.length, maxValue, temp);
    return newImage;
  }
}