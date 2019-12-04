package PApplicationGestionHotel.PHotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class HotelView extends JFrame implements IHotelView {

    /**
     * HotelController
     */
    private HotelController hotelController;

    /**
     * Premiere date pour la reservation
     */
    private JSpinner firstDate;

    /**
     * Seconde date pour la reservation
     */
    private JSpinner secondDate;

    /**
     * Groupe de boutons
     */
    private ButtonGroup bg;

    /**
     * Tableau des checkboxs pour la selection des chambres;
     */
    private JCheckBox[] box;

    public HotelView(HotelController hotelController) {
        this.hotelController = hotelController;

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
    public void afficherMenu() {
        // Création de boutons pour chaque option
        JButton firstButton = new JButton("Ajouter une réservation");
        firstButton.addActionListener(new ButtonHandler());
        JButton secondButton = new JButton("Afficher les réservations");
        secondButton.addActionListener(new ButtonHandler());
        JButton thirdButton = new JButton("Afficher les clients");
        thirdButton.addActionListener(new ButtonHandler());
        JButton fourthButton = new JButton("Afficher les chambres");
        fourthButton.addActionListener(new ButtonHandler());

        // Ajout des boutons a la fenetre
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
    public void afficherReservations() {

    }

    /**
     * Afficher une reservation specifique
     */
    public void afficherReservation() {

    }

    /**
     * Afficher la totalite des chambre dispos pour une periode donnee
     */
    public void afficherChambresDispos() {
        // Recuperation des chambres disponibles pour une periode
        ArrayList<Chambre> chambres = hotelController.recupererChambresDipos(this.firstDate.getValue(), this.secondDate.getValue());
        if (chambres != null) {
            getContentPane().removeAll();
            setSize(400, 450);

            this.box = new JCheckBox[chambres.size()];
            int count = 0;
            for (Chambre chambre : chambres) {
                this.box[count] = new JCheckBox(chambre.getName());
                add(this.box[count]);
                count++;
            }

            JButton button = new JButton("Valider les chambres");
            button.addActionListener(new ButtonHandler());
            add(button);

            validate();
            repaint();
        }
    }

    /**
     * Afficher le recapitulatif pour l'ajout d'une reservation
     */
    public void afficherRecapitulatif() {
        getContentPane().removeAll();

        Reservation reservation = this.hotelController.recupererReservationEnCours();
        JLabel date_debut = new JLabel("Date d'arrivée : " + reservation.date_debut);
        JLabel date_fin = new JLabel("Date de départ : " + reservation.date_fin);
        JLabel label1 = new JLabel("Client : " + reservation.client);
        JLabel chambres = new JLabel("Chambres : ");

        add(date_debut);
        add(date_fin);
        add(label1);
        add(chambres);
        for (String chambre : reservation.chambres) {
            JLabel label = new JLabel(chambre);
            add(label);
        }

        JButton button = new JButton("Valider Réservation");
        button.addActionListener(new ButtonHandler());

        add(button);

        validate();
        repaint();
    }

    /**
     * Afficher succes d'une operation
     */
    public void afficherSucces() {

    }

    /**
     * Demander les dates MAJ d'une reservation
     */
    public void demanderDates() {
        // MAJ fenetre
        getContentPane().removeAll();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(200, 200);

        // Creation du format pour la date
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        Date earlyDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date lastestDate = calendar.getTime();
        SpinnerModel model = new SpinnerDateModel(initDate, earlyDate, lastestDate, Calendar.YEAR);
        SpinnerModel model2 = new SpinnerDateModel(initDate, earlyDate, lastestDate, Calendar.YEAR);

        // Creation elements de la page
        JLabel firstLabel = new JLabel("Date d'arrivée : ");
        JLabel secondLabel = new JLabel("Date de départ : ");
        this.firstDate = new JSpinner(model);
        this.secondDate = new JSpinner(model2);
        this.firstDate.setEditor(new JSpinner.DateEditor(this.firstDate, "dd/MM/yyyy"));
        this.secondDate.setEditor(new JSpinner.DateEditor(this.secondDate, "dd/MM/yyyy"));

        // Creation du bouton et de son evenement
        JButton button = new JButton("Rechercher");
        button.addActionListener(new ButtonHandler());

        // Ajout des elements a la fenetre
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
    public void demanderClient() {
        if (this.hotelController.reserverChambres(this.box)) {
            getContentPane().removeAll();
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            setSize(400, 400);

            add(this.afficherClients(true));
            add(this.afficherFormulaireClient(true));

            validate();
            repaint();
        }
    }

    /**
     * Demander la validation de l'annulation
     */
    public void checkAnnulerReservation() {

    }

    /**
     * Annuler une reservation
     */
    public void annulerReservation() {

    }

    /**
     * Recuperer les dates entrees
     */
    public void recupererDates() {

    }

    /**
     * Recuperer les chambres selectionnees pour une reservation
     */
    public void recupererChambresSelectionnees() {

    }

    /**
     * Valider une reservation
     */
    public void validerReservation() {
        this.hotelController.ajouterReservation();
    }

    /**
     * Recuperer le client selectionne
     */
    public void recupererClient() {
        if (this.hotelController.reserverClient(this.bg)) {
            this.afficherRecapitulatif();
        }
        System.out.println(this.bg.getSelection().getActionCommand());
    }

    /**
     * Afficher la totalite des clients
     */
    public JPanel afficherClients(boolean mode) {
        ArrayList<Client> clients = this.hotelController.recupererClients();
        if (clients != null) {
            JPanel panel = new JPanel();
            this.bg = new ButtonGroup();
            for (Client client : clients) {
                if (mode) {
                    JRadioButton jRadioButton = new JRadioButton(client.getName());
                    jRadioButton.setActionCommand(client.getName());
                    bg.add(jRadioButton);
                    panel.add(jRadioButton);
                } else {
                    JLabel label = new JLabel(client.getName());
                    add(label);
                }
            }
            if (mode) {
                JButton button = new JButton("Choisir Client");
                button.addActionListener(new ButtonHandler());
                panel.add(button);
            }
            return panel;
        } else {
            return null;
        }
    }

    /**
     * Afficher le formulaire pour l'ajout d'un client
     */
    public JPanel afficherFormulaireClient(boolean mode) {
        if (mode) {
            return new JPanel();
        } else {
            return new JPanel();
        }
    }

    /**
     * Recuperer le formulaire pour l'ajout d'un client
     */
    public void recupererFormulaireClient() {

    }

    /**
     * Afficher le formulaire pour la MAJ d'un client
     */
    public void afficherFormulaireClientMAJ() {

    }

    /**
     * Recuperer le client a mettre a jour
     */
    public void recupererClientMAJ() {

    }

    /**
     * Afficher la totalite des chambres
     */
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
                    afficherClients(false);
                    break;
                case "Afficher les chambres":
                    afficherChambres();
                    break;
                case "Rechercher":
                    afficherChambresDispos();
                    break;
                case "Valider les chambres":
                    demanderClient();
                    break;
                case "Choisir Client":
                    recupererClient();
                    break;
                case "Valider Réservation":
                    validerReservation();
                    break;
            }
        }
    }
}
