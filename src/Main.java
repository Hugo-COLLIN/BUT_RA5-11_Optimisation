import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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

public class Main {
    public static <List> void main(String[] args) throws IOException {
        // open datainputstr
        DataInputStream dis = new DataInputStream(new FileInputStream("data/t10k-images.idx3-ubyte"));

        //lire numero magique
        int magicNumber = dis.readInt();

        //lire nb images
        int numberOfImages = dis.readInt();

        //cree tableau imagette
        int[][] pixels;

        //lire lignes
        int numberOfRows = dis.readInt();

        //lire col
        int numberOfColumns = dis.readInt();


        ArrayList<Imagette> imagettes = new ArrayList<>();
        //pour chaque imagette
        for (int i = 0; i < numberOfImages; i++) {
            //creer imagette
            pixels = new int[numberOfRows][numberOfColumns];

            //pour chaque ligne
            for (int row = 0; row < numberOfRows; row++)
                //pour chaque col
                for (int col = 0; col < numberOfRows; col++)
                    //lire octet (unsigned) readunsignedint
                    pixels[row][col] = dis.readUnsignedByte();

            //mettre dans imagette //ajoute tableau imagette
            imagettes.add(new Imagette(pixels));
        }

        //retourner tab imagette
        imagettes.get(0).saveToDisk("saved/1");
        imagettes.get(numberOfImages - 1).saveToDisk("saved/" + (numberOfImages - 1));


        //fermer flux
        dis.close();

    }
}