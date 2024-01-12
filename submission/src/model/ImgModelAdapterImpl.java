package model;

import exception.ValidCheckedException;

import java.io.IOException;
import java.util.Map;

/**
 * The ImgModelAdapterImpl class is an implementation of the ImgModelAdaptive interface,
 * which adapts an existing ImgModel object to the interface. It provides a way to perform
 * image analysis using the ImgModel object.
 */
public class ImgModelAdapterImpl implements ImgModelAdaptive {
  private final ImgModel model;
  private Image image;

  /**
   * Constructs an instance of ImgModelAdapterImpl using the provided ImgModel object.
   *
   * @param model The ImgModel object to be used by the adapter.
   */
  public ImgModelAdapterImpl(ImgModel model) {
    this.model = model;
  }

  @Override
  public Image savePPM(String filePath, String filename) throws
          IOException, ValidCheckedException {
    image = model.savePPM(filePath, filename);
    return image;
  }

  @Override
  public Image applyBrighten(int value, String filename, String newFilename)
          throws ValidCheckedException {
    model.applyBrighten(value, filename, newFilename);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(newFilename).length;
    int width = newMap.get(newFilename)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(newFilename));
    return image;
  }

  @Override
  public Image applyVFlip(String filename, String newFilename)
          throws ValidCheckedException {
    model.applyVFlip(filename, newFilename);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(newFilename).length;
    int width = newMap.get(newFilename)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(newFilename));
    return image;
  }

  @Override
  public Image applyHFlip(String filename, String newFilename) throws ValidCheckedException {
    model.applyHFlip(filename, newFilename);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(newFilename).length;
    int width = newMap.get(newFilename)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(newFilename));
    return image;
  }

  @Override
  public Image applyGreyscaleComponent(String component, String filename, String newFilename)
          throws ValidCheckedException {
    model.applyGreyscaleComponent(component, filename, newFilename);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(newFilename).length;
    int width = newMap.get(newFilename)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(newFilename));
    return image;
  }

  @Override
  public Image applyRGBSplit(String filename, String redImage,
                             String greenImage, String blueImage) throws ValidCheckedException {
    model.applyRGBSplit(filename, redImage, greenImage, blueImage);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(redImage).length;
    int width = newMap.get(redImage)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(redImage));
    return image;
  }

  @Override
  public Image applyRGBCombine(String filename, String redImage,
                               String greenImage, String blueImage) throws ValidCheckedException {
    model.applyRGBCombine(filename, redImage, greenImage, blueImage);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(filename).length;
    int width = newMap.get(filename)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(filename));
    return image;
  }

  @Override
  public Image load(String filename, String alias, Image newImage)
          throws IOException, ValidCheckedException {
    model.load(filename, alias, newImage);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(alias).length;
    int width = newMap.get(alias)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(alias));
    return image;
  }

  @Override
  public Image applyBlur(String fileName, String alias)
          throws IllegalArgumentException, ValidCheckedException {
    model.applyBlur(fileName, alias);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(alias).length;
    int width = newMap.get(alias)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(alias));
    return image;
  }

  @Override
  public Image applySharpen(String fileName, String alias)
          throws IllegalArgumentException, ValidCheckedException {
    model.applySharpen(fileName, alias);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(alias).length;
    int width = newMap.get(alias)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(alias));
    return image;
  }

  @Override
  public Image transformGreyscale(String fileName, String alias) throws ValidCheckedException {
    model.transformGreyscale(fileName, alias);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(alias).length;
    int width = newMap.get(alias)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(alias));
    return image;
  }

  @Override
  public Image sepiaTransform(String fileName, String alias) throws ValidCheckedException {
    model.sepiaTransform(fileName, alias);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(alias).length;
    int width = newMap.get(alias)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(alias));
    return image;
  }

  @Override
  public Image ditherTransform(String fileName, String alias) throws ValidCheckedException {
    model.ditherTransform(fileName, alias);
    Map<String, int[][][]> newMap = model.getImageMap();
    int height = newMap.get(alias).length;
    int width = newMap.get(alias)[0].length;
    int maxValue = model.getMaxValue();
    image = new ImageImpl(height, width, maxValue, newMap.get(alias));
    return image;
  }

  @Override
  public int[][] getHistogram(String filename) throws ValidCheckedException {
    model.createHistogram(filename);
    Map<String, int[][]> newMap = model.getHistImage();
    int[][] newHist = newMap.get(filename);
    return newHist;
  }
}
