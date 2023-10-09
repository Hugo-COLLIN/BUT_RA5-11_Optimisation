import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Imagette {
    int[][] pixels;

    Imagette(int[][] pixels) {
        this.pixels = pixels;
    }

    void saveToDisk(String filename) throws IOException {
        int width = this.pixels[0].length;
        int height = this.pixels.length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int row = 0; row < height; row ++)
            for (int col = 0; col < width; col ++) {
                int rgb = this.pixels[col][row];
                image.setRGB(row, col, rgb*256*256+rgb*256+rgb);
            }

        ImageIO.write(image, "png", new File(filename + ".png"));
    }
}