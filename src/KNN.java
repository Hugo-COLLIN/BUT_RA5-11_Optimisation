import java.util.TreeMap;

public class KNN extends AlgoClassification {

    private static final int N_PLUS_PROCHES = 5;

    public KNN(Donnees d) {
        super(d);
    }

    @Override
    public int predire(Imagette imagetteInput) {
        // liste pour stocker les k plus proches
        TreeMap<Double, Imagette> kPlusProches = new TreeMap<>();
        //remplir cette liste
        for (Imagette imagetteEntrainement : this.donneesEntrainement.imagettes) {
            // les k premières données
            if (kPlusProches.size() < N_PLUS_PROCHES) {
                kPlusProches.put(calculerDistance(imagetteInput, imagetteEntrainement), imagetteEntrainement);
            } else {
                // si la distance est plus petite que la plus grande distance de la liste
                if (calculerDistance(imagetteInput, imagetteEntrainement) < kPlusProches.lastKey()) {
                    // on supprime la plus grande distance
                    kPlusProches.remove(kPlusProches.lastKey());
                    // on ajoute la nouvelle distance
                    kPlusProches.put(calculerDistance(imagetteInput, imagetteEntrainement), imagetteEntrainement);
                }
            }
        }
        // return l'étiquette la plus présente dans la liste
        return kPlusProches.get(kPlusProches.firstKey()).etiquette;
    }

    public double calculerDistance(Imagette imagette1, Imagette imagette2) {
        double somme = 0;
        for (int i = 0; i < imagette1.pixels.length; i++)
            for (int j = 0; j < imagette2.pixels.length; j++)
                somme += Math.pow(imagette1.pixels[i][j] - imagette2.pixels[i][j], 2);
        return Math.sqrt(somme);
    }
}