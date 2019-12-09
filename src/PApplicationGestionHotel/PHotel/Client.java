package PApplicationGestionHotel.PHotel;

public class Client {

    /**
     * Id du client
     */
    public int id;

    /**
     * Nom de famille du client
     */
    public String nom;

    /**
     * Prenom du client
     */
    public String prenom;

    /**
     * CIN du client
     */
    public int cin;

    /**
     * Numero de telephone du client
     */
    public String telephone;

    /**
     * Numero de carte bancaire du client
     */
    public String cb;

    /**
     * Constructeur du client
     *
     * @param id
     *          Id du client
     * @param nom
     *          Nom du client
     * @param prenom
     *          Prenom du client
     * @param cin
     *          CIN du client
     * @param telephone
     *          Numero de telephone du client
     * @param cb
     *          Numero de carte bancaire du client
     */
    public Client(int id, String nom, String prenom, int cin, String telephone, String cb) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.cb = cb;
    }

    /**
     * Recuperation des details du client
     *
     * @return
     *          Details du client
     */
    public String getName() {
        return this.id + ", " + this.nom + " " + this.prenom + ", " + this.cin;
    }
}
