/**
 *  La classe CrudFrame ouvre la frame dans laquelle l'utilisateur
 remplit le formulaire ou le modifie en fonction du choix (Client ou Prospect)
 *
 * @author Mill Zuckergates
 * @version 1.0
 */

package com.stephane.frame;

import com.stephane.dao.DAO;
import com.stephane.dao.DAOClient;
import com.stephane.dao.DAOException;
import com.stephane.dao.DAOProspect;
import com.stephane.entity.*;
import com.stephane.exceptions.ReversoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import javax.swing.*;

import static com.stephane.logs.LoggerReverso.LOGGER;

public class CrudFrame extends JFrame {
    private JPanel crudPanel;
    private JTextField textFieldRaison;
    private JTextField textFieldNumero;
    private JTextField textFieldNomRue;
    private JTextField textFieldCodePostal;
    private JTextField textFieldVille;
    private JTextField textFieldTel;
    private JTextField textFieldEmail;
    private JTextField textFieldCommentaire;
    private JTextField textFieldDateProspection;
    private JTextField textFieldInteret;
    private JTextField textFieldId;
    private JButton validerButton;
    private JButton menuButton;
    private JLabel labelDateProspection;
    private JLabel labelInteret;
    private JLabel labelTitre;
    private JLabel labelId;
    private JTextField textFieldChiffreAffaires;
    private JTextField textFieldNombreEmployes;
    private JLabel labelChiffreAffaires;
    private JLabel labelNombreEmployes;
    private Societe choixSociete;
    private Choix choix;
    private Crud crud;

    /**
     * Constructeur de la frame
     * @param choix Variable de choix (Prospect ou Client)
     * @param choixSociete Variable du choix de la société choisie
     * @param crud Variable contenant une enum (AJOUTER, MODIFIER, SUPPRIMER...)
     */
    public CrudFrame(Choix choix, Societe choixSociete, Crud crud) {
        this.choix = choix;
        this.choixSociete = choixSociete;
        this.crud = crud;
        initComponent();
        affichage();
        actionListener();
    }

    /**
     * Initialisation de la frame avec ses paramètres.
     */
    public void initComponent() {
        // Paramètres de frame
        setContentPane(crudPanel);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        // Options
        labelId.setVisible(false);
        textFieldId.setVisible(false);
    }

    /**
     * Enregistre les données du client ou prospect
     * @param raisonSociale Le nom de l'entreprise
     * @param numeroRue Le numéro d'habitation
     * @param nomRue Le nom de la rue
     * @param codePostal Le code postal
     * @param ville Le nom de la ville
     * @param tel Le numéro de téléphone
     * @param email L'adresse email
     * @param commentaire Commentaire sur la société (facultatif)
     */
    private void setSociete(String raisonSociale, String numeroRue, String nomRue,
            String codePostal, String ville, String tel, String email,
            String commentaire) throws ReversoException {
            choixSociete.setRaisonSociale(raisonSociale);
            choixSociete.setNumRue(numeroRue);
            choixSociete.setRue(nomRue);
            choixSociete.setCodePostal(codePostal);
            choixSociete.setVille(ville);
            choixSociete.setTel(tel);
            choixSociete.setEmail(email);
            choixSociete.setCommentaire(commentaire);
    }

    /**
     * Affiche les données récupérées dans les champs du formulaire
     */
    private void affichageChamps() {
        labelId.setVisible(true);
        textFieldId.setVisible(true);
        textFieldId.setText(Integer.toString(choixSociete.getIdSociete()));;
        textFieldRaison.setText(choixSociete.getRaisonSociale());
        textFieldNumero.setText(choixSociete.getNumRue());
        textFieldNomRue.setText(choixSociete.getRue());
        textFieldCodePostal.setText(choixSociete.getCodePostal());
        textFieldVille.setText(choixSociete.getVille());
        textFieldTel.setText(choixSociete.getTel());
        textFieldEmail.setText(choixSociete.getEmail());
        textFieldCommentaire.setText(choixSociete.getCommentaire());
    }

