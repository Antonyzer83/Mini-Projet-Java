package PApplicationGestionHotel;

import java.sql.*;
import java.io.File;

import org.ini4j.*;

public class HotelModel implements IHotelModel {

    /**
     * Connexion a la BDD
     */
    private Connection connection;

    /**
     * Constructeur de la classe HotelModel
     *
     * Initialisation de la connexion avec la BDD
     */
    public HotelModel() {
        this.connection = this.connectBDD();
    }

    /**
     * Initialisation de la connexion a la BDD
     *
     * Lecture du db.ini
     *
     * @return
     *          Connexion avec la BDD
     */
    private Connection connectBDD() {
        try {
            // Lecture du db.ini
            Wini ini = new Wini(new File("resources/db.ini"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connexion a la BDD
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + ini.get("database", "address") + ":" + ini.get("database", "port") + "/" + ini.get("database", "table") + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
                    ini.get("database", "user"),
                    ini.get("database", "password")
            );
            return con;
        } catch (Exception e) {
            System.out.println("La connexion à la base de données a echoué :" + e);
            return null;
        }
    }

    /**
     * Recuperer la totalite des reservations
     *
     * @return
     *          Totalite des reservations
     */
    public ResultSet recupererReservations() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery("select reservation.id_reservation, nb_nuits, date_reservation, nb_personne, date_debut, date_fin, rc.id_chambre, s.nom as saison, s.pourcentage as prixSaison, c.nom as categorie, c.pourcentage as prixCategorie, t.nom as type, t.prix as prixCategorie, cl.id_client, cl.nom, prenom, cin, telephone, cb " +
                    "FROM reservation " +
                    "JOIN reservation_chambre rc on reservation.id_reservation = rc.id_reservation " +
                    "JOIN chambre ch on rc.id_chambre = ch.id_chambre " +
                    "JOIN saison s on ch.id_saison = s.id_saison " +
                    "JOIN categorie c on ch.id_categorie = c.id_categorie " +
                    "JOIN type t on ch.id_type = t.id_type " +
                    "JOIN client cl on cl.id_client = reservation.id_client;");
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Recuperer la totalite des chambres
     *
     * @return
     *          Totalite des chambres
     */
    public ResultSet recupererChambres() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery(" SELECT id_chambre, s.nom as saison, s.pourcentage as prixSaison, c.nom as categorie, c.pourcentage as prixCategorie, t.nom as type, t.prix as prixCategorie " +
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
    public int ajouterReservation(int nb_nuits, Date date_reservation, int nb_personne, int client_id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO reservation (nb_nuits, date_reservation, nb_personne, id_client) " +
                    "VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, nb_nuits);
            stmt.setDate(2, date_reservation);
            stmt.setInt(3, nb_personne);
            stmt.setInt(4, client_id);
            stmt.execute();
            ResultSet res = stmt.getGeneratedKeys();
            if (res.next()) {
                return res.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

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
    public boolean ajouterReservationChambre(int id_reservation, int id_chambre, Date date_debut, Date date_fin) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO reservation_chambre (id_reservation, id_chambre, date_debut, date_fin) " +
                    "VALUES(?, ?, ?, ?);");
            stmt.setInt(1, id_reservation);
            stmt.setInt(2, id_chambre);
            stmt.setDate(3, date_debut);
            stmt.setDate(4, date_fin);
            return stmt.execute();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Supprimer une reservation
     *
     * @param id_reservation
     *          Id de la reservation
     * @return
     *          Succes de la suppression de la reservation
     */
    public boolean supprimerReservation(int id_reservation) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE r, rc FROM reservation r " +
                    "JOIN reservation_chambre rc ON  rc.id_reservation = r.id_reservation " +
                    "WHERE r.id_reservation = ?;");
            stmt.setInt(1, id_reservation);
            stmt.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
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
     * Recuperer la totalite des clients
     *
     * @return
     *          Totalite des clients
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
    public int ajouterClient(String nom, String prenom, int cin, String telephone, String cb) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO client(nom, prenom, cin, telephone, cb) " +
                    "VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setInt(3, cin);
            stmt.setString(4, telephone);
            stmt.setString(5, cb);
            stmt.execute();
            ResultSet res = stmt.getGeneratedKeys();
            if (res.next()) {
                return res.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    /**
     * Recuperer la totalite des clients
     *
     * @return
     *          Totalite des clients
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
     *
     * @param date_debut
     *          Date de debut de la periode
     * @param date_fin
     *          Date de fin de la periode
     * @return
     *          Chambres disponibles pendant la periode
     */
    public ResultSet recupererChambresDispos(Date date_debut, Date date_fin) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT chambre.id_chambre, c.nom, c.pourcentage, t.nom, t.prix, s.nom, s.pourcentage " +
                    "FROM reservation_chambre, chambre " +
                    "JOIN type t on chambre.id_type = t.id_type " +
                    "JOIN categorie c on chambre.id_categorie = c.id_categorie " +
                    "JOIN saison s on chambre.id_saison = s.id_saison " +
                    "WHERE (? NOT BETWEEN date_debut AND date_fin) AND (? NOT BETWEEN date_debut AND date_fin) " +
                    "AND chambre.id_chambre = reservation_chambre.id_chambre " +
                    "OR chambre.id_chambre NOT IN (SELECT id_chambre FROM reservation_chambre)" +
                    "GROUP BY chambre.id_chambre;");
            stmt.setDate(1, date_debut);
            stmt.setDate(2, date_fin);
            ResultSet res = stmt.executeQuery();
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
