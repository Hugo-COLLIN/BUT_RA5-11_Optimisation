public class PlusProche extends AlgoClassification
{

    public PlusProche(Donnees d) {
        super(d);
    }

    @Override
    public int predire(Imagette imagetteInput) {
        // init minDistance
        double minDistance = Double.MAX_VALUE;

        //init etiquette + proche
        Imagette plusProche = null;

        //pour chaque imagette de donneesEntrainement
        for (Imagette imagetteEntrainement : this.donneesEntrainement.imagettes) {
            //calculer la distance
            double dist = calculerDistance(imagetteInput, imagetteEntrainement);
            //si distance < minDistance
            if (dist < minDistance) {
                //minDistance = distance
                minDistance = dist;

                //image plus proche <- imagetteEntrainement
                plusProche = imagetteEntrainement;
            }
        }


        assert plusProche != null;
        return plusProche.etiquette;
    }

    public double calculerDistance(Imagette imagette1, Imagette imagette2)
    {
        double somme = 0;
        for (int i = 0 ; i < imagette1.pixels.length ; i ++)
            for (int j = 0 ; j < imagette2.pixels.length ; j ++)
                somme += Math.pow(imagette1.pixels[i][j] - imagette2.pixels[i][j], 2);
        return Math.sqrt(somme);
    }


}
