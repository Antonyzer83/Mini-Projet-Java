package PApplicationGestionHotel.PHotel;

public class Chambre {

    public int id;

    public String saison;

    public String type;

    public String categorie;

    public double prix;

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

    public String getName() {
        return this.id + ", " + this.categorie + ", " + this.type + ", " + this.prix + "€";
    }

    private double calculPrixNuit(double saison, double type, double categorie) {
        return (double) Math.round(saison * type * categorie * 10) / 10;
    }
}
