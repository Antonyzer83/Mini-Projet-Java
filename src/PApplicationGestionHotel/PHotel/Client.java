package PApplicationGestionHotel.PHotel;

public class Client {

    public int id;

    public String nom;

    public String prenom;

    public int cin;

    public String telephone;

    public String cb;

    public Client(int id, String nom, String prenom, int cin, String telephone, String cb) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.cb = cb;
    }

    public String getName() {
        return this.id + ", " + this.nom + " " + this.prenom + ", " + this.cin;
    }
}
