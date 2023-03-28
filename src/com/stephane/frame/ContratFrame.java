package com.stephane.frame;

import com.stephane.dao.DAOContrat;
import com.stephane.dao.DAOException;
import com.stephane.entity.*;
import com.stephane.exceptions.ReversoException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

import static com.stephane.logs.LoggerReverso.LOGGER;

public class ContratFrame extends JFrame{
    private JTable contratTable;
    private JPanel contratPanel;
    private JButton MenuButton;
    private Societe choixSociete;

    /**
     * Constructeur de la frame
     * @param choixSociete Variable du choix de la société choisie
     */
    public ContratFrame(Societe choixSociete){
        this.choixSociete = choixSociete;
        initComponent();
        actionListener();
        initTable();
    }

    /**
     * Initialisation de la frame avec ses paramètres.
     */
    public void initComponent(){
        setContentPane(contratPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionListener(){
        MenuButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                HomeFrame homeFrame = new HomeFrame();
                dispose();
            }
        });
    }

    public void initTable(){
        try{
            // En-tête du tableau
            String[] colonnes = new String[]{"ID","ID_client", "Libellé", "Montant"};
            // Création du modèle du tableau
            DefaultTableModel modele = new DefaultTableModel(new Object[][]{},colonnes);
            modele.addRow(colonnes);
            ArrayList<Contrat> contrats = DAOContrat.findByIdClient(choixSociete.getIdSociete());

            for(Contrat contrat : contrats){
                modele.addRow(new Object[]{
                        contrat.getId(),
                        contrat.getIdClient(),
                        contrat.getLibelleContrat(),
                        contrat.getMontantContrat()
                });
            }
            // On passe le modèle au tableau
            contratTable.setModel(modele);
        }catch(DAOException de){
            if(de.getGravity() != 5){
                JOptionPane.showMessageDialog(null,de.getMessage());
            }else{
                LOGGER.log(Level.SEVERE,"Problème de lecture bdd " + de.getMessage() +
                        "L'appli va fermer.");
                JOptionPane.showMessageDialog(null,de.getMessage());
                System.exit(1);
            }
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,"Erreur fatale." + e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
