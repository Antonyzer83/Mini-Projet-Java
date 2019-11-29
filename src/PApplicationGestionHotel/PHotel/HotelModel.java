package PApplicationGestionHotel.PHotel;

import java.sql.*;

public class HotelModel implements IHotelModel {

    private Connection connection;

    public HotelModel() {
        this.connection = this.connectBDD();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from chambre");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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
    public void recupererReservations() {

    }

    /**
     * Recuperer la totalite des chambres
     */
    public void recupererChambres() {

    }

    /**
     * Ajouter une nouvelle reservation
     */
    public void ajouterReservation() {

    }

    /**
     * Supprimer une reservation
     */
    public void supprimerReservation() {

    }

    /**
     * Recuperer une reservation specifique
     */
    public void recupererReservation() {

    }

    /**
     * Recuperer les dates d'une chambres
     */
    public void recupererDateChambre() {

    }

    /**
     * Mettre a jour la date d'une chambre
     */
    public void MAJDateChambre() {

    }

    /**
     * Recuperer un client specifique
     */
    public void recupererClient() {

    }

    /**
     * Mettre a jour un client
     */
    public void MAJClient() {

    }

    /**
     * Ajouter un nouveau client
     */
    public void ajouterClient() {

    }

    /**
     * Recuperer la totalite des clients
     */
    public void recupererClients() {

    }
}
