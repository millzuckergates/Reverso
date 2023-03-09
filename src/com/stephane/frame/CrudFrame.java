package com.stephane.frame;

import com.stephane.entity.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField textFieldVar1;
    private JTextField textFieldVar2;
    private JButton validerButton;
    private JButton menuButton;
    private JLabel labelID;
    private JLabel labelVar1;
    private JLabel labelVar2;
    private JLabel labelTitre;

    public CrudFrame(Crud choix){
        initComponent();
        test();
        actionListener();
    }

    public void initComponent(){
        // Paramètres de frame
        setContentPane(crudPanel);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void test(){
        // Récupération des données
        String raisonSociale = textFieldRaison.getText();
        String numeroRue = textFieldNumero.getText();
        String nomRue = textFieldNomRue.getText();
        String codePostal = textFieldCodePostal.getText();
        String ville = textFieldVille.getText();
        String tel = textFieldTel.getText();
        String email = textFieldEmail.getText();
        String commentaire = textFieldCommentaire.getText();
        String var1 = textFieldVar1.getText();
        String var2 = textFieldVar2.getText();

        switch(HomeFrame.choix){
            case PROSPECTS:
                labelVar1.setText("Date de prospection");
                labelVar2.setText("Prospect intéressé");
                break;
            case CLIENTS:
                labelVar1.setText("Chiffre d'affaires");
                labelVar2.setText("Nombre d'employés");
                break;
        }
    }

    public void actionListener(){
        validerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                }
            }
        });
    }
}
