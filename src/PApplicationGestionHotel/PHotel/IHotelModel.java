package PApplicationGestionHotel.PHotel;

import java.sql.Date;
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
    public int ajouterReservation(int nb_nuits, Date date_reservation, int nb_personne, int client_id);

    /**
     * Ajouter une reservation et une chambre
     */
    public boolean ajouterReservationChambre(int id_reservation, int id_chambre, Date date_debut, Date date_fin);

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

    /**
     * Recuperer les chambres disponibles pendant une periode
     * @return
     */
    public ResultSet recupererChambresDispos(String date_debut, String date_fin);
}
