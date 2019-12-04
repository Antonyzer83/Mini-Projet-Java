package PApplicationGestionHotel.PHotel;

import javax.swing.*;

public interface IHotelView {

    /**
     * Afficher le menu general de l'application
     */
    public void afficherMenu();

    /**
     * Afficher la totalite des reservations
     */
    public void afficherReservations();

    /**
     * Afficher une reservation specifique
     */
    public void afficherReservation();

    /**
     * Afficher la totalite des chambre dispos pour une periode donnee
     */
    public void afficherChambresDispos();

    /**
     * Afficher le recapitulatif pour l'ajout d'une reservation
     */
    public void afficherRecapitulatif();

    /**
     * Afficher succes d'une operation
     */
    public void afficherSucces();

    /**
     * Demander les dates MAJ d'une reservation
     */
    public void demanderDates();

    /**
     * Demander le client a choisir pour la reservation
     */
    public void demanderClient();

    /**
     * Demander la validation de l'annulation
     */
    public void checkAnnulerReservation();

    /**
     * Annuler une reservation
     */
    public void annulerReservation();

    /**
     * Recuperer les dates entrees
     */
    public void recupererDates();

    /**
     * Recuperer les chambres selectionnees pour une reservation
     */
    public void recupererChambresSelectionnees();

    /**
     * Valider une reservation
     */
    public void validerReservation();

    /**
     * Recuperer le client selectionne
     */
    public void recupererClient();

    /**
     * Afficher la totalite des clients
     */
    public JPanel afficherClients(boolean mode);

    /**
     * Afficher le formulaire pour l'ajout d'un client
     */
    public JPanel afficherFormulaireClient(boolean mode);

    /**
     * Recuperer le formulaire pour l'ajout d'un client
     */
    public void recupererFormulaireClient();

    /**
     * Afficher le formulaire pour la MAJ d'un client
     */
    public void afficherFormulaireClientMAJ();

    /**
     * Recuperer le client a mettre a jour
     */
    public void recupererClientMAJ();

    /**
     * Afficher la totalite des chambres
     */
    public void afficherChambres();
}
