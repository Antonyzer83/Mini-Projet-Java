package PApplicationGestionHotel.PHotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    /**
     * Nom du client
     */
    private JTextField nomField = new JTextField();

    /**
     * Prenom du client
     */
    private JTextField prenomField = new JTextField();

    /**
     * CIN du client
     */
    private JTextField cinField = new JTextField();

    /**
     * Numero de telephone du client
     */
    private JTextField telephoneField = new JTextField();

    /**
     * Numero de carte bancaire du client
     */
    private JTextField cbField = new JTextField();

    /**
     * Constructeur de la classe HotelView
     *
     * @param hotelController
     *          Controller
     */
    public HotelView(HotelController hotelController) {
        this.hotelController = hotelController;

        // Parametres de la fenetre
        setSize(400, 400);
        setTitle("Gestion Hotel");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Affichage du menu
        this.afficherMenu();

        setVisible(true);
    }

    /**
     * Afficher le menu general de l'application
     */
    public void afficherMenu() {
        // Parametres de la fenetre
        getContentPane().removeAll();
        setLayout(new GridLayout(2, 2));
        setSize(400, 400);

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

        repaint();
        validate();
    }

    /**
     * Afficher la totalite des reservations
     */
    public void afficherReservations() {
        // Recuperations des reservations a l'aide du controller
        ArrayList<Reservation> reservations = this.hotelController.recupererReservations();
        // Verification de la recuperation des reservations
        if (reservations != null) {
            // SUppression du contenu de la page
            getContentPane().removeAll();

            // Parametres de la fenetre
            setSize(400, 600);
            setLayout(new GridLayout(0, 1));

            // Panel global
            JPanel mainPanel = new JPanel(new GridLayout(0, 1));

            // Pour chaque reservation
            for (Reservation reservation : reservations) {
                JPanel panel = new JPanel(new GridLayout(0, 1));
                JPanel secondPanel = new JPanel(new GridLayout(2, 0));
                panel.setBorder(BorderFactory.createTitledBorder("Réservation n°" + reservation.id));

                // Affichage d'une reservation
                JLabel title = new JLabel(reservation.getName());
                JLabel client = new JLabel("Client : " + reservation.client);
                JLabel chambreTitle = new JLabel("Chambres : ");

                // Bouton pour annuler une reservation
                JButton button = new JButton("Annuler réservation");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        annulerReservation(reservation.id);
                    }
                });

                // Ajout des elements au panel
                panel.add(title);
                panel.add(client);
                panel.add(chambreTitle);

                // Affichage de chaque chambre
                for (String chambre : reservation.chambres) {
                    JLabel chambreLabel = new JLabel(chambre);
                    panel.add(chambreLabel);
                }

                // Ajout du bouton pour l'annulation
                panel.add(button);

                // Bouton pour le retour a l'accueil
                JButton button2 = new JButton("Retour menu");
                button2.addActionListener(new ButtonHandler());
                panel.add(button2);

                secondPanel.add(new JScrollPane(panel));

                mainPanel.add(secondPanel);
            }

            // Ajout des reservations a la page
            add(new JScrollPane(mainPanel));

            validate();
            repaint();
        }
    }

    /**
     * Afficher la totalite des chambre dispos pour une periode donnee
     */
    public void afficherChambresDispos() {
        // Recuperation des chambres disponibles pour une periode
        ArrayList<Chambre> chambres = this.hotelController.recupererChambresDipos(this.firstDate.getValue(), this.secondDate.getValue());
        // Verification du succes de la recuperation
        if (chambres != null) {
            // Parametres de la fenetre
            getContentPane().removeAll();
            setSize(400, 450);

            this.box = new JCheckBox[chambres.size()];
            int count = 0;

            // Recuperation de chaque chambre
            for (Chambre chambre : chambres) {
                this.box[count] = new JCheckBox(chambre.getName());
                add(this.box[count]);
                count++;
            }

            // Bouton pour l'ajout final des chambres
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

        // Affichage des elements de la reservation
        Reservation reservation = this.hotelController.recupererReservationEnCours();
        JLabel date_debut = new JLabel("Date d'arrivée : " + reservation.date_debut);
        JLabel date_fin = new JLabel("Date de départ : " + reservation.date_fin);
        JLabel label1 = new JLabel("Client : " + reservation.client);
        JLabel chambres = new JLabel("Chambres : ");

        // Ajout des elements a la fenetre
        add(date_debut);
        add(date_fin);
        add(label1);
        add(chambres);

        // Affichage de chaque chambre
        for (String chambre : reservation.chambres) {
            JLabel label = new JLabel(chambre);
            add(label);
        }

        // Bouton pour la validation finale de la reservation
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
        getContentPane().removeAll();

        JLabel label = new JLabel("Succès de l'opération");
        JButton button = new JButton("Retour menu");
        button.addActionListener(new ButtonHandler());

        add(label);
        add(button);

        repaint();
        validate();
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
        // Verification des chambres choisies
        if (this.hotelController.reserverChambres(this.box)) {
            // Parametres de la fenetre
            getContentPane().removeAll();
            setSize(500, 500);

            // Panel contenant les clients existants et un formulaire d'ajout
            JPanel container = new JPanel();
            container.add(this.afficherClients(true));
            container.add(this.afficherFormulaireClient(true, true));

            // Ajout du panel
            container.setLayout(new GridLayout(1, 2));
            add(container);

            validate();
            repaint();
        }
    }

    /**
     * Demander la validation de l'annulation
     *
     * @param id
     *          Id de la reservation a annuler
     */
    public void annulerReservation(int id) {
        // Verification du success de l'annulation
        if (this.hotelController.annulerReservation(id)) {
            // Creation d'une boite de dialogue
            JDialog dialog = new JDialog();
            dialog.setSize(200, 200);
            dialog.setTitle("Réservation supprimée");

            JOptionPane.showMessageDialog(dialog, "Vous avez supprimé la réservation n°" + id);
            this.afficherReservations();
        }
    }

    /**
     * Valider une reservation
     */
    public void validerReservation() {
        // Ajout de la reservation en cours
        this.hotelController.ajouterReservation();
        // Affichage du succes de l'operation
        this.afficherSucces();
    }

    /**
     * Recuperer le client selectionne
     */
    public void recupererClient() {
        // Verification du client choisi pour la reservation
        if (this.hotelController.reserverClient(this.bg)) {
            // Affichage du recapitulatif de la commande
            this.afficherRecapitulatif();
        }
    }

    /**
     * Afficher la totalite des clients
     *
     * @param mode
     *          Mode reservation ou affichage unique
     * @return
     *          Panel comportant les clients
     */
    public JPanel afficherClients(boolean mode) {
        // Recuperation des clients
        ArrayList<Client> clients = this.hotelController.recupererClients();
        // Verification du succes de la recuperation
        if (clients != null) {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JPanel secondPanel = new JPanel();

            // Mode reservation
            if (mode) {
                panel.setBorder(BorderFactory.createTitledBorder("Choisir un client."));
                secondPanel.setLayout(new GridLayout(2, 0));
                this.bg = new ButtonGroup();

                // Affichage de chaque client
                for (Client client : clients) {
                    JRadioButton jRadioButton = new JRadioButton(client.getName());
                    jRadioButton.setActionCommand(client.getName());
                    this.bg.add(jRadioButton);
                    panel.add(jRadioButton);
                }

                secondPanel.add(new JScrollPane(panel));

                // Bouton pour le choix du client
                JButton button = new JButton("Choisir Client");
                button.addActionListener(new ButtonHandler());
                secondPanel.add(button);

                return secondPanel;
            } else { // Mode affichage unique
                getContentPane().removeAll();

                // Affichage de chaque client
                for (Client client : clients) {
                    JLabel label = new JLabel(client.getName());
                    panel.add(label);
                }

                // Bouton pour l'ajout de client
                JButton button = new JButton("Ajouter client");
                button.addActionListener(new ButtonHandler());

                // Bouton pour le retour au menu
                JButton button2 = new JButton("Retour menu");
                button2.addActionListener(new ButtonHandler());

                // Ajout des boutons au panel
                panel.add(button);
                panel.add(button2);

                secondPanel.add(new JScrollPane(panel));

                add(secondPanel);

                validate();
                repaint();
                // Ajout direct a la fenetre donc return inutile
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Afficher le formulaire pour l'ajout d'un client
     *
     * @param mode
     *          Mode reservation ou affichage unique
     * @param buttonMode
     *          Mode du bouton
     * @return
     *          Panel comportant le formulaire
     */
    public JPanel afficherFormulaireClient(boolean mode, boolean buttonMode) {
        // Affichage lors reservation
        if (mode) {
            // Creation d'un panel
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 2));

            // Labels des informations
            JLabel nomLabel = new JLabel("Nom : ");
            JLabel prenomLabel = new JLabel("Prenom : ");
            JLabel cinLabel = new JLabel("CIN : ");
            JLabel telephoneLabel = new JLabel("Téléphone : ");
            JLabel cbLabel = new JLabel("CB : ");

            // Bouton pour la suite du processus -> Réservation / Classique
            JButton button = new JButton((buttonMode) ? "Nouveau client" : "Nouveau");
            button.addActionListener(new ButtonHandler());

            // Ajout des elements au panel
            panel.add(nomLabel);
            panel.add(this.nomField);
            panel.add(prenomLabel);
            panel.add(this.prenomField);
            panel.add(cinLabel);
            panel.add(this.cinField);
            panel.add(telephoneLabel);
            panel.add(this.telephoneField);
            panel.add(cbLabel);
            panel.add(this.cbField);
            panel.add(button);

            return panel;
        } else { // Affichage classique
            getContentPane().removeAll();

            // Ajout du formulaire a la fenetre
            add(this.afficherFormulaireClient(true, false));

            validate();
            repaint();
            return null;
        }
    }

    /**
     * Recuperer le formulaire pour l'ajout d'un client
     *
     * @param mode
     *          Reservation / Classique
     */
    public void recupererFormulaireClient(boolean mode) {
        // Verification de l'ajout d'un client
        if (this.hotelController.ajouterClient(mode, this.nomField.getText(), this.prenomField.getText(), this.cinField.getText(), this.telephoneField.getText(), this.cbField.getText())) {
            if (mode) { // Reservation
                this.afficherRecapitulatif();
            } else { // Classique
                this.afficherClients(false);
            }
        }
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
        // Recuperation des chambres
        ArrayList<Chambre> chambres = this.hotelController.recupererChambres();
        // Verification du succes de la recuperation
        if (chambres != null) {
            getContentPane().removeAll();
            JPanel panel = new JPanel(new GridLayout(16, 1));

            // Affichage des chambres
            for (Chambre chambre : chambres) {
                JLabel label = new JLabel(chambre.getName());
                panel.add(label);
            }

            // Bouton pour le retour au lenu
            JButton button2 = new JButton("Retour menu");
            button2.addActionListener(new ButtonHandler());
            button2.setSize(40, 40);

            // Ajout des elements a la fenetre
            panel.add(button2);
            add(panel);

            validate();
            repaint();
        }
    }

    class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Recuperation de la valeur du bouton
            String buttonText = ((JButton)  (e.getSource())).getText();

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
                case "Nouveau client":
                    recupererFormulaireClient(true);
                    break;
                case "Valider Réservation":
                    validerReservation();
                    break;
                case "Retour menu":
                    afficherMenu();
                    break;
                case "Ajouter client":
                    afficherFormulaireClient(false, false);
                    break;
                // Nouveau client hors de la reservation
                case "Nouveau":
                    recupererFormulaireClient(false);
            }
        }
    }
}
