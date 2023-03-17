/**
 *  La classe Client hérite de la classe Societe et représente un client d'une entreprise.
 *
 * @author Mill Zuckergates
 * @version 1.0
 */

package com.stephane.entity;

import com.stephane.exceptions.ReversoException;

import java.util.ArrayList;

public class Client extends Societe{

    private static int idClient = 0;
    private double chiffreAffaires;
    private int nbEmployes;

    /**
     * @param raisonSociale Le nom de l'entreprise
     * @param numRue    Le numéro d'habitation
     * @param rue   Le nom de la rue
     * @param codePostal Le code postal
     * @param ville Le nom de la ville
     * @param tel   Le numéro de téléphone
     * @param email L'adresse email
     * @param commentaire Commentaire sur la société (facultatif)
     * @param chiffreAffaires Le chiffre d'affaires de la société
     * @param nbEmployes Le nombre d'employés dans la société
     * @throws ReversoException
     */
    public Client(String raisonSociale,String numRue,String rue,String codePostal,String ville,String tel,
            String email,String commentaire, double chiffreAffaires,int nbEmployes) throws ReversoException{
        super(raisonSociale,numRue,rue,codePostal,ville,tel,email,commentaire);
        this.setChiffreAffaires(chiffreAffaires);
        this.setNbEmployes(nbEmployes);
    }

    public double getChiffreAffaires(){
        return chiffreAffaires;
    }

    /**
     * Modifie le chiffre d'affaires
     * @param chiffreAffaires Le chiffre d'affaires. Ne doit pas être vide. Doit
     être strictement supérieur à 200
     * @throws ReversoException
     */
    public void setChiffreAffaires(double chiffreAffaires)
            throws ReversoException{
        if(chiffreAffaires > 200){
            this.chiffreAffaires = chiffreAffaires;
        }else{
            throw new ReversoException("Le chiffre d'affaire doit être supérieur" +
            " à 200");
        }
    }

    public int getNbEmployes(){
        return nbEmployes;
    }

    /**
     * Modifie le nombre d'employés
     * @param nbEmployes Le nombre d'employés. Ne doit pas être vide. Doit être
     supérieur à 0.
     * @throws ReversoException
     */
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

    @Override
    public String toString(){
        return super.toString() + "Client{" + "chiffreAffaires=" +
                chiffreAffaires + ", nbEmployes=" + nbEmployes + '}';
    }
}