    /**
     * Méthode d'affichage des données lors de l'ouverture de la frame pour
     l'ajout, la modification ou la supression d'une société.
     */
    public void affichage() {
        switch (choix) {
            case PROSPECTS:
                labelChiffreAffaires.setVisible(false);
                labelNombreEmployes.setVisible(false);
                textFieldChiffreAffaires.setVisible(false);
                textFieldNombreEmployes.setVisible(false);
                break;
            case CLIENTS:
                labelDateProspection.setVisible(false);
                labelInteret.setVisible(false);
                textFieldDateProspection.setVisible(false);
                textFieldInteret.setVisible(false);
                break;
        }

        switch (crud) {
            case MODIFIER:
            case SUPPRIMER:
                // Rend les champs impossible à editer
                if (crud == Crud.SUPPRIMER) {
                    labelTitre.setText("SUPPRIMER");
                    textFieldRaison.setEditable(false);
                    textFieldNumero.setEditable(false);
                    textFieldNomRue.setEditable(false);
                    textFieldCodePostal.setEditable(false);
                    textFieldVille.setEditable(false);
                    textFieldTel.setEditable(false);
                    textFieldEmail.setEditable(false);
                    textFieldCommentaire.setEditable(false);
                    textFieldNombreEmployes.setEditable(false);
                    textFieldChiffreAffaires.setEditable(false);
                    textFieldInteret.setEditable(false);
                    textFieldDateProspection.setEditable(false);
                }
                labelTitre.setText("MODIFIER");
                affichageChamps();
                switch (choix) {
                    case CLIENTS:
                        textFieldId.setEditable(false);
                        Client choixClient = (Client) choixSociete;
                        textFieldChiffreAffaires.setText(
                                Double.toString(choixClient.getChiffreAffaires()));
                        textFieldNombreEmployes.setText(
                                Integer.toString(choixClient.getNbEmployes()));
                        break;
                    case PROSPECTS:
                        textFieldId.setEditable(false);
                        Prospect choixProspect = (Prospect) choixSociete;
                        textFieldDateProspection.setText(
                                choixProspect.getDateProspection().toString());
                        textFieldInteret.setText(choixProspect.getInteret());
                        break;
                }
                break;
            case AJOUTER:
                labelTitre.setText("AJOUTER");
                break;
        }
    }

