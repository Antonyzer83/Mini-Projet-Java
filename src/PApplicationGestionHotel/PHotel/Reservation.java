package PApplicationGestionHotel.PHotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Reservation {

    /**
     * Id de la reservation
     */
    public int id = 0;

    /**
     * Nombre de nuits
     */
    public int nb_nuits = 0;

    /**
     * Nombre de personnes
     */
    public int nb_personnes = 0;

    /**
     * Date de la reservation
     */
    public Date date_reservation = new Date();

    /**
     * Date de debut de la reservation
     */
    public Date date_debut = new Date();

    /**
     * Date de fin de la reservation
     */
    public Date date_fin = new Date();

    /**
     * Liste des chambres pour la reservation
     */
    public List<String> chambres;

    /**
     * Client assigne a la chambre
     */
    public String client;

    /**
     * Constructeur de la classe Reservation
     */
    public Reservation() {
        this.chambres = new ArrayList<String>();
    }

    /**
     * Ajouter une chambre a la reservation
     *
     * @param chambre
     *          Nouvelle chambre
     */
    public void ajouterChambre(String chambre) {
        this.chambres.add(chambre);
    }

    /**
     * Ajouter un client a la chambre
     *
     * @param client
     *          Nouveau client
     */
    public void ajouterClient(String client) {
        this.client = client;
    }

    /**
     * Ajouter des details a la reservation
     *
     * @param nb_nuits
     *          Nombre de nuits reservees
     * @param date_reservation
     *          Date de la reservation
     * @param nb_personnes
     *          Nombre de personnes
     * @param date_debut
     *          Date de debut de la reservation
     * @param date_fin
     *          Date de fin de la reservation
     */
    public void ajouterDetails(int nb_nuits, Date date_reservation, int nb_personnes, Date date_debut, Date date_fin) {
        this.nb_nuits = nb_nuits;
        this.date_reservation = date_reservation;
        this.nb_personnes = nb_personnes;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    /**
     * Ajouter l'id de la reservation
     *
     * @param id
     *          Id de la reservation
     */
    public void ajouterId(int id) {
        this.id = id;
    }

    /**
     * Calcul du nombre de nuits reservees
     *
     * @return
     *          Nombre de nuits
     */
    public int calculNbJour() {
        long diff = this.date_fin.getTime() - this.date_debut.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * Recuperation des details de la reservation
     *
     * @return
     *          Details de la reservation
     */
    public String getName() {
        return this.id + " : du " + this.date_debut + " au " + this.date_fin + "(" + this.nb_nuits + ") pour " + this.nb_personnes + " personnes";
    }
}
