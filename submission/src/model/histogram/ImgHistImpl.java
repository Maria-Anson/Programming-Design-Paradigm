package model.histogram;

import model.Image;

/**
 * The ImgHistImpl class implements the ImgHist interface to calculate histograms
 * for an Image object. This class calculates separate histograms for red, green,
 * blue, and overall intensity values of each pixel in the image. The number of bins
 * for each histogram is set to 256.
 */
public class ImgHistImpl implements ImgHist {
  private static final int BINS = 256;

  @Override
  public int[][] calculateHistogram(Image image) {
    int[] redHistogram = new int[BINS];
    int[] greenHistogram = new int[BINS];
    int[] blueHistogram = new int[BINS];
    int[] intensityHistogram = new int[BINS];
    int[][][] imageData = image.getImage();

    for (int x = 0; x < imageData.length; x++) {
      for (int y = 0; y < imageData[0].length; y++) {
        int red = imageData[x][y][0];
        int green = imageData[x][y][1];
        int blue = imageData[x][y][2];

        redHistogram[red]++;
        greenHistogram[green]++;
        blueHistogram[blue]++;
        intensityHistogram[(red + green + blue) / 3]++;
      }
    }

    int[][] allHistograms = new int[4][BINS];
    allHistograms[0] = redHistogram;
    allHistograms[1] = greenHistogram;
    allHistograms[2] = blueHistogram;
    allHistograms[3] = intensityHistogram;

    return allHistograms;
  }
}
