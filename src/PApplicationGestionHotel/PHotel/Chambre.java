package PApplicationGestionHotel.PHotel;

public class Chambre {

    /**
     * Id de la chambre
     */
    public int id;

    /**
     * Nom de la saison
     */
    public String saison;

    /**
     * Nom du type
     */
    public String type;

    /**
     * Nom de la categorie
     */
    public String categorie;

    /**
     * Prix de la chambre pour une nuit
     */
    public double prix;

    /**
     * Constructeur de la classe Chambre
     *
     * @param id
     *          Id de la chambre
     * @param saison
     *          Nom de la saison
     * @param type
     *          Nom du type
     * @param categorie
     *          Nom de la categorie
     * @param prixSaison
     *          Prix de la saison
     * @param prixType
     *          Prix du type
     * @param prixCategorie
     *          Prix de la categorie
     */
    public Chambre(int id, String saison, String type, String categorie, double prixSaison, double prixType, double prixCategorie) {
        this.id = id;
        this.saison = saison;
        this.type = type;
        this.categorie = categorie;
        this.prix = this.calculPrixNuit(prixSaison, prixType, prixCategorie);
    }

    @Override
    public String toString() {
        return "Id : " + this.id +
                "\nSaison : " + this.saison +
                "\nType : " + this.type +
                "\nCategorie : " + this.categorie +
                "\nPrix : " + this.prix;
    }

    /**
     * Recuperation des details de la chambre
     *
     * @return
     *          Details de la chambre
     */
    public String getName() {
        return this.id + ", " + this.categorie + ", " + this.type + ", " + this.prix + "€";
    }

    /**
     * Calcul du prix de la chambre pour une nuit*
     *
     * @param saison
     *          Prix de la saison
     * @param type
     *          Prix du type
     * @param categorie
     *          Prix de la categorie
     * @return
     *          Prix calcule a l'aide de la saison, type et categorie
     */
    private double calculPrixNuit(double saison, double type, double categorie) {
        return (double) Math.round(saison * type * categorie * 10) / 10;
    }
}
