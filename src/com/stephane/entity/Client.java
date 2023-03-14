package com.stephane.entity;

import com.stephane.exceptions.ReversoException;

import java.util.ArrayList;

public class Client extends Societe{

    private static int idClient = 0;
    private double chiffreAffaires;
    private int nbEmployes;

    public Client(String raisonSociale,String numRue,String rue,String codePostal,String ville,String tel,
            String email,String commentaire, double chiffreAffaires,int nbEmployes) throws ReversoException{
        super(raisonSociale,numRue,rue,codePostal,ville,tel,email,commentaire);
        this.setChiffreAffaires(chiffreAffaires);
        this.setNbEmployes(nbEmployes);
    }

    public double getChiffreAffaires(){
        return chiffreAffaires;
    }

    public void setChiffreAffaires(double chiffreAffaires) throws ReversoException{
        if(chiffreAffaires > 200){
            this.chiffreAffaires = chiffreAffaires;
        }else{
            throw new ReversoException("Le chiffre d'affaire doit être supérieur à 200");
        }
    }

    public int getNbEmployes(){
        return nbEmployes;
    }

    public void setNbEmployes(int nbEmployes) throws ReversoException{
        if(nbEmployes > 0){
            this.nbEmployes = nbEmployes;
        }else{
            throw new ReversoException("Le nombre d'employés doit être supérieur à 0");
        }
    }

    public static int getIdClient(){
        return idClient;
    }

    public static void setIdClient(int idClient){
        Client.idClient = idClient;
    }
}
