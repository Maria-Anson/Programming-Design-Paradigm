import java.util.Map;

import model.Image;
import model.ImgModel;

/**
 * This class represents a MockingImgModel, which is an implementation of the ImgModel interface.
 * It logs any image processing operation performed on the image using a StringBuilder.
 */
public class MockingImgModel implements ImgModel {

  private final StringBuilder logger;
  private Image image;

  /**
   * Creates a new instance of MockingImgModel with an empty StringBuilder for logging image
   * processing operations.
   */
  public MockingImgModel() {
    this.logger = new StringBuilder();
  }

  @Override
  public void load(String filename, String alias, Image newImage) {
    return;
  }

  @Override
  public void applyBlur(String fileName, String alias) throws IllegalArgumentException {
    logger.append(fileName + " Blur successfully and named as " + alias + " ");

  }

  @Override
  public void applySharpen(String fileName, String alias) throws IllegalArgumentException {
    logger.append(fileName + " Sharpen successfully and named as " + alias + " ");

  }

  @Override
  public void transformGreyscale(String fileName, String alias) {
    logger.append(fileName + " Transformed to Greyscale successfully and named as " + alias + " ");
  }

  @Override
  public void sepiaTransform(String fileName, String alias) {
    logger.append(fileName + " Sepia successfully and named as " + alias + " ");
  }

  @Override
  public void ditherTransform(String fileName, String alias) {
    logger.append(fileName + " Dither successfully and named as " + alias + " ");
  }

  @Override
  public void createHistogram(String fileName) {
    //used to create histogram.
  }

  @Override
  public Map<String, int[][][]> getImageMap() {
    return null;
  }

  @Override
  public Map<String, int[][]> getHistImage() {
    return null;
  }

  @Override
  public int getMaxValue() {
    return 0;
  }

  @Override
  public void applyBrighten(int value, String filename, String newFilename) {
    logger.append(filename + " Brightened successfully and named as " + newFilename + " ");
  }

  @Override
  public void applyHFlip(String filename, String newFilename) {
    logger.append(filename + " Flipped Horizontally successfully and named as "
            + newFilename + " ");
  }

  @Override
  public void applyRGBSplit(String filename, String redImage, String greenImage, String blueImage) {
    logger.append(filename + " RGB Split successfully done ");
  }

  @Override
  public int[][][] deepCopy(String filename) {
    return new int[0][][];
  }

  @Override
  public Image savePPM(String filePath, String filename) {
    logger.append(filename + " successfully saved to path " + filePath + " ");
    return image;
  }

  @Override
  public void applyRGBCombine(String newFilename, String redImage, String greenImage,
                              String blueImage) {
    logger.append("RGB Combined successfully and named as " + newFilename + " ");
  }

  @Override
  public void applyGreyscaleComponent(String component, String filename, String newFilename) {
    logger.append(filename + " Greyscale successfully done and named as " + newFilename + " ");
  }

  @Override
  public void applyVFlip(String filename, String newFilename) {
    logger.append(filename + " Flipped Vertically successfully and named as " + newFilename + " ");
  }

  @Override
  public String toString() {
    return "" + logger;
  }
}
