package com.stephane.frame;

import com.stephane.entity.Crud;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
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
    public static Crud choix;
    public static Crud crud;

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
                        ListFrame listFrameProspects = new ListFrame(Crud.PROSPECTS);
                        break;
                    case CLIENTS:
                        ListFrame listFrameClients = new ListFrame(Crud.CLIENTS);
                        break;
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
