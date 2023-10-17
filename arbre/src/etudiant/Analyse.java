package etudiant;

import chargement.Data;

import java.util.List;
import java.util.Map;


public class Analyse {

    /**
     * methode qui separe les donnees selon un critere
     *
     * @param donnees  la liste des donnees a separer
     * @param critere critere de separation
     * @return la liste des donnees reparties en sous groupe dans une map : valeur du critere -> donnees correspondantes
     */
    public Map<String, List<Data>> separer(List<Data> donnees, String critere) {
        // TODO Afaire
        throw new Error("TODO");
    }


    /**
     * retourne les probabilites d'etre dans les groupes (calculees à partir des frequences)
     *
     * @param groupes la map qui associe groupe a chaque valeur
     * @return Table : chaque valeur -> proba d'etre dans le groupe
     */
    public Map<String, Double> calculerDistribution(Map<String, List<Data>> groupes) {
        // TODO Afaire
        throw new Error("TODO");
    }

    /**
     * calcule l'entropie d'un groupe en fonction de la sortie predite
     *
     * @param groupe groupe dont on veut l'entropie
     * @param sortie nom du critère de sortie à predire
     * @return l'entropie selon la sortie
     */
    double entropie(List<Data> groupe, String sortie) {
        // TODO Afaire
        throw new Error("TODO");
    }

    /**
     * retourne l'entropie moyenne associée à une séparation en sous groupe
     *
     * @param groupe groupe de depart
     * @param nomCritere le critere utilise pour séparer les données
     * @param sortie le critère final à prédire (pour calcul d'entropie)
     * @return l'entropie moyenne correspondant à séparer le groupe en sous-groupes.
     */
    double entropieMoyenne(List<Data> groupe, String nomCritere, String sortie) {
        // TODO Afaire
        throw new Error("TODO");
    }


    /**
     * cherche le critere qui ameliore le mieux le gain d'entropie
     *
     * @param entrees liste des critères
     * @param sortie  sortie a predire
     * @return meilleur critere ou null si aucun critere n'améliore
     */
    public String getMeilleurCritere(List<Data> donnees, String[] entrees, String sortie) {
        // TODO Afaire
       throw new Error("TODO");
    }


}
