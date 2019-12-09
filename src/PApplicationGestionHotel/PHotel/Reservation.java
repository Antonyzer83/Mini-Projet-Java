package PApplicationGestionHotel.PHotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Reservation {

    public int id = 0;

    public int nb_nuits = 0;

    public int nb_personnes = 0;

    public Date date_reservation = new Date();

    public Date date_debut = new Date();

    public Date date_fin = new Date();

    public List<String> chambres;

    public String client;

    public Reservation() {
        this.chambres = new ArrayList<String>();
    }

    public void ajouterChambre(String chambre) {
        this.chambres.add(chambre);
    }

    public void ajouterClient(String client) {
        this.client = client;
    }

    public void ajouterDetails(int nb_nuits, Date date_reservation, int nb_personnes, Date date_debut, Date date_fin) {
        this.nb_nuits = nb_nuits;
        this.date_reservation = date_reservation;
        this.nb_personnes = nb_personnes;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public void ajouterId(int id) {
        this.id = id;
    }

    public int calculNbJour() {
        long diff = this.date_fin.getTime() - this.date_debut.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String getName() {
        return this.id + " : du " + this.date_debut + " au " + this.date_fin + "(" + this.nb_nuits + ") pour " + this.nb_personnes + " personnes";
    }
}
