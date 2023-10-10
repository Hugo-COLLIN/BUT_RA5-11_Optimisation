public abstract class AlgoClassification {
    Donnees donneesEntrainement;

    public AlgoClassification(Donnees d)
    {
        this.donneesEntrainement = d;
    }

    public abstract int predire(Imagette imagette);
}
