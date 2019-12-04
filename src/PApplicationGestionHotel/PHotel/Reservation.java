package PApplicationGestionHotel.PHotel;

import java.util.ArrayList;
import java.util.List;

public class Reservation {

    public List<Integer> chambres;

    public Reservation() {
        this.chambres = new ArrayList<Integer>();
    }

    public void ajouterChambre(int chambre) {
        this.chambres.add(chambre);
    }
}