    /**
     * Clic sur le bouton de validation du formulaire
     */
    public void actionListener(){
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    // Récupération des données
                    String raisonSociale = textFieldRaison.getText();
                    String numeroRue = textFieldNumero.getText();
                    String nomRue = textFieldNomRue.getText();
                    String codePostal = textFieldCodePostal.getText();
                    String ville = textFieldVille.getText();
                    String tel = textFieldTel.getText();
                    String email = textFieldEmail.getText();
                    String commentaire = textFieldCommentaire.getText();

                    switch (crud) {
                        case AJOUTER:
                            switch (choix) {
                                // Traitement de l'ajout d'un prospect
                                case PROSPECTS:
                                    // Formattage de la date en format jour/mois/année
                                    DateTimeFormatter formatter =
                                            DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    // Remplace les / éventuels en -
                                    String replace =
                                            textFieldDateProspection.getText().replaceAll("-", "/");
                                    LocalDate dateProspection =
                                            LocalDate.parse(replace, formatter);
                                    String interet = textFieldInteret.getText();

                                    Prospect prospect = new Prospect();
                                    prospect.setRaisonSociale(raisonSociale);
                                    prospect.setNumRue(numeroRue);
                                    prospect.setRue(nomRue);
                                    prospect.setCodePostal(codePostal);
                                    prospect.setVille(ville);
                                    prospect.setTel(tel);
                                    prospect.setEmail(email);
                                    prospect.setCommentaire(commentaire);
                                    prospect.setDateProspection(dateProspection);
                                    prospect.setInteret(interet);
                                    DAOProspect daoProspect = new DAOProspect();

                                    daoProspect.save(null, prospect);
//

                                    // Ajoute le prospect dans la lise
                                    JOptionPane.showMessageDialog(
                                            null, "Le prospect a bien été ajouté");
                                    dispose();
                                    HomeFrame homeFrameProspects = new HomeFrame();
                                    break;
                                // Traitement de l'ajout d'un client
                                case CLIENTS:
                                    Double chiffreAffaires =
                                            Double.parseDouble(textFieldChiffreAffaires.getText());
                                    int nombreEmployes =
                                            Integer.parseInt(textFieldNombreEmployes.getText());
                                    Client client = new Client();
                                    client.setRaisonSociale(raisonSociale);
                                    client.setNumRue(numeroRue);
                                    client.setRue(nomRue);
                                    client.setCodePostal(codePostal);
                                    client.setVille(ville);
                                    client.setTel(tel);
                                    client.setEmail(email);
                                    client.setCommentaire(commentaire);
                                    client.setChiffreAffaires(chiffreAffaires);
                                    client.setNbEmployes(nombreEmployes);
                                    DAOClient daoClient = new DAOClient();

                                    daoClient.save(null, client);
                                    JOptionPane.showMessageDialog(
                                            null, "Le client a bien été ajouté");
                                    dispose();
                                    HomeFrame homeFrameClients = new HomeFrame();
                                    break;
                            }
                            break;
                        case MODIFIER:
                            setSociete(raisonSociale, numeroRue, nomRue, codePostal, ville,
                                    tel, email, commentaire);
                            switch (choix) {
                                case CLIENTS:
                                    Double chiffreAffaires =
                                            Double.parseDouble(textFieldChiffreAffaires.getText());
                                    int nombreEmployes =
                                            Integer.parseInt(textFieldNombreEmployes.getText());
                                    Client choixClient = (Client) choixSociete;
                                    choixClient.setChiffreAffaires(chiffreAffaires);
                                    choixClient.setNbEmployes(nombreEmployes);
                                    int clientId = choixClient.getIdSociete();
//                                    DAOClient.save(clientId, raisonSociale, numeroRue, nomRue,
//                                            codePostal, ville, tel, email, commentaire,
//                                            chiffreAffaires, nombreEmployes);

                                    JOptionPane.showMessageDialog(
                                            null, "Le client a bien été modifié");
                                    dispose();
                                    HomeFrame homeFrameClients = new HomeFrame();
                                    break;
                                case PROSPECTS:
                                    LocalDate dateProspection =
                                            LocalDate.parse(textFieldDateProspection.getText());
                                    String interet = textFieldInteret.getText();
                                    Prospect choixProspect = (Prospect) choixSociete;
                                    choixProspect.setDateProspection(dateProspection);
                                    choixProspect.setInteret(interet);
                                    int prospectId = choixProspect.getIdSociete();

//                                    DAOProspect.save(prospectId, raisonSociale, numeroRue, nomRue,
//                                            codePostal, ville, tel, email, commentaire,
//                                            dateProspection, interet);

                                    JOptionPane.showMessageDialog(
                                            null, "Le prospect a bien été modifié");
                                    dispose();
                                    HomeFrame homeFrameProspect = new HomeFrame();
                                    break;
                            }
                            break;
                        case SUPPRIMER:
                            int choixSupprimer = JOptionPane.showConfirmDialog(null,
                                    "Êtes-vous sûr de vouloir supprimer cette société ?",
                                    "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                            if (choixSupprimer == JOptionPane.YES_OPTION) {
                                switch (choix) {
                                    case CLIENTS:
                                        DAOClient daoClient = new DAOClient();
                                        daoClient.delete(Integer.parseInt(textFieldId.getText()));
                                    case PROSPECTS:
                                        DAOProspect daoProspect = new DAOProspect();
                                        daoProspect.delete(Integer.parseInt(textFieldId.getText()));
                                }
                                JOptionPane.showMessageDialog(
                                        null, "La société a bien été supprimée");
                                dispose();
                                HomeFrame homeFrame = new HomeFrame();
                            }
                    }
                }catch(NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(
                            null, "Erreur dans le format du numéro");
                }catch(DateTimeException de){
                    JOptionPane.showMessageDialog(null,"Le format de la date n'est pas correct");
                }catch(ReversoException re) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + re.getMessage());
                }catch(Exception e) {
                    JOptionPane.showMessageDialog(
                            null, "Une erreur est survenue. Contactez l'administrateur");
                        e.printStackTrace();
                }
            }
        });

        /**
         * Clic sur le bouton de retour au menu
         */
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeFrame homeFrame = new HomeFrame();
                dispose();
            }
        });
    }
}
