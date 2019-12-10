package PApplicationGestionHotel.PHotel;

import java.sql.Date;
import java.sql.ResultSet;

public interface IHotelModel {

    /**
     * Recuperer la totalite des reservations
     *
     * @return
     *          Totalite des reservations
     */
    public ResultSet recupererReservations();

    /**
     * Recuperer la totalite des chambres
     *
     * @return
     *          Totalite des chambres
     */
    public ResultSet recupererChambres();

    /**
     * Ajouter une nouvelle reservation
     *
     * @param nb_nuits
     *          Nombre de nuits
     * @param date_reservation
     *          Date de reservation
     * @param nb_personne
     *          Nombre de personnes
     * @param client_id
     *          Id du client
     * @return
     *          Id de la reservation
     */
    public int ajouterReservation(int nb_nuits, Date date_reservation, int nb_personne, int client_id);

    /**
     * Ajouter une reservation et une chambre
     *
     * @param id_reservation
     *          Id de la reservation
     * @param id_chambre
     *          Id de la chambre
     * @param date_debut
     *          Date de debut de la reservation
     * @param date_fin
     *          Date de fin de la reservation
     * @return
     *          Succes de l'ajout de la reservation
     */
    public boolean ajouterReservationChambre(int id_reservation, int id_chambre, Date date_debut, Date date_fin);

    /**
     * Supprimer une reservation
     *
     * @param id_reservation
     *          Id de la reservation
     * @return
     *          Succes de la suppression de la reservation
     */
    public boolean supprimerReservation(int id_reservation);

    /**
     * Mettre a jour la date d'une chambre
     */
    public void MAJDateChambre();

    /**
     * Recuperer un client specifique
     *
     * @param id_client
     *          Id du client
     * @return
     *          Client en fonction de l'id
     */
    public ResultSet recupererClient(int id_client);

    /**
     * Mettre a jour un client
     */
    public void MAJClient();

    /**
     * Ajouter un nouveau client
     *
     * @param nom
     *          Nom de famille
     * @param prenom
     *          Prenom du client
     * @param cin
     *          CIN du client
     * @param telephone
     *          Numero de telephone
     * @param cb
     *          Numero de carte bancaire
     * @return
     *          Id du client ajoute
     */
    public int ajouterClient(String nom, String prenom, int cin, String telephone, String cb);

    /**
     * Recuperer la totalite des clients
     *
     * @return
     *          Totalite des clients
     */
    public ResultSet recupererClients();

    /**
     * Recuperer les chambres disponibles pendant une periode
     *
     * @param date_debut
     *          Date de debut de la periode
     * @param date_fin
     *          Date de fin de la periode
     * @return
     *          Chambres disponibles pendant la periode
     */
    public ResultSet recupererChambresDispos(Date date_debut, Date date_fin);
}
