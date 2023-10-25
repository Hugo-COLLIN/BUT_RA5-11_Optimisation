package etudiant;

import chargement.Chargement;

import java.io.IOException;

public class MainArbre {

    public static void main(String[] args) throws IOException {

        // nom du fichier des donnees
        String nomDonnees = "arbre/data/data_animaux/animaux.csv";

        // chargement des donnees
        Chargement ch = new Chargement(nomDonnees);

        // TODO creation de l'arbre a et lancement des calculs
        Arbre arbre = new Arbre(ch.chargerFichier(), new String[]{"nageoire", "couleur"}, "nager");

        // affichage de l'arbre
        Affichage affiche = new Affichage();
        System.out.println(affiche.toGraphviz(arbre));

        // TODO a regarder dans graphviz


    }
}
