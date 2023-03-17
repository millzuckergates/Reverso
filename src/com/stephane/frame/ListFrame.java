/**
 *  La classe ListFrame affiche une liste de
 *  clients ou de prospects, selon le choix de l'utilisateur. Cette fenêtre
 *  contient une JTable avec une DefaultTableModel pour afficher les données.
 *  Les colonnes de la table varient en fonction du choix de l'utilisateur et
 *  les données sont récupérées à partir des listes de clients ou de prospects
 *  stockées dans les classes Clients et Prospects respectivement. La fenêtre
 *  a également un bouton de retour qui permet à l'utilisateur de retourner à
 *  la page d'accueil.
 *
 * @author Mill Zuckergates
 * @version 1.0
 */

package com.stephane.frame;

import com.stephane.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class ListFrame extends JFrame{
    private JPanel listPanel;
    private JTable listTable;
    private DefaultTableModel listModel;
    private JButton retourAuMenuButton;
    private Choix choix;

    /**
     * Constructeur de la frame
     * @param choix L'enumération de choix (PROSPECTS ou CLIENTS) déclarée dans
     HomeFrame.
     */
    public ListFrame(Choix choix){
        this.choix = choix;
        initComponent();
        actionListener();
        initTable();
    }

    /**
     * Initialisation de la frame avec ses paramètres.
     */
    public void initComponent(){
        setContentPane(listPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Tous les ActionListener
     */
    public void actionListener(){
        retourAuMenuButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                HomeFrame homeFrame = new HomeFrame();
                dispose();
            }
        });
    }

    /**
     * Méthode d'initialisation et d'affichage du tableau.
     */
    public void initTable(){
        // En-tête du tableau
        String[] colonnes = new String[]{"ID", "Raison sociale", "Numéro de rue"
        , "Rue", "Code postal", "Ville", "Tel", "Email", "Chiffre d'affaire", "Nombre employés"};
        if(choix == Choix.PROSPECTS){
            colonnes[8] = "Date de prospection";
            colonnes[9] = "Intêret";
        }
        // Création du modèle du tableau
        DefaultTableModel modele = new DefaultTableModel(new Object[][]{}, colonnes);
        modele.addRow(colonnes);
        switch(choix){
            case CLIENTS:
                for(Client client : Clients.getListClients()){
                    modele.addRow(new Object[]{
                        client.getIdClient(),
                        client.getRaisonSociale(),
                        client.getNumRue(),
                        client.getRue(),
                        client.getCodePostal(),
                        client.getVille(),
                        client.getTel(),
                        client.getEmail(),
                        client.getChiffreAffaires(),
                        client.getNbEmployes()
                    });
                }
                break;
            case PROSPECTS:
                for(Prospect prospect : Prospects.getListProspects()){
                    modele.addRow(new Object[]{
                        prospect.getIdProspect(),
                        prospect.getRaisonSociale(),
                        prospect.getNumRue(),
                        prospect.getRue(),
                        prospect.getCodePostal(),
                        prospect.getVille(),
                        prospect.getTel(),
                        prospect.getEmail(),
                        prospect.getDateProspection().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        prospect.getInteret()
                    });
                }
                break;
        }
        // On passe le modèle au tableau
        listTable.setModel(modele);
    }
}
