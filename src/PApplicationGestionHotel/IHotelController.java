package PApplicationGestionHotel;

import PApplicationGestionHotel.PHotel.Chambre;
import PApplicationGestionHotel.PHotel.Client;
import PApplicationGestionHotel.PHotel.Reservation;

import javax.swing.*;
import java.util.ArrayList;

public interface IHotelController {

    /**
     * Recuperer la totalite des reservations
     */
    public ArrayList<Reservation> recupererReservations();

    /**
     * Recuperer une reservation specifique
     */
    public void recupererReservation();

    /**
     * Recuperer la reservation en cours d'enregistrement
     */
    public Reservation recupererReservationEnCours();

    /**
     * Recuperer les chambres disponibles pour une periode
     */
    public ArrayList<Chambre> recupererChambresDipos(Object firstDate, Object secondDate);

    /**
     * Reserve des chambres pour une reservation
     */
    public boolean reserverChambres(JCheckBox[] boxes);

    /**
     * Reserver un client pour une reservation
     */
    public boolean reserverClient(ButtonGroup bg);

    /**
     * Ajouter une nouvelle reservation complete
     */
    public void ajouterReservation();

    /**
     * Annuler une reservation
     */
    public boolean annulerReservation(int id);

    /**
     * Changer de date pour une reservation
     */
    public void changerDate();

    /**
     * Verifier la disponibilite d'une chambre pour une periode
     */
    public void verifierDisponibilite();

    /**
     * Calculer le prix d'une chambre
     */
    public void calculerPrix();

    /**
     * Recuperer la totalite des clients
     */
    public ArrayList<Client> recupererClients();

    /**
     * Ajouter un nouveau client
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
     */
    public ArrayList<Chambre> recupererChambres();
}
