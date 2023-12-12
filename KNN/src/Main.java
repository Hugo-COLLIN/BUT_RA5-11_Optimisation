import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> labels = manipulateDataLabels("KNN/data/train-labels.idx1-ubyte", 1000);
        List<Imagette> imagettes = manipulateDataImages("KNN/data/train-images.idx3-ubyte", labels, 1000, true);
        Donnees donnees = new Donnees(imagettes);

        AlgoClassification plusProche = new PlusProche(donnees);


        List<Integer> labelsTest = manipulateDataLabels("KNN/data/t10k-labels.idx1-ubyte", 1000);
        List<Imagette> imagettesTest = manipulateDataImages("KNN/data/t10k-images.idx3-ubyte", labels, 1000, false);

        compareTest(plusProche, imagettesTest, labelsTest);

//        compareTest(plusProche);

    }

    private static void compareTest(AlgoClassification plusProche, List<Imagette> imagettesTest, List<Integer> labelsTest) {
        int nbCorrect = 0;
        for (int i = 0 ; i < 1000 ; i ++)
        {
            int prediction = plusProche.predire(imagettesTest.get(i));
            if (prediction == labelsTest.get(i))
                nbCorrect ++;
        }

        System.out.println("nbCorrect = " + nbCorrect);
    }

    private static List<Integer> manipulateDataLabels(String dataset, int nbImages) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(dataset));

        //lire numero magique
        int magicNumber = dis.readInt();

        //lire nb images
        int numberOfElements = dis.readInt();


        List<Integer> labels = new ArrayList<>();
        //pour chaque imagette
        for (int i = 0; i < nbImages; i++)
            //creer imagette
            labels.add(dis.readUnsignedByte());


        System.out.println(labels.get(0));
        System.out.println(labels.get(nbImages-1));

        //fermer flux
        dis.close();

        return labels;
    }

    private static List<Imagette> manipulateDataImages(String dataset, List<Integer> labels, int nbImages, boolean export) throws IOException {
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
        for (int i = 0; i < nbImages; i++) {
            //creer imagette
            pixels = new int[numberOfRows][numberOfColumns];

            //pour chaque ligne
            for (int row = 0; row < numberOfRows; row++)
                //pour chaque col
                for (int col = 0; col < numberOfRows; col++)
                    //lire octet (unsigned) readunsignedint
                    pixels[row][col] = dis.readUnsignedByte();

            //mettre dans imagette //ajoute tableau imagette
            imagettes.add(new Imagette(pixels, labels.get(i)));
        }

        if (export)
        {
            //retourner tab imagette
            imagettes.get(0).saveToDisk("saved/1");
            imagettes.get(nbImages - 1).saveToDisk("saved/" + (nbImages - 1));
        }


        //fermer flux
        dis.close();
        return imagettes;
    }
}