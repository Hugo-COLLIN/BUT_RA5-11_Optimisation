package chargement;

import java.util.HashMap;
import java.util.Map;

/**
 * une donnee de base
 * contient une map de type clef -> valeur
 * tout est en String
 */
public class Data {

    /**
     * attributs de la donnee
     */
    Map<String, String> attributs;

    /**
     * creer une table vide
     */
    public Data() {
        this.attributs = new HashMap<>();
    }

    /**
     * ajoute une association clef -> valeur
     *
     * @param clef   la clef identifiant l'attribut
     * @param valeur la valeur associee a l'attribut
     */
    public void ajouterAttribut(String clef, String valeur) {
        this.attributs.put(clef, valeur);
    }

    /**
     * recupere la valeur du critere
     *
     * @param critere critere a evaluer
     * @return valeur du critere
     */
    public String getValeur(String critere) {
        //TODO ??
        if (!this.attributs.containsKey(critere))
            return"iconnu";
        return this.attributs.get(critere);
    }
}
