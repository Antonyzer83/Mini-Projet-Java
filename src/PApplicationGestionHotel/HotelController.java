package PApplicationGestionHotel;

import PApplicationGestionHotel.PHotel.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelController implements IHotelController {

    /**
     * Modele
     */
    private HotelModel hotelModel;

    /**
     * Reservation en cours
     */
    private Reservation reservation;

    /**
     * Constructeur de la classe HotelController
     *
     * @param hotelModel
     *          Modele
     */
    public HotelController(HotelModel hotelModel) {
        this.hotelModel = hotelModel;
        this.reservation = new Reservation();
    }

    /**
     * Recuperer la totalite des reservations
     *
     * @return
     *          Liste des reservations
     */
    public ArrayList<Reservation> recupererReservations() {
        try {
            // Recuperation des reservations
            ResultSet res = this.hotelModel.recupererReservations();
            // Creation d'une liste de reservations
            ArrayList<Reservation> reservations = new ArrayList<>();
            int count = 0;
            while (res.next())
            {
                // Verification de l'id des reservations precedente et en cours
                if (count > 0 && reservations.get(count - 1).id == res.getInt(1)) {
                    // Creation d'une nouvelle chambre
                    Chambre chambre = new Chambre(
                            res.getInt(7),
                            res.getString(8),
                            res.getString(12),
                            res.getString(10),
                            res.getDouble(9),
                            res.getDouble(13),
                            res.getDouble(11)
                    );
                    reservations.get(count - 1).ajouterChambre(chambre.getName());
                } else { // Ajout d'une nouvelle reservation
                    Reservation reservation = new Reservation();
                    reservation.ajouterId(res.getInt(1));
                    reservation.ajouterDetails(
                            res.getInt(2),
                            res.getDate(3),
                            res.getInt(4),
                            res.getDate(5),
                            res.getDate(6)
                    );
                    // Ajout d'une chambre
                    reservation.ajouterChambre(new Chambre(
                            res.getInt(7),
                            res.getString(8),
                            res.getString(12),
                            res.getString(10),
                            res.getDouble(9),
                            res.getDouble(13),
                            res.getDouble(11)
                    ).getName());
                    // Ajout d'un client
                    reservation.ajouterClient(new Client(
                            res.getInt(14),
                            res.getString(15),
                            res.getString(16),
                            res.getInt(17),
                            res.getString(18),
                            res.getString(19)
                    ).getName());

                    // Ajout de la reservation a la liste
                    reservations.add(reservation);
                    count++;
                }
            }
            return reservations;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Recuperer la reservation en cours d'enregistrement
     */
    public Reservation recupererReservationEnCours() {
        return this.reservation;
    }

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
                    //double prix = (double) Math.round(res.getDouble(3) * res.getDouble(5) * res.getDouble(7) * 10) / 10;
                    chambres.add(new Chambre(res.getInt(1), res.getString(6), res.getString(2), res.getString(4), res.getDouble(5), res.getDouble(7), res.getDouble(3)));
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
     *
     * @param boxes
     *          Cases à choix
     * @return
     *          Succes
     */
    public boolean reserverChambres(JCheckBox[] boxes) {
        int count = 0;
        for (JCheckBox box : boxes) {
            if (box.isSelected()) {
                // Ajout d'une nouvelle chambre
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
     *
     * @param bg
     *          Groupe de boutons
     * @return
     *          Succes
     */
    public boolean reserverClient(ButtonGroup bg) {
        // Recuperation du client
        String client = bg.getSelection().getActionCommand();
        if (client != null) {
            // Ajout du client a la reservation
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
        // Recuperation de l'id du client
        int client_id = Integer.parseInt(this.reservation.client.split(", ")[0]);
        // Ajout d'une reservation a la BDD
        int reservation_id = this.hotelModel.ajouterReservation(
                this.reservation.calculNbJour(),
                new java.sql.Date(Calendar.getInstance().getTime().getTime()),
                5,
                client_id
        );
        // Ajout une par une de chaque chambre a la BDD
        for (String chambre : this.reservation.chambres) {
            List<String> chambreSplit = Arrays.asList(chambre.split(", "));
            int chambre_id = Integer.parseInt(chambreSplit.get(0));
            this.hotelModel.ajouterReservationChambre(reservation_id, chambre_id, new java.sql.Date(this.reservation.date_debut.getTime()), new java.sql.Date(this.reservation.date_fin.getTime()));
        }
    }

    /**
     * Annuler une reservation
     *
     * @param id
     *          Id de la reservation
     */
    public boolean annulerReservation(int id) {
        if (this.hotelModel.supprimerReservation(id)) {
            System.out.println("YEEEES");
            return true;
        } else {
            System.out.println("NOOOO");
            return false;
        }
    }

    /**
     * Recuperer la totalite des clients
     *
     * @return
     *          Liste des clients
     */
    public ArrayList<Client> recupererClients() {
        try {
            // Recuperation des clients
            ResultSet res = this.hotelModel.recupererClients();
            // Creation d'une liste de clients
            ArrayList<Client> clients = new ArrayList<>();
            while (res.next()) {
                // Ajout d'un client a la liste
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
    public boolean ajouterClient(boolean mode, String nom, String prenom, String cin, String telephone, String cb) {
        // Verification des valeurs entrees
        if (!nom.equals("") && !prenom.equals("") && !cin.equals("") && !telephone.equals("") && !cb.equals("")) {
            // Ajout d'un client et recuperation de son id
            int id = this.hotelModel.ajouterClient(nom, prenom, Integer.parseInt(cin), telephone, cb);
            if (id != 0) {
                // Ajout de l'id a la reservation en cours
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
     *
     * @return
     *          Liste de chambres
     */
    public ArrayList<Chambre> recupererChambres() {
        try {
            // Recuperation des chambres
            ResultSet res = this.hotelModel.recupererChambres();
            // Creation d'une liste de chambres
            ArrayList<Chambre> chambres = new ArrayList<>();
            while (res.next()) {
                // Ajout d'une chambre a la reservation en cours
                chambres.add(new Chambre(res.getInt(1), res.getString(2), res.getString(6), res.getString(4), res.getDouble(3), res.getDouble(5), res.getDouble(7)));
            }
            return chambres;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
