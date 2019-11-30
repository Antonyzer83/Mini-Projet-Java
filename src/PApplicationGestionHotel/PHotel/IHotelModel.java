package PApplicationGestionHotel.PHotel;

import java.sql.ResultSet;

public interface IHotelModel {

    /**
     * Recuperer la totalite des reservations
     */
    public ResultSet recupererReservations();

    /**
     * Recuperer la totalite des chambres
     */
    public ResultSet recupererChambres();

    /**
     * Ajouter une nouvelle reservation
     */
    public void ajouterReservation();

    /**
     * Supprimer une reservation
     */
    public boolean supprimerReservation(int id_reservation);

    /**
     * Recuperer une reservation specifique
     */
    public ResultSet recupererReservation(int id_reservation);

    /**
     * Recuperer les dates d'une chambres
     */
    public ResultSet recupererDateChambre(int id_reservation, int id_chambre);

    /**
     * Mettre a jour la date d'une chambre
     */
    public void MAJDateChambre();

    /**
     * Recuperer un client specifique
     */
    public ResultSet recupererClient(int id_client);

    /**
     * Mettre a jour un client
     */
    public void MAJClient();

    /**
     * Ajouter un nouveau client
     */
    public boolean ajouterClient(String nom, String prenom, int cin, String telephone);

    /**
     * Recuperer la totalite des clients
     */
    public ResultSet recupererClients();
}
