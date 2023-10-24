package etudiant;

import chargement.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Analyse {

    /**
     * methode qui separe les donnees selon un critere
     *
     * @param donnees  la liste des donnees a separer
     * @param critere critere de separation
     * @return la liste des donnees reparties en sous groupe dans une map : valeur du critere -> donnees correspondantes
     */
    public Map<String, List<Data>> separer(List<Data> donnees, String critere) {
        Map<String, List<Data>> res = new TreeMap<>();

        for (Data d : donnees)
        {
            List<Data> critereDejaAjoute = res.get(d.getValeur(critere));
            if (critereDejaAjoute != null)
                res.get(d.getValeur(critere)).add(d);
            else {
                List<Data> newCritere = new ArrayList<>();
                newCritere.add(d);
                res.put(d.getValeur(critere), newCritere);
            }
        }
        return res;
    }


    /**
     * retourne les probabilites d'etre dans les groupes (calculees à partir des frequences)
     *
     * @param groupes la map qui associe groupe a chaque valeur
     * @return Table : chaque valeur -> proba d'etre dans le groupe
     */
    public Map<String, Double> calculerDistribution(Map<String, List<Data>> groupes) {
        Map<String, Double> res = new TreeMap<>();
        int totalSize = groupes.values().stream().mapToInt(List::size).sum();

        //pour chaque groupe
        for (Map.Entry<String, List<Data>> mapEntry : groupes.entrySet())
        {
            // calculer la probabilité d'être dans le groupe
            double proba = (double) mapEntry.getValue().size() / totalSize;
            // ajouter la valeur à l'élément de clé spécifié pour la map
            res.put(mapEntry.getKey(), proba);
        }

        return res;
    }


    /**
     * calcule l'entropie d'un groupe en fonction de la sortie predite
     *
     * @param groupe groupe dont on veut l'entropie
     * @param sortie nom du critère de sortie à predire
     * @return l'entropie selon la sortie
     */
    public double entropie(List<Data> groupe, String sortie) {
        // Séparer le groupe en sous-groupes selon le critère de sortie
        Map<String, List<Data>> separatedGroup = separer(groupe, sortie);

        // Calculer la distribution
        Map<String, Double> distribution = calculerDistribution(separatedGroup);

        // Calculer l'entropie
        double entropy = 0.0;
        for (Double proba : distribution.values()) {
            if (proba > 0) {
                entropy += -proba * Math.log(proba);
            }
        }

        return entropy;
    }

    /**
     * retourne l'entropie moyenne associée à une séparation en sous groupe
     *
     * @param groupe groupe de depart
     * @param nomCritere le critere utilise pour séparer les données
     * @param sortie le critère final à prédire (pour calcul d'entropie)
     * @return l'entropie moyenne correspondant à séparer le groupe en sous-groupes.
     */
    public double entropieMoyenne(List<Data> groupe, String nomCritere, String sortie) {
        // Séparer le groupe en sous-groupes selon le critère de sortie
        Map<String, List<Data>> separatedGroup = separer(groupe, nomCritere);

        // Calculer la distribution
        Map<String, Double> distribution = calculerDistribution(separatedGroup);

        // Calculer l'entropie moyenne
        double averageEntropy = 0.0;
        for (Map.Entry<String, List<Data>> entry : separatedGroup.entrySet()) {
            double proba = distribution.get(entry.getKey());
            double entropy = entropie(entry.getValue(), sortie);
            averageEntropy += proba * entropy;
        }

        return averageEntropy;
    }


    /**
     * cherche le critere qui ameliore le mieux le gain d'entropie
     *
     * @param entrees liste des critères
     * @param sortie  sortie a predire
     * @return meilleur critere ou null si aucun critere n'améliore
     */
    public String getMeilleurCritere(List<Data> donnees, String[] entrees, String sortie) {
        String meilleurCritere = null;
        double maxGain = Double.NEGATIVE_INFINITY;

        // Calculate the entropy of the input group
        double entropieDonnees = entropie(donnees, sortie);

        // For each criterion, calculate the entropy gain
        for (String critere : entrees) {
            double entropieMoyenne = entropieMoyenne(donnees, critere, sortie);
            double gain = entropieDonnees - entropieMoyenne;

            // If the gain is higher than the current maximum, update the maximum and the best criterion
            if (gain > maxGain) {
                maxGain = gain;
                meilleurCritere = critere;
            }
        }

        return meilleurCritere;
    }


}
