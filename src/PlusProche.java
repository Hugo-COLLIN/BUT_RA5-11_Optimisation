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

    public double calculerDistance(Imagette test, Imagette entrainement)
    {

    }


}
