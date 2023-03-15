package com.stephane.frame;

import com.stephane.entity.*;
import com.stephane.exceptions.ReversoException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CrudFrame extends JFrame{
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
    private JButton validerButton;
    private JButton menuButton;
    private JLabel labelDateProspection;
    private JLabel labelInteret;
    private JLabel labelTitre;
    private JLabel textFieldId;
    private JLabel labelId;
    private JTextField textFieldChiffreAffaires;
    private JTextField textFieldNombreEmployes;
    private JLabel labelChiffreAffaires;
    private JLabel labelNombreEmployes;
    private Societe choixSociete;
    private Choix choix;
    private Crud crud;

    public CrudFrame(Choix choix, Societe choixSociete, Crud crud){
        this.choix = choix;
        this.choixSociete = choixSociete;
        this.crud = crud;
        initComponent();
        affichage();
        actionListener();
    }

    public void initComponent(){
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

    public void affichage(){
        switch(choix){
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

        switch(crud){
            case MODIFIER:
                labelId.setVisible(true);
                textFieldId.setVisible(true);
                textFieldRaison.setText(choixSociete.getRaisonSociale());
                textFieldNumero.setText(choixSociete.getNumRue());
                textFieldNomRue.setText(choixSociete.getRue());
                textFieldCodePostal.setText(choixSociete.getCodePostal());
                textFieldVille.setText(choixSociete.getVille());
                textFieldTel.setText(choixSociete.getTel());
                textFieldEmail.setText(choixSociete.getEmail());
                textFieldCommentaire.setText(choixSociete.getCommentaire());
                switch(choix){
                    case CLIENTS:
                            Client choixClient = (Client)choixSociete;
                            textFieldChiffreAffaires.setText(Double.toString(choixClient.getChiffreAffaires()));
                            textFieldNombreEmployes.setText(Integer.toString(choixClient.getNbEmployes()));
                            break;
                    case PROSPECTS:
                        Prospect choixProspect = (Prospect)choixSociete;
                            textFieldDateProspection.setText(choixProspect.getDateProspection().toString());
                            textFieldInteret.setText(choixProspect.getInteret());
                            break;
                }
            case AJOUTER:
                switch(choix){
                    case CLIENTS:
                        labelTitre.setText("Ajouter un client");
                        break;
                    case PROSPECTS:
                        labelTitre.setText("Ajouter un prospect");
                        break;
                }
        }
    }

    /**
     * Clic sur le bouton de validation du formulaire
     */
    public void actionListener(){
        validerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    // Récupération des données
                    String raisonSociale = textFieldRaison.getText();
                    String numeroRue = textFieldNumero.getText();
                    String nomRue = textFieldNomRue.getText();
                    String codePostal = textFieldCodePostal.getText();
                    String ville = textFieldVille.getText();
                    String tel = textFieldTel.getText();
                    String email = textFieldEmail.getText();
                    String commentaire = textFieldCommentaire.getText();

                    switch(crud){
                        case AJOUTER:
                            switch(choix){
                                case PROSPECTS:
                                    Prospect.setIdProspect(Prospect.getIdProspect()+1);
                                    LocalDate dateProspection = LocalDate.parse(textFieldDateProspection.getText());
                                    String interet = textFieldInteret.getText();
                                    Prospect prospect = new Prospect(raisonSociale,numeroRue,nomRue,codePostal,ville,tel,email,commentaire,dateProspection,interet);
                                    Prospects.getListProspects().add(prospect);
                                    JOptionPane.showMessageDialog(null,"Le prospect a bien été ajouté");
                                    System.out.println(prospect.getDateProspection());
                                    dispose();
                                    HomeFrame homeFrameProspects = new HomeFrame();
                                    break;
                                case CLIENTS:
                                    Client.setIdClient(Client.getIdClient()+1);
                                    Double chiffreAffaires = Double.parseDouble(textFieldChiffreAffaires.getText());
                                    int nombreEmployes = Integer.parseInt(textFieldNombreEmployes.getText());
                                    Client client = new Client(raisonSociale,numeroRue,nomRue,codePostal,ville,tel,email,commentaire,chiffreAffaires,nombreEmployes);
                                    Clients.getListClients().add(client);
                                    JOptionPane.showMessageDialog(null,"Le client a bien été ajouté");
                                    dispose();
                                    HomeFrame homeFrameClients = new HomeFrame();
                                    break;
                            }
                            break;
                        case MODIFIER:
                            choixSociete.setRaisonSociale(raisonSociale);
                            choixSociete.setNumRue(numeroRue);
                            choixSociete.setRue(nomRue);
                            choixSociete.setCodePostal(codePostal);
                            choixSociete.setVille(ville);
                            choixSociete.setTel(tel);
                            choixSociete.setEmail(email);
                            choixSociete.setCommentaire(commentaire);
                            switch(choix){
                                case CLIENTS:
                                    Double chiffreAffaires = Double.parseDouble(textFieldChiffreAffaires.getText());
                                    int nombreEmployes = Integer.parseInt(textFieldNombreEmployes.getText());
                                        Client choixClient = (Client)choixSociete;
                                        choixClient.setChiffreAffaires(chiffreAffaires);
                                        choixClient.setNbEmployes(nombreEmployes);
                                    JOptionPane.showMessageDialog(null,"Le client a bien été modifié");
                                    dispose();
                                    HomeFrame homeFrameClients = new HomeFrame();
                                    break;
                                case PROSPECTS:
                                    LocalDate dateProspection = LocalDate.parse(textFieldDateProspection.getText());
                                    String interet = textFieldInteret.getText();
                                        Prospect choixProspect = (Prospect)choixSociete;
                                        choixProspect.setDateProspection(dateProspection);
                                        choixProspect.setInteret(interet);
                                    JOptionPane.showMessageDialog(null,"Le prospect a bien été modifié");
                                    dispose();
                                    HomeFrame homeFrameProspect = new HomeFrame();
                                    break;
                            }
                        break;
                    }
                }catch(ReversoException re){
                    JOptionPane.showMessageDialog(null,"Erreur : " + re.getMessage());
                }
            }
        });

        menuButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                HomeFrame homeFrame = new HomeFrame();
                dispose();
            }
        });
    }
}
