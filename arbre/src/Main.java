import chargement.Chargement;
import chargement.Data;
import etudiant.Analyse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws IOException {
        Chargement ch = new Chargement("arbre/data/data_animaux/animaux.csv");
        List<Data> dataAnimaux = ch.chargerFichier();

//        for (Data animal : dataAnimaux)
//        {
//            animal.ajouterAttribut("alea", String.valueOf(Math.random()));
//            System.out.println(animal.getValeur("couleur"));
//        }

        Analyse a = new Analyse();
        Map<String, List<Data>> trie = a.separer(dataAnimaux, "couleur");
        System.out.println(trie);

        Map<String, Double> distr = a.calculerDistribution(trie);
        System.out.println(distr);

        double entropy = a.entropie(dataAnimaux, "nager");
        System.out.println(entropy);

        double entropy2 = a.entropie(dataAnimaux, "couleur");
        System.out.println(entropy2);

        double moyEntropie = a.entropieMoyenne(dataAnimaux, "nageoire", "nager");
        System.out.println(moyEntropie);


        String[] entrees = {"nageoire", "couleur"};
        String meilleurCritere = a.getMeilleurCritere(dataAnimaux, entrees, "nager");
        System.out.println(meilleurCritere);

    }
}
