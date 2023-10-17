package etudiant;

import chargement.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Noeud {


    ///////////////////////////////////////////////////////////
    // ## rendu graphviz (externaliser ?)
    ///////////////////////////////////////////////////////////

    /**
     * pour avec des id de noeuds dans graphviz
     */
    public static int ID_NOEUD = 0;
    String id = "";


    ///////////////////////////////////////////////////////////
    // ## attributs utiles
    ///////////////////////////////////////////////////////////

    /**
     * la liste des data du noeud
     */
    List<Data> data;

    /**
     * le nom du critère a predire
     */
    String sortie;


    /**
     * le critere conserve au niveau de ce noeud
     */
    String critereTest = null;


    /**
     * gestion des fils => structure d'arbre
     */
    Map<String, Noeud> fils;

    /**
     * constructeur simple
     *
     * @param data   donnees associes au noeud
     * @param sortie critere de sortie
     */
    public Noeud(List<Data> data, String sortie) {
        this.data = data;
        this.fils = new HashMap<>();

        this.sortie = sortie;

        // id du noeud pour graphviz
        this.id = "N" + ID_NOEUD;
        ID_NOEUD++;
    }

    ///////////////////////////////////////////////////////////////////////
    // #### Appels récurifs
    ///////////////////////////////////////////////////////////////////////


    /**
     * appel recursif en passant les criteres consideres pour ce noeud
     *
     * @param entrees liste des criteres utiles pour ce noeud.
     */
    public void creationArbre(String[] entrees) {
        // TODO a faire
        throw new Error("TODO");
    }

    /**
     * methode intermediaire permettant de retourner un tableau de critere correspodant au tableau initial sans le meilleurcritere choisi
     *
     * @param entrees         tableau initial des criteres
     * @param meilleurCritere critere a retirer
     * @return nouveau tableau de critere pour les noeuds fils
     */
    private String[] retirerCritere(String[] entrees, String meilleurCritere) {
        // TODO a faire
        throw new Error("TODO");

    }

}
