package PApplicationGestionHotel;

import PApplicationGestionHotel.PHotel.HotelController;
import PApplicationGestionHotel.PHotel.HotelModel;
import PApplicationGestionHotel.PHotel.HotelView;

public class Main {

    public static void main(String[] args) {
        HotelModel hotelModel = new HotelModel();

        HotelController hotelController = new HotelController();

        HotelView hotelView = new HotelView();
    }
}
