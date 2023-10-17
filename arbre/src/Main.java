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

    }
}
