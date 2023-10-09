import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
//        manipulateDataImages("data/t10k-images.idx3-ubyte");

        manipulateDataLabels("data/train-labels.idx1-ubyte");

    }

    private static void manipulateDataLabels(String dataset) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(dataset));

        //lire numero magique
        int magicNumber = dis.readInt();

        //lire nb images
        int numberOfElements = dis.readInt();


        List<Integer> labels = new ArrayList<>();
        //pour chaque imagette
        for (int i = 0; i < numberOfElements; i++)
            //creer imagette
            labels.add(dis.readUnsignedByte());



        if (labels.get(0) == 5 && labels.get(numberOfElements-1) == 8)
            System.out.println("Correct");
        else System.out.println("Incorrect");

        //fermer flux
        dis.close();
    }

    private static void manipulateDataImages(String dataset) throws IOException {
        // open datainputstr
        DataInputStream dis = new DataInputStream(new FileInputStream(dataset));

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