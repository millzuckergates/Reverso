/**
 *  La classe HomeFrame ouvre la frame d'accueil qui donne accès aux differentes
 actions possibles (Ajouter, modifier, supprimer un client/prospect...)
 *
 * @author Mill Zuckergates
 * @version 1.0
 */

package com.stephane.frame;

import com.stephane.dao.DAOClient;
import com.stephane.dao.DAOException;
import com.stephane.dao.DAOProspect;
import com.stephane.entity.*;
import com.stephane.exceptions.ReversoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import static com.stephane.logs.LoggerReverso.LOGGER;

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
    private JButton contratsButton;
    private JButton menuPrincipalButton;
    private Choix choix;
    private Crud crud;
    private Societe choixSociete;


    /**
     * Constructeur de la frame
     */
    public HomeFrame(){
        initComponent();
        actionListener();
    }

    /**
     * Initialisation de la frame avec ses paramètres.
     */
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
     * Contient tous les ActionListener
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
                contratsButton.setVisible(false);
                // Réinitialisation de la combobox (évite l'affichage en double)
                comboBox.removeAllItems();
                // Remplissage de la combobox
                try{
                    DAOProspect DaoProspect = new DAOProspect();
                    for(Prospect prospect : DaoProspect.findAll()){
                        comboBox.addItem(prospect);
                    }
                }catch(ReversoException re){
                    JOptionPane.showMessageDialog(null, re.getMessage());
                }catch(DAOException de){
                    if(de.getGravity() != 5){
                        JOptionPane.showMessageDialog(null,de.getMessage());
                    }else{
                        LOGGER.log(Level.SEVERE,"Problème de lecture bdd " + de.getMessage() +
                                "L'appli va fermer.");
                        JOptionPane.showMessageDialog(null,de.getMessage());
                        System.exit(1);
                    }
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
                contratsButton.setVisible(true);
                // Réinitialisation de la combobox (évite l'affichage en double)
                comboBox.removeAllItems();
                // Remplissage de la combobox
                try{
                    DAOClient DaoClient = new DAOClient();
                    for(Client client : DaoClient.findAll()){
                        comboBox.addItem(client);
                    }
                }catch(ReversoException re){
                    JOptionPane.showMessageDialog(null, re.getMessage());
                }catch(DAOException de){
                    if(de.getGravity() != 5){
                        JOptionPane.showMessageDialog(null,de.getMessage());
                    }else{
                        LOGGER.log(Level.SEVERE,"Problème de lecture bdd " + de.getMessage() +
                                "L'appli va fermer.");
                        JOptionPane.showMessageDialog(null,de.getMessage());
                        System.exit(1);
                    }
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
         * Clic sur le bouton Contrats
         */
        contratsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                homePanelListe.setVisible(true);
                validerButton.setText("Voir les contrats");
                crud = Crud.CONTRATS;
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
                System.out.println(crud);
            }
        });

        /**
         * Clic sur le bouton Valider
         */
        validerButton.addActionListener(new ActionListener(){
                @Override public void actionPerformed (ActionEvent e){
                        switch(crud){
                            case MODIFIER:
                            case SUPPRIMER:
                                if(crud == Crud.MODIFIER){
                                    validerButton.getText().equals("Modifier");
                                }
                                if(crud == Crud.SUPPRIMER){
                                    validerButton.getText().equals("Supprimer");
                                    titreLabel.setText("SUPPRIMER");
                                }
                                int index = comboBox.getSelectedIndex();
                                switch(choix){
                                    case PROSPECTS:
                                    case CLIENTS:
                                        choixSociete = (Societe)comboBox.getSelectedItem();
                                        dispose();
                                        break;
                                }
                                CrudFrame crudModifierSupprimer = new CrudFrame(choix,choixSociete,crud);
                                break;
                            case CONTRATS:
                                if(crud == Crud.CONTRATS){
                                    validerButton.getText().equals("Voir les contrats");
                                }
                                switch(choix){
                                    case CLIENTS:
                                        choixSociete = (Societe)comboBox.getSelectedItem();
                                        dispose();
                                        break;
                                }
                                ContratFrame contratFrame = new ContratFrame(choixSociete);
                            break;
                        }
                }
        });

        /**
         * Clic sur le bouton Quitter
         */
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
