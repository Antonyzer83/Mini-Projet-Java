package PApplicationGestionHotel;

import PApplicationGestionHotel.PHotel.Chambre;
import PApplicationGestionHotel.PHotel.Client;
import PApplicationGestionHotel.PHotel.Reservation;

import javax.swing.*;
import java.util.ArrayList;

public interface IHotelController {

    /**
     * Recuperer la totalite des reservations
     *
     * @return
     *          Liste des reservations
     */
    public ArrayList<Reservation> recupererReservations();

    /**
     * Recuperer la reservation en cours d'enregistrement
     *
     * @return
     *          Reservation en cours
     */
    public Reservation recupererReservationEnCours();

    /**
     * Recuperer les chambres disponibles pour une periode
     *
     * @param firstDate
     *          Date de debut
     * @param secondDate
     *          Date de fin
     * @return
     *          Liste des chambres disponibles
     */
    public ArrayList<Chambre> recupererChambresDipos(Object firstDate, Object secondDate);

    /**
     * Reserve des chambres pour une reservation
     *
     * @param boxes
     *          Cases à choix
     * @return
     *          Succes
     */
    public boolean reserverChambres(JCheckBox[] boxes);

    /**
     * Reserver un client pour une reservation
     *
     * @param bg
     *          Groupe de boutons
     * @return
     *          Succes
     */
    public boolean reserverClient(ButtonGroup bg);

    /**
     * Ajouter une nouvelle reservation complete
     */
    public void ajouterReservation();

    /**
     * Annuler une reservation
     *
     * @param id
     *          Id de la reservation
     */
    public boolean annulerReservation(int id);

    /**
     * Recuperer la totalite des clients
     *
     * @return
     *          Liste des clients
     */
    public ArrayList<Client> recupererClients();

    /**
     * Ajouter un nouveau client
     *
     * @param mode
     *          Mode
     * @param nom
     *          Nom du client
     * @param prenom
     *          Prenom du client
     * @param cin
     *          CIN du client
     * @param telephone
     *          Numero de telephone du client
     * @param cb
     *          Numero de carte bancaire
     * @return
     *          Succes
     */
    public boolean ajouterClient(boolean mode, String nom, String prenom, String cin, String telephone, String cb);

    /**
     * Verifier la conformite d'un formulaire
     */
    public void verifierFormulaire();

    /**
     * Recuperer un client specifique
     */
    public void recupererClient();

    /**
     * Mettre a jour un client existant
     */
    public void MAJClient();

    /**
     * Recuperer la totalite des chambres
     *
     * @return
     *          Liste de chambres
     */
    public ArrayList<Chambre> recupererChambres();
}
