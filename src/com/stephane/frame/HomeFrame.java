package com.stephane.frame;

import com.stephane.entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame{
    private JPanel homePanel;
    private JButton quitterButton;
    private JButton clientsButton;
    private JButton prospectButton;
    private JButton listeButton;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JComboBox comboBox;
    private JButton validerButton;
    private JPanel homePanelCrud;
    private JPanel homePanelListe;
    private JLabel choixLabel;
    private JLabel titreLabel;
    private JPanel choixPanel;
    private JButton menuPrincipalButton;
    private Choix choix;
    private Crud crud;
    private Societe choixSociete;


    public HomeFrame(){
        initComponent();
        actionListener();
    }

    public void initComponent(){
        // Paramètres de frame
        setContentPane(homePanel);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        // Options
        homePanelCrud.setVisible(false);
        homePanelListe.setVisible(false);
        titreLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        titreLabel.setForeground(Color.RED);
    }

    /**
     * Contient tous les listener
     */
    public void actionListener(){

        /**
         * Clic sur le bouton Prospect
         */
        prospectButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                choix = Choix.PROSPECTS;
                affichageCrud();
                choixLabel.setText("Gestion des prospects");
                homePanelListe.setVisible(false);
                // Réinitialisation de la combobox (évite l'affichage en double)
                comboBox.removeAllItems();
                // Remplissage de la combobox
                for(Prospect prospect : Prospects.getListProspects()){
                    comboBox.addItem(prospect.getRaisonSociale());
                }
            }
        });

        /**
         * Clic sur le bouton Clients
         */
        clientsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                choix = Choix.CLIENTS;
                affichageCrud();
                choixLabel.setText("Gestion des clients");
                homePanelListe.setVisible(false);
                // Réinitialisation de la combobox (évite l'affichage en double)
                comboBox.removeAllItems();
                // Remplissage de la combobox
                for(Client client : Clients.getListClients()){
                    comboBox.addItem(client.getRaisonSociale());
                }
            }
        });

        /**
         * Clic sur le bouton Liste
         */
        listeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switch(choix){
                    case PROSPECTS:
                        ListFrame listProspects = new ListFrame(Choix.PROSPECTS);
                        dispose();
                        break;
                    case CLIENTS:
                        ListFrame listClients = new ListFrame(Choix.CLIENTS);
                        dispose();
                        break;
                }
            }
        });

        /**
         * Clic sur le bouton Ajouter
         */
        ajouterButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switch(choix){
                    case PROSPECTS:
                        crud = Crud.AJOUTER;
                        CrudFrame crudProspects = new CrudFrame(Choix.PROSPECTS, choixSociete, crud);
                        dispose();
                        break;
                    case CLIENTS:
                        crud = Crud.AJOUTER;
                        CrudFrame crudClients = new CrudFrame(Choix.CLIENTS, choixSociete, crud);
                        dispose();
                        break;
                    }
            }
        });

        /**
         * Clic sur le bouton Modifier
         */
        modifierButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                homePanelListe.setVisible(true);
                validerButton.setText("Modifier");
                crud = Crud.MODIFIER;
            }
        });

        /**
         * Clic sur le bouton Supprimer
         */
        supprimerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                homePanelListe.setVisible(true);
                validerButton.setText("Supprimer");
                crud = Crud.SUPPRIMER;
            }
        });

        /**
         * Clic sur le bouton Valider
         */
        validerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switch(crud){
                    case MODIFIER:
                        if(crud == Crud.MODIFIER){
                            validerButton.getText().equals("Modifier");
                        }
                            int index = comboBox.getSelectedIndex();
                            switch(choix){
                                case PROSPECTS:
                                    choixSociete = Prospects.getListProspects().get(index);
                                    dispose();
                                    break;
                                case CLIENTS:
                                    choixSociete = Clients.getListClients().get(index);
                                    dispose();
                                    break;
                            }
                            CrudFrame crudModifierSupprimer = new CrudFrame(choix, choixSociete, crud);
                        }
//                if(validerButton.getText().equals("Supprimer")){
//                    CrudFrame crudSupprimer = new CrudFrame(choix, choixSociete);
//                    dispose();
//                }
            }
        });

        quitterButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

    }

    /**
     * Affichage dynamique selon le choix
     */
    public void affichageCrud(){
        switch(choix){
            case PROSPECTS:
                System.out.println("Prospects");
                homePanelCrud.setVisible(true);
                break;

            case CLIENTS:
                System.out.println("Clients");
                homePanelCrud.setVisible(true);
                break;
        }

    };

}
