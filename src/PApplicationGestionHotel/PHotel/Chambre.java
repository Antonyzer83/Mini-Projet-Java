package PApplicationGestionHotel.PHotel;

public class Chambre {

    public int id;

    public String saison;

    public String type;

    public String categorie;

    public double prix;

    public Chambre(int id, String saison, String type, String categorie, double prix) {
        this.id = id;
        this.saison = saison;
        this.type = type;
        this.categorie = categorie;
        this.prix = prix;
        //System.out.println(this);
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
        return this.type + ", " + this.categorie + ", " + this.prix;
    }
}
