package com.stephane.frame;

import com.stephane.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListFrame extends JFrame{
    private JPanel listPanel;
    private JTable listTable;
    private DefaultTableModel listModel;
    private JButton retourAuMenuButton;
    private Choix choix;

    public ListFrame(Choix choix){
        this.choix = choix;
        initComponent();
        actionListener();
        initTable();
    }

    public void initComponent(){
        setContentPane(listPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionListener(){
        retourAuMenuButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                HomeFrame homeFrame = new HomeFrame();
                dispose();
            }
        });
    }

    public void initTable(){
        // En-tête du tableau
        String[] colonnes = new String[]{"ID", "Raison sociale", "Numéro de rue"
        , "Rue", "Code postal", "Ville", "Tel", "Email", "Chiffre d'affaire", "Nombre employés"};
        if(choix == Choix.PROSPECTS){
            colonnes[8] = "Date de prospection";
            colonnes[9] = "Intêret";
        }
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
                        prospect.getDateProspection(),
                        prospect.getInteret()
                    });
                }
                break;
        }
        listTable.setModel(modele);
    }
}
