package PApplicationGestionHotel.PHotel;

import javax.swing.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelController implements IHotelController {

    private HotelModel hotelModel;

    private Reservation reservation;

    public HotelController(HotelModel hotelModel) {
        this.hotelModel = hotelModel;
        this.reservation = new Reservation();
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
    public Reservation recupererReservationEnCours() {
        return this.reservation;
    }

    /**
     * Recuperer les chambres disponibles pour une periode
     */
    public ArrayList<Chambre> recupererChambresDipos(Object firstDate, Object secondDate) {
        this.reservation = new Reservation();
        //Conversion des objets date en String
        String date_d = new SimpleDateFormat("dd-MM-yyyy").format(firstDate);
        String date_f = new SimpleDateFormat("dd-MM-yyyy").format(secondDate);
        try {
            // Conversion des strings date en Date
            Date date_debut = new SimpleDateFormat("dd-MM-yyyy").parse(date_d);
            this.reservation.date_debut = date_debut;
            Date date_fin = new SimpleDateFormat("dd-MM-yyyy").parse(date_f);
            this.reservation.date_fin = date_fin;
            // Vérification que la date finale est supérieure à la date initiale
            if (date_debut.before(date_fin)) {
                // Récupération des chambres disponibles
                ResultSet res = hotelModel.recupererChambresDispos( new java.sql.Date(date_debut.getTime()),  new java.sql.Date(date_fin.getTime()));
                ArrayList<Chambre> chambres = new ArrayList<>();
                // Pour chaque chambre
                while (res.next()) {
                    double prix = (double) Math.round(res.getDouble(3) * res.getDouble(5) * res.getDouble(7) * 10) / 10;
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
    public boolean reserverChambres(JCheckBox[] boxes) {
        int count = 0;
        for (JCheckBox box : boxes) {
            if (box.isSelected()) {
                this.reservation.ajouterChambre(box.getText());
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reserver un client pour une reservation
     */
    public boolean reserverClient(ButtonGroup bg) {
        String client = bg.getSelection().getActionCommand();
        if (client != null) {
            this.reservation.ajouterClient(client);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ajouter une nouvelle reservation complete
     */
    public void ajouterReservation() {
        int client_id = Integer.parseInt(this.reservation.client.split(", ")[0]);
        int reservation_id = this.hotelModel.ajouterReservation(
                this.reservation.calculNbJour(),
                new java.sql.Date(Calendar.getInstance().getTime().getTime()),
                5,
                client_id
        );
        for (String chambre : this.reservation.chambres) {
            List<String> chambreSplit = Arrays.asList(chambre.split(", "));
            int chambre_id = Integer.parseInt(chambreSplit.get(0));
            this.hotelModel.ajouterReservationChambre(reservation_id, chambre_id, new java.sql.Date(this.reservation.date_debut.getTime()), new java.sql.Date(this.reservation.date_fin.getTime()));
        }
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
    public ArrayList<Client> recupererClients() {
        try {
            ResultSet res = this.hotelModel.recupererClients();
            ArrayList<Client> clients = new ArrayList<>();
            while (res.next()) {
                clients.add(new Client(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getString(6)));
            }
            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Ajouter un nouveau client
     */
    public boolean ajouterClient(boolean mode, String nom, String prenom, String cin, String telephone, String cb) {
        if (!nom.equals("") && !prenom.equals("") && !cin.equals("") && !telephone.equals("") && !cb.equals("")) {
            int id = this.hotelModel.ajouterClient(nom, prenom, Integer.parseInt(cin), telephone, cb);
            if (id != 0) {
                this.reservation.ajouterClient(id + ", " + nom + " " + prenom + ", " + cin);
                return true;
            }
            return false;
        } else {
            return false;
        }
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
    public ArrayList<Chambre> recupererChambres() {
        ResultSet res = this.hotelModel.recupererChambres();
        ArrayList<Chambre> chambres;
        return null;
    }
}
