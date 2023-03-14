package com.stephane.entity;

import com.stephane.exceptions.ReversoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prospect extends Societe{

    private static int idProspect = 0;
    private LocalDate dateProspection = LocalDate.now();
    private String interet;

    public Prospect(String raisonSociale,String numRue,String rue,
            String codePostal,String ville,String tel,String email,
            String commentaire, LocalDate dateProspection,String interet)
            throws ReversoException{
        super(raisonSociale,numRue,rue,codePostal,ville,tel,email,commentaire);
        this.setDateProspection(dateProspection);
        this.setInteret(interet);
    }

    public LocalDate getDateProspection(){
        return dateProspection;
    }

    public void setDateProspection(LocalDate dateProspection){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateProspection.format(formatter);
        this.dateProspection = dateProspection;

    }

    public String getInteret(){
        return interet;
    }

    public void setInteret(String interet){
        this.interet = interet;
    }

    public static int getIdProspect(){
        return idProspect;
    }

    public static void setIdProspect(int idProspect){
        Prospect.idProspect = idProspect;
    }
}
