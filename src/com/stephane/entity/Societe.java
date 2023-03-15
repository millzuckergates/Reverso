/**
 *  Cette classe abstraite représente une société avec ses informations de base
 *
 * @author Mill Zuckergates
 * @version 1.0
 */

package com.stephane.entity;

import com.stephane.exceptions.ReversoException;

public abstract class Societe{
    private int idSociete ;
    private String raisonSociale;
    private String numRue;
    private String rue;
    private String codePostal;
    private String ville;
    private String tel;
    private String email;
    private String commentaire;

    /**
     *
     * @param raisonSociale Le nom de l'entreprise
     * @param numRue    Le numéro d'habitation
     * @param rue   Le nom de la rue
     * @param codePostal Le code postal
     * @param ville Le nom de la ville
     * @param tel   Le numéro de téléphone
     * @param email L'adresse email
     * @param commentaire Commentaire sur la société (facultatif)
     * @throws ReversoException
     */
    public Societe(String raisonSociale,String numRue,String rue,String codePostal,String ville,String tel,
            String email, String commentaire) throws ReversoException{
        this.setIdSociete(idSociete);
        this.setRaisonSociale(raisonSociale);
        this.setNumRue(numRue);
        this.setRue(rue);
        this.setCodePostal(codePostal);
        this.setVille(ville);
        this.setTel(tel);
        this.setEmail(email);
        this.setCommentaire(commentaire);
    }

    public int getIdSociete(){
        return idSociete;
    }

    public void setIdSociete(int idSociete){
        this.idSociete = idSociete;
    }

    public String getRaisonSociale(){
        return raisonSociale;
    }

    /**
     * Modifie le nom de la société
     * @param raisonSociale Le nom de la société. Ne doit pas être vide.
     * @throws ReversoException
     */
    public void setRaisonSociale(String raisonSociale) throws ReversoException{
        if(!raisonSociale.isEmpty()){
            this.raisonSociale = raisonSociale;
        }else{
            throw new ReversoException("La raison sociale doit être saisie");
        }
    }

    public String getNumRue(){
        return numRue;
    }

    /**
     * Modifie le numéro d'habitation
     * @param numRue Le numéro d'habitation. Ne doit pas être vide
     * @throws ReversoException
     */
    public void setNumRue(String numRue) throws ReversoException{
        if(!numRue.isEmpty()){
            this.numRue = numRue;
        }else{
            throw new ReversoException("Le numéro de rue doit être saisi");
        }
    }

    public String getRue(){
        return rue;
    }

    /**
     * Modifie le nom de la rue
     * @param rue Le nom de la rue. Ne doit pas être vide
     * @throws ReversoException
     */
    public void setRue(String rue) throws ReversoException{
        if(!rue.isEmpty()){
            this.rue = rue;
        }else{
            throw new ReversoException("Le nom de la rue doit être saisie");
        }
    }

    public String getCodePostal(){
        return codePostal;
    }

    /**
     * Modifie le code postal
     * @param codePostal Le code postal. Ne doit pas être vide. Doit contenir 5 caractères.
     * @throws ReversoException
     */
    public void setCodePostal(String codePostal) throws ReversoException{
        if(!codePostal.isEmpty() && codePostal.length() == 5){
            this.codePostal = codePostal;
        }else{
            throw new ReversoException("Le numéro de rue doit être saisi et doit contenir 5 caractères.");
        }
    }

    public String getVille(){
        return ville;
    }

    /**
     * Modifie la ville
     * @param ville Le nom de la ville. Ne doit pas être vide
     * @throws ReversoException
     */
    public void setVille(String ville) throws ReversoException{
        if(!ville.isEmpty()){
            this.ville = ville;
        }else{
            throw new ReversoException("Le numéro de rue doit être saisi");
        }
    }

    public String getTel(){
        return tel;
    }

    /**
     * Modifie le numéro de téléphone
     * @param tel Le numéro de téléphone. Doit contenir 10 caractères. Ne doit
    pas contenir de lettres.
     * @throws ReversoException
     */
    public void setTel(String tel) throws ReversoException{
        if (tel.matches("\\d{10,}")){
            this.tel = tel;
        }else{
            throw new ReversoException("Le numéro de téléphone doit être renseigné et contenir 10 caractères.");
        }
    }

    public String getEmail(){
        return email;
    }

    /**
     * Modifie l'adresse email
     * @param email L'adresse email. Ne doit être vide. Doit contenir un @.
     * @throws ReversoException
     */
    public void setEmail(String email) throws ReversoException{
        if (!email.isEmpty() && email.matches("[^@]+@[^@]+")){
            this.email = email;
        }else{
            throw new ReversoException("L'adresse email est incorrecte");
        }
    }

    public String getCommentaire(){
        return commentaire;
    }

    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
}
