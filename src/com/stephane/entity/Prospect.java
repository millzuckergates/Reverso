/**
 *  La classe Prospect hérite de la classe Societe et représente un prospect d'une entreprise.
 *
 * @author Mill Zuckergates
 * @version 1.0
 */

package com.stephane.entity;

import com.stephane.exceptions.ReversoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Prospect extends Societe{
    private static int idProspect = 0;
    private LocalDate dateProspection = LocalDate.now();
    private String interet;

    /**
     * @param raisonSociale Le nom de l'entreprise
     * @param numRue Le numéro d'habitation
     * @param rue Le nom de la rue
     * @param codePostal Le code postal
     * @param ville Le nom de la ville
     * @param tel Le numéro de téléphone
     * @param email L'adresse email
     * @param commentaire Commentaire sur la société (facultatif)
     * @param dateProspection La date de prospection
     * @param interet Interessement du client (Oui ou Non)
     * @throws ReversoException
     */
    public Prospect(int id,String raisonSociale,String numRue,String rue,
            String codePostal,String ville,String tel,String email,
            String commentaire, LocalDate dateProspection,String interet)
            throws ReversoException{
        super(raisonSociale,numRue,rue,codePostal,ville,tel,email,commentaire);
        idProspect++;
        this.setDateProspection(dateProspection);
        this.setInteret(interet);
    }

    public LocalDate getDateProspection(){
        return dateProspection;
    }

    public void setDateProspection(LocalDate dateProspection){
        this.dateProspection = dateProspection;

    }

    public String getInteret(){
        return interet;
    }

    public void setInteret(String interet) throws ReversoException{
        if(interet.equalsIgnoreCase("Oui") || interet.equalsIgnoreCase("Non")){
            this.interet = interet;
        }else{
            throw new ReversoException("Le paramètre 'interet' doit être 'Oui' ou 'Non'");
        }
    }

    public static int getIdProspect(){
        return idProspect;
    }

    public static void setIdProspect(int idProspect){
        Prospect.idProspect = idProspect;
    }

    @Override
    public String toString(){
        return super.toString() + "Prospect{" + "dateProspection=" +
                dateProspection + ", interet='" + interet + '\'' + '}';
    }

}
