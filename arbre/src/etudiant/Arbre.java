package etudiant;

import chargement.Data;

import java.util.List;

/**
 * represente un arbre
 */
public class Arbre {

    /**
     * racine de l'arbre
     */
    Noeud racine;

    /**
     * liste des données presentes a ce noeud
     */
    List<Data> data;

    /**
     * liste des criteres possibles a considerer pour separer les donnees
     */
    String[] entrees;

    /**
     * nom du critere de sortie final à predire
     */
    String sortie;


    /**
     * construction de l'arbre
     *
     * @param donnees donnees initiales
     * @param entree  liste des critere d'entree utiles pour la construction de l'arbre de decision
     * @param sortie  nom du ritère de sortie à predire
     */
    public Arbre(List<Data> donnees, String[] entree, String sortie) {
        this.data = donnees;
        this.sortie = sortie;
        this.entrees = entree;

        // creation du noeud racine
        this.racine = new Noeud(data, sortie);
    }

    /**
     * lancement des calculs => creation de l'arbre en partant de la racine.
     */
    public void creerArbre() {
        this.racine.creationArbre(entrees);
    }


}
