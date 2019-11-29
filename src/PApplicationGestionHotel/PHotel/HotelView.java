package PApplicationGestionHotel.PHotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HotelView extends JFrame implements IHotelView {

    private JSpinner firstDate;

    private JSpinner secondDate;

    public HotelView() {
        setLayout(new GridLayout(2, 2));
        setSize(400, 400);
        setTitle("Gestion Hotel");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.afficherMenu();

        setVisible(true);
    }

    /**
     * Afficher le menu general de l'application
     */
    @Override
    public void afficherMenu() {
        JButton firstButton = new JButton("Ajouter une réservation");
        firstButton.addActionListener(new ButtonHandler());
        JButton secondButton = new JButton("Afficher les réservations");
        secondButton.addActionListener(new ButtonHandler());
        JButton thirdButton = new JButton("Afficher les clients");
        thirdButton.addActionListener(new ButtonHandler());
        JButton fourthButton = new JButton("Afficher les chambres");
        fourthButton.addActionListener(new ButtonHandler());

        add(firstButton);
        add(secondButton);
        add(thirdButton);
        add(fourthButton);
    }

    public void refresh() {
        getContentPane().removeAll();
        validate();
        repaint();
    }

    /**
     * Afficher la totalite des reservations
     */
    @Override
    public void afficherReservations() {

    }

    /**
     * Afficher une reservation specifique
     */
    @Override
    public void afficherReservation() {

    }

    /**
     * Afficher la totalite des chambre dispos pour une periode donnee
     */
    @Override
    public void afficherChambresDispos() {
    }

    /**
     * Afficher le recapitulatif pour l'ajout d'une reservation
     */
    @Override
    public void afficherRecapitulatif() {

    }

    /**
     * Afficher succes d'une operation
     */
    @Override
    public void afficherSucces() {

    }

    /**
     * Demander les dates MAJ d'une reservation
     */
    @Override
    public void demanderDates() {
        // Update Frame
        getContentPane().removeAll();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(200, 200);

        // Create Date format
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        Date earlyDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date lastestDate = calendar.getTime();
        SpinnerModel model = new SpinnerDateModel(initDate, earlyDate, lastestDate, Calendar.YEAR);
        SpinnerModel model2 = new SpinnerDateModel(initDate, earlyDate, lastestDate, Calendar.YEAR);

        // Create elements
        JLabel firstLabel = new JLabel("Date d'arrivée : ");
        JLabel secondLabel = new JLabel("Date de départ : ");
        this.firstDate = new JSpinner(model);
        this.secondDate = new JSpinner(model2);
        this.firstDate.setEditor(new JSpinner.DateEditor(this.firstDate, "dd/MM/yyyy"));
        this.secondDate.setEditor(new JSpinner.DateEditor(this.secondDate, "dd/MM/yyyy"));

        // Create button and event
        JButton button = new JButton("Rechercher");
        button.addActionListener(new ButtonHandler());

        // Add elements to frame
        add(firstLabel);
        add(this.firstDate);
        add(secondLabel);
        add(this.secondDate);
        add(button);

        validate();
        repaint();
    }

    /**
     * Demander le client a choisir pour la reservation
     */
    @Override
    public void demanderClient() {

    }

    /**
     * Demander la validation de l'annulation
     */
    @Override
    public void checkAnnulerReservation() {

    }

    /**
     * Annuler une reservation
     */
    @Override
    public void annulerReservation() {

    }

    /**
     * Recuperer les dates entrees
     */
    @Override
    public void recupererDates() {

    }

    /**
     * Recuperer les chambres selectionnees pour une reservation
     */
    @Override
    public void recupererChambresSelectionnees() {

    }

    /**
     * Valider une reservation
     */
    @Override
    public void validerReservation() {

    }

    /**
     * Recuperer le client selectionne
     */
    @Override
    public void recupererClient() {

    }

    /**
     * Afficher la totalite des clients
     */
    @Override
    public void afficherClients() {

    }

    /**
     * Afficher le formulaire pour l'ajout d'un client
     */
    @Override
    public void afficherFormulaireClient() {

    }

    /**
     * Recuperer le formulaire pour l'ajout d'un client
     */
    @Override
    public void recupererFormulaireClient() {

    }

    /**
     * Afficher le formulaire pour la MAJ d'un client
     */
    @Override
    public void afficherFormulaireClientMAJ() {

    }

    /**
     * Recuperer le client a mettre a jour
     */
    @Override
    public void recupererClientMAJ() {

    }

    /**
     * Afficher la totalite des chambres
     */
    @Override
    public void afficherChambres() {

    }

    class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton)  (e.getSource())).getText();
            System.out.println(buttonText);

            switch (buttonText) {
                case "Ajouter une réservation":
                    demanderDates();
                    break;
                case "Afficher les réservations":
                    afficherReservations();
                    break;
                case "Afficher les clients":
                    afficherClients();
                    break;
                case "Afficher les chambres":
                    afficherChambres();
                    break;
                case "Rechercher":
                    System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(firstDate.getValue()));
                    System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(secondDate.getValue()));
                    afficherChambresDispos();
            }
        }
    }
}
