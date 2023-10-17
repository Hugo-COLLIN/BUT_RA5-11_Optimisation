package etudiant;

import java.io.IOException;

public class MainArbre {

    public static void main(String[] args) throws IOException {

        // TODO nom du fichier des donnees
        String nomDonnees = "";

        // TODO chargement des donnees

        // TODO creation de l'arbre a et lancement des calculs
        Arbre arbre = null;

        // affichage de l'arbre
        Affichage affiche = new Affichage();
        System.out.println(affiche.toGraphviz(arbre));

        // TODO a regarder dans graphviz

    }
}
