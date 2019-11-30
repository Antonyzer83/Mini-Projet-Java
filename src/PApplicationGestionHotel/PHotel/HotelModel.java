package PApplicationGestionHotel.PHotel;

import java.sql.*;

public class HotelModel implements IHotelModel {

    private Connection connection;

    public HotelModel() {
        this.connection = this.connectBDD();
    }

    private Connection connectBDD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
                    "root",
                    "root"
            );
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Recuperer la totalite des reservations
     */
    public ResultSet recupererReservations() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery("select reservation.id_reservation, nb_nuits, date_reservation, nb_personne, pension, date_debut, date_fin, t.nom, c.nom " +
                    "FROM reservation " +
                    "JOIN reservation_chambre rc on reservation.id_reservation = rc.id_reservation " +
                    "JOIN chambre ch on rc.id_chambre = ch.id_chambre " +
                    "JOIN categorie c on ch.id_categorie = c.id_categorie " +
                    "JOIN type t on ch.id_type = t.id_type");
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Recuperer la totalite des chambres
     */
    public void recupererChambres() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Ajouter une nouvelle reservation
     */
    public void ajouterReservation() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Supprimer une reservation
     */
    public void supprimerReservation() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Recuperer une reservation specifique
     */
    public void recupererReservation() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Recuperer les dates d'une chambres
     */
    public void recupererDateChambre() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Mettre a jour la date d'une chambre
     */
    public void MAJDateChambre() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Recuperer un client specifique
     */
    public void recupererClient() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Mettre a jour un client
     */
    public void MAJClient() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Ajouter un nouveau client
     */
    public void ajouterClient() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Recuperer la totalite des clients
     */
    public void recupererClients() {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
