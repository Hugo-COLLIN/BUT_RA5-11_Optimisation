package etudiant;

import chargement.Data;

import java.util.List;
import java.util.Map;

/**
 * classe d'affichage integralement fournie
 */
public class Affichage {

    Analyse analyse = new Analyse();

    ///////////////////////////////////////////////////////////////////////
    // #### toString
    ///////////////////////////////////////////////////////////////////////


    /**
     * transforme un arbre en chaine de caracteres
     * @param a arbre
     * @return chaine correspondante
     */
    public String toString(Arbre a) {
        // lance appel recursif
        return this.toString(0, a.racine);
    }

    /**
     * affiche un noeud a un certain niveau et affiche ses fils par recursion.
     * @param level niveau du noeud
     * @param n noeud a afficher
     * @return chaine correspondante
     */
    public String toString(int level, Noeud n) {
        // creer une chaine de saut
        String espace = "";
        for (int i = 0; i < level; i++)
            espace += "   ";
        espace = espace + "* L" + level + " ";

        // affiche le nom
        String res = "";

        // si c'est un noeud final
        if (n.fils.size() == 0)
            res += espace + "- (" + n.data.size() + ")\n";

        else {
            res += espace + "" + n.critereTest + " ? (" + n.data.size() + ")" + "\n";
            // pour chaque fils
            for (String valeur : n.fils.keySet()) {
                res += espace + "--> " + valeur + "\n";
                res += this.toString(level + 1, n.fils.get(valeur));
            }

        }
        return res;
    }


    ////////////////////////////////////////////
    // # graphviz
    ////////////////////////////////////////////


    /**
     * genere un affiche graphviz d'un arbre
     * @param a arbre a afficher
     * @return chaine graphviz
     */
    public String toGraphviz(Arbre a) {
        String res = "";
        res += "digraph G {\n";
        res += getGraphvizInterne(a.racine);
        res += "\n}\n";

        return res;
    }


    /**
     * permet d'afficher les valeurs au niveau d'un noeud
     * @param liste liste des donnees
     * @param sortie valeur de sortie
     * @return descriptif de la répartition des sorties
     */
    private String afficheDistribution(List<Data> liste, String sortie) {
        // couper selon sortie
        Map<String, List<Data>> repartition = analyse.separer(liste, sortie);

        // resultat
        String res = "";
        for (String clef : repartition.keySet()) {
            res += clef;
            res += "(" + repartition.get(clef).size() + ")";
            res += " ";
        }
        return res;
    }

    /**
     * affiche un noeud et ses fils par recursivite
     * @param n noeud a afficher
     * @return chaine correspondante
     */
    private String getGraphvizInterne(Noeud n) {
        String res = "";
        // si c'est un noeud terminal
        if (n.fils.size() == 0) {
            // recupere repartition
            String dist = afficheDistribution(n.data, n.sortie);

            // affiche le résultat de sortie + nombre
            String v = n.data.get(0).getValeur(n.sortie);

            // res = n.id + " [label=\"" + v + " (" + n.data.size() + ")\",style=filled, color=green,shape =rectangle]\n";
            res = n.id + " [label=\"" + dist + "\",style=filled, color=green,shape =rectangle]\n";
        } else {
            // sinon, pas un noeud terminal

            //ajoute le noeud avec le titre
            // recupere repartition
            String dist = afficheDistribution(n.data, n.sortie);

            res = n.id + " [label=\"" + dist+"\\n "+ n.critereTest + " ? (" + n.data.size() + ")\"]\n";

            // ajoute les fils
            for (String valeur : n.fils.keySet()) {
                //recupere l'enfant
                Noeud enfant = n.fils.get(valeur);

                // ajoute lien vers les fils
                res += n.id + " -> " + enfant.id + " [label=\"" + valeur + "\"]\n";

                // ajoute les descendants
                res += this.getGraphvizInterne(enfant);
            }
        }
        return res;
    }

}
