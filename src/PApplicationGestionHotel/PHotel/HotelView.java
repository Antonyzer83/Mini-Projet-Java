package PApplicationGestionHotel.PHotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelView extends JFrame implements IHotelView {

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
                    break;
                case "Afficher les clients":
                    break;
                case "Afficher les chambres":
                    break;
            }
        }
    }
}
