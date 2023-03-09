package com.stephane.entity;

import com.stephane.exceptions.ReversoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prospect extends Societe{
    private static int idProspect = 0;
    private LocalDate dateProspection;
    private String interet;

    public Prospect(String raisonSociale,String numRue,String rue,String codePostal,String ville,
            String tel,String email,LocalDate dateProspection,String interet) throws ReversoException{
        super(raisonSociale,numRue,rue,codePostal,ville,tel,email);
        this.setDateProspection(dateProspection);
        this.setInteret(interet);
        idProspect++;
        Prospects.getListProspects().add(this);
    }

    public LocalDate getDateProspection(){
        return dateProspection;
    }

    public void setDateProspection(LocalDate dateProspection){
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        this.dateProspection = LocalDate.parse(, format);
    }

    public String getInteret(){
        return interet;
    }

    public void setInteret(String interet){
        this.interet = interet;
    }
}
