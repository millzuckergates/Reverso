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
    public static Crud choix;
    public static Crud crud;

    public HomeFrame(){
        initComponent();
        actionListener();
        System.out.println(Clients.getListClients());
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
                choix = Crud.PROSPECTS;
                affichageCrud();
                choixLabel.setText("Gestion des prospects");
                homePanelListe.setVisible(false);
                comboBox.removeAllItems();
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
                choix = Crud.CLIENTS;
                affichageCrud();
                choixLabel.setText("Gestion des clients");
                homePanelListe.setVisible(false);
                comboBox.removeAllItems();
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
                        ListFrame listProspects = new ListFrame(Crud.PROSPECTS);
                        break;
                    case CLIENTS:
                        ListFrame listClients = new ListFrame(Crud.CLIENTS);
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
                    CrudFrame crudProspects = new CrudFrame(Crud.PROSPECTS);
                    break;
                case CLIENTS:
                    CrudFrame crudClients = new CrudFrame(Crud.CLIENTS);
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
            }
        });

        /**
         * Clic sur le bouton Valider
         */
        validerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(validerButton.getText().equals("Modifier")){
                    CrudFrame crudModifier = new CrudFrame(choix);
                    System.out.println(choix);
                }
                if(validerButton.getText().equals("Supprimer")){
                    CrudFrame crudSupprimer = new CrudFrame(choix);
                    System.out.println(choix);
                }
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
