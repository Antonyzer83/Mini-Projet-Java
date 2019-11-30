package PApplicationGestionHotel.PHotel;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HotelController implements IHotelController {

    private HotelModel hotelModel;

    public HotelController(HotelModel hotelModel) {
        this.hotelModel = hotelModel;
    }

    /**
     * Recuperer la totalite des reservations
     */
    public void recupererReservations() {

    }

    /**
     * Recuperer une reservation specifique
     */
    public void recupererReservation() {

    }

    /**
     * Recuperer la reservation en cours d'enregistrement
     */
    public void recupererReservationEnCours() {}

    /**
     * Recuperer les chambres disponibles pour une periode
     */
    public ArrayList<Chambre> recupererChambresDipos(Object firstDate, Object secondDate) {
        String date_d = new SimpleDateFormat("dd-MM-yyyy").format(firstDate);
        String date_f = new SimpleDateFormat("dd-MM-yyyy").format(secondDate);
        System.out.println(date_d + " " + date_f);
        try {
            Date date_debut = new SimpleDateFormat("dd-MM-yyyy").parse(date_d);
            Date date_fin = new SimpleDateFormat("dd-MM-yyyy").parse(date_f);
            if (date_debut.before(date_fin)) {
                ResultSet res = hotelModel.recupererChambresDispos(date_d, date_f);
                ArrayList<Chambre> chambres = new ArrayList<>();
                while (res.next()) {
                    double prix = res.getDouble(3) * res.getDouble(5) * res.getDouble(7);
                    chambres.add(new Chambre(res.getInt(1), res.getString(6), res.getString(2), res.getString(4), prix));
                }
                return chambres;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Reserve des chambres pour une reservation
     */
    public void reserverChambres() {

    }

    /**
     * Reserver un client pour une reservation
     */
    public void reserverClient() {

    }

    /**
     * Ajouter une nouvelle reservation complete
     */
    public void ajouterReservation() {

    }

    /**
     * Annuler une reservation
     */
    public void annulerReservation() {

    }

    /**
     * Changer de date pour une reservation
     */
    public void changerDate() {

    }

    /**
     * Verifier la disponibilite d'une chambre pour une periode
     */
    public void verifierDisponibilite() {

    }

    /**
     * Calculer le prix d'une chambre
     */
    public void calculerPrix() {

    }

    /**
     * Recuperer la totalite des clients
     */
    public void recupererClients() {

    }

    /**
     * Ajouter un nouveau client
     */
    public void ajouterClient() {

    }

    /**
     * Verifier la conformite d'un formulaire
     */
    public void verifierFormulaire() {

    }

    /**
     * Recuperer un client specifique
     */
    public void recupererClient() {

    }

    /**
     * Mettre a jour un client existant
     */
    public void MAJClient() {

    }

    /**
     * Recuperer la totalite des chambres
     */
    public void recupererChambres() {

    }
}
