package PApplicationGestionHotel.PHotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Reservation {

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

    public int calculNbJour() {
        long diff = this.date_fin.getTime() - this.date_debut.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
