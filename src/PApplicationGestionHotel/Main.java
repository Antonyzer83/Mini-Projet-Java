package PApplicationGestionHotel;

public class Main {

    public static void main(String[] args) {
        HotelModel hotelModel = new HotelModel();

        HotelController hotelController = new HotelController(hotelModel);

        HotelView hotelView = new HotelView(hotelController);
    }
}
