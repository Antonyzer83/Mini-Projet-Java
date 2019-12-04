package PApplicationGestionHotel.PHotel;

import java.sql.*;
import java.io.File;
import org.ini4j.*;

public class HotelModel implements IHotelModel {

    private Connection connection;

    public HotelModel() {
        this.connection = this.connectBDD();
    }

    private Connection connectBDD() {
        try {
            Wini ini = new Wini(new File("src/PApplicationGestionHotel/PHotel/db.ini"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + ini.get("database", "address") + ":" + ini.get("database", "port") + "/" + ini.get("database", "table") + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
                    ini.get("database", "user"),
                    ini.get("database", "password")
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
                    "JOIN type t on ch.id_type = t.id_type;");
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Recuperer la totalite des chambres
     */
    public ResultSet recupererChambres() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery(" SELECT id_chambre, s.nom, c.nom, t.nom " +
                    "FROM chambre " +
                    "JOIN saison s on chambre.id_saison = s.id_saison " +
                    "JOIN categorie c on chambre.id_categorie = c.id_categorie " +
                    "JOIN type t on chambre.id_type = t.id_type;");
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
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
    public boolean supprimerReservation(int id_reservation) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE r, rc FROM reservation r " +
                    "JOIN reservation_chambre rc ON  rc.id_reservation = r.id_reservation " +
                    "WHERE r.id_reservation = ?;");
            stmt.setInt(1, id_reservation);
            return stmt.execute();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Recuperer une reservation specifique
     */
    public ResultSet recupererReservation(int id_reservation) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT reservation.id_reservation, nb_nuits, date_reservation, nb_personne, pension, date_debut, date_fin, t.nom, c.nom " +
                    "FROM reservation " +
                    "JOIN reservation_chambre rc on reservation.id_reservation = rc.id_reservation " +
                    "JOIN chambre ch on rc.id_chambre = ch.id_chambre " +
                    "JOIN categorie c on ch.id_categorie = c.id_categorie " +
                    "JOIN type t on ch.id_type = t.id_type " +
                    "WHERE reservation.id_reservation = ?;");
            stmt.setInt(1, id_reservation);
            ResultSet res = stmt.executeQuery();
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Recuperer les dates d'une chambre
     */
    public ResultSet recupererDateChambre(int id_reservation, int id_chambre) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT date_debut, date_fin " +
                    "FROM reservation_chambre " +
                    "WHERE id_chambre = ? AND id_reservation = ?;");
            stmt.setInt(1, id_chambre);
            stmt.setInt(2, id_reservation);
            ResultSet res = stmt.executeQuery();
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
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
    public ResultSet recupererClient(int id_client) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * " +
                    "FROM client " +
                    "WHERE id_client = ?");
            stmt.setInt(1, id_client);
            ResultSet res = stmt.executeQuery();
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
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
    public boolean ajouterClient(String nom, String prenom, int cin, String telephone) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO client(nom, prenom, cin, telephone) " +
                    "VALUES(?, ?, ?, ?);");
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setInt(3, cin);
            stmt.setString(4, telephone);
            return stmt.execute();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Recuperer la totalite des clients
     */
    public ResultSet recupererClients() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * " +
                    "FROM client;");
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Recuperer les chambres disponibles pendant une periode
     * @return
     */
    public ResultSet recupererChambresDispos(String date_debut, String date_fin) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT chambre.id_chambre, c.nom, c.pourcentage, t.nom, t.prix, s.nom, s.pourcentage " +
                    "FROM reservation_chambre, chambre " +
                    "JOIN type t on chambre.id_type = t.id_type " +
                    "JOIN categorie c on chambre.id_categorie = c.id_categorie " +
                    "JOIN saison s on chambre.id_saison = s.id_saison " +
                    "WHERE (? NOT BETWEEN date_debut AND date_fin) AND (? NOT BETWEEN date_debut AND date_fin) " +
                    "AND chambre.id_chambre = reservation_chambre.id_chambre " +
                    "OR chambre.id_chambre NOT IN (SELECT id_chambre FROM reservation_chambre);");
            stmt.setString(1, date_debut);
            stmt.setString(2, date_fin);
            ResultSet res = stmt.executeQuery();
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
