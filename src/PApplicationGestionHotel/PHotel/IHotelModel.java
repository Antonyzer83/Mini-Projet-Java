package PApplicationGestionHotel.PHotel;

public interface IHotelModel {

    /**
     * Recuperer la totalite des reservations
     */
    public void recupererReservations();

    /**
     * Recuperer la totalite des chambres
     */
    public void recupererChambres();

    /**
     * Ajouter une nouvelle reservation
     */
    public void ajouterReservation();

    /**
     * Supprimer une reservation
     */
    public void supprimerReservation();

    /**
     * Recuperer une reservation specifique
     */
    public void recupererReservation();

    /**
     * Recuperer les dates d'une chambres
     */
    public void recupererDateChambre();

    /**
     * Mettre a jour la date d'une chambre
     */
    public void MAJDateChambre();

    /**
     * Recuperer un client specifique
     */
    public void recupererClient();

    /**
     * Mettre a jour un client
     */
    public void MAJClient();

    /**
     * Ajouter un nouveau client
     */
    public void ajouterClient();

    /**
     * Recuperer la totalite des clients
     */
    public void recupererClients();
}
