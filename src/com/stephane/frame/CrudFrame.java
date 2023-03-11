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

    public CrudFrame(Crud choix){
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
        switch(HomeFrame.choix){
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
    }

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
                    // Variables pour prospects
                    LocalDate dateProspection = LocalDate.now();
                    String interet = textFieldInteret.getText();

                    switch(HomeFrame.crud){
                        case AJOUTER:
                            switch(HomeFrame.choix){
                                case PROSPECTS:
                                    Prospect prospect = new Prospect(raisonSociale,numeroRue,nomRue,codePostal,ville,tel,email,commentaire,dateProspection,interet);
                                    Prospects.getListProspects().add(prospect);
                                    JOptionPane.showMessageDialog(null,"Le prospect a bien été ajouté");
                                    dispose();
                                    break;
                                case CLIENTS:
                                    Double chiffreAffaires = Double.parseDouble(textFieldChiffreAffaires.getText());
                                    int nombreEmployes = Integer.parseInt(textFieldNombreEmployes.getText());
                                    Client client = new Client(raisonSociale,numeroRue,nomRue,codePostal,ville,tel,email,commentaire,chiffreAffaires,nombreEmployes);
                                    Clients.getListClients().add(client);
                                    JOptionPane.showMessageDialog(null,"Le client a bien été ajouté");
                                    dispose();
                                    break;
                                case MODIFIER:

                                    break;
                                case SUPPRIMER:

                                    break;
                            }
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
