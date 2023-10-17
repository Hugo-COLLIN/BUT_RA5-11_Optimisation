package chargement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * classe qui charge un CSV et transmet une liste de rawdata
 */
public class Chargement {

    /**
     * nom du fichier à charger
     */
    String nomFichier;

    /**
     * le separateur de colonne dans le fichier csv
     */
    String separateur = ";";


    /**
     * constructeur d'objet chargement
     *
     * @param nom nom du fichier a charger
     */
    public Chargement(String nom) {
        this.nomFichier = nom;
    }


    /**
     * permet de charger le fichier csv sous forme de liste de rawdata
     * <li> la premiere ligne du fichier doit etre la liste des attributs </li>
     *
     * @return liste de rawdata
     * @throws IOException probleme de lecture / sauvegarde
     */
    public List<Data> chargerFichier() throws IOException {

        // liste des elements
        List<Data> elements = new ArrayList<>();

        // ouverture du fichier
        BufferedReader bf = new BufferedReader(new FileReader(this.nomFichier));

        // chargement de la liste des clefs (correspond à la premiere ligne)
        String clefsString = bf.readLine();
        // genere la liste des clefs
        List<String> clefs = this.analyserClef(clefsString);

        // parcours du fichier
        String ligne = bf.readLine();

        // pour chaque ligne
        while (ligne != null) {
            // creer et ajouter rawdata dans la liste
            Data item = this.creerRawData(ligne, clefs);
            elements.add(item);
            ligne = bf.readLine();
        }

        // fermeture fichier
        bf.close();

        return elements;
    }

    /**
     * creer un RawData a partir de la liste des clefs et de la ligne
     *
     * @param ligne ligne sous forme de chaine
     * @param clefs liste des clefs
     * @return rawdata correspondant
     */
    private Data creerRawData(String ligne, List<String> clefs) {
        // creer rawdata
        Data data = new Data();

        // split la ligne
        String[] attributs = ligne.split(this.separateur);

        // si pas la bonne taille -> leve erreur
        if (attributs.length > clefs.size())
            throw new RuntimeException("le nombre de colonnes de la ligne n'est pas le bon");

        // pour chaque element de la ligne
        for (int i = 0; i < attributs.length; i++) {
            // ajoute le lien clef -> valeur
            data.ajouterAttribut(clefs.get(i),attributs[i]);
        }

        // retourne la data
        return data;
    }

    /**
     * permet de generer la liste des clefs à partir du fichier initial
     *
     * @param clefsString la chaine representant la liste des clefs
     * @return la liste des clefs sous forme de liste de strings
     */
    private List<String> analyserClef(String clefsString) {
        // la liste de chains
        List<String> res = new ArrayList<>();

        // split selon separateur
        String[] split = clefsString.split(this.separateur);

        // ajoute tout
        for (String clef:split)
            res.add(clef);

        return res;
    }
}
