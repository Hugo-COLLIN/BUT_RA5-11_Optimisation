import chargement.Chargement;
import chargement.Data;

import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException {
        Chargement ch = new Chargement("arbre/data/data_animaux/animaux.csv");
        List<Data> dataAnimaux = ch.chargerFichier();

        for (Data animal : dataAnimaux)
        {
            animal.ajouterAttribut("alea", String.valueOf(Math.random()));
//            System.out.println(animal.getValeur("alea"));
            System.out.println(animal.getValeur("couleur"));
        }


    }
}
