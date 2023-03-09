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

    public Societe(String raisonSociale,String numRue,String rue,String codePostal,String ville,String tel,
            String email) throws ReversoException{
        this.setIdSociete(idSociete);
        this.setRaisonSociale(raisonSociale);
        this.setNumRue(numRue);
        this.setRue(rue);
        this.setCodePostal(codePostal);
        this.setVille(ville);
        this.setTel(tel);
        this.setEmail(email);
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
     *
     * @param raisonSociale
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

    public void setNumRue(String numRue){
        this.numRue = numRue;
    }

    public String getRue(){
        return rue;
    }

    public void setRue(String rue){
        this.rue = rue;
    }

    public String getCodePostal(){
        return codePostal;
    }

    public void setCodePostal(String codePostal){
        this.codePostal = codePostal;
    }

    public String getVille(){
        return ville;
    }

    public void setVille(String ville){
        this.ville = ville;
    }

    public String getTel(){
        return tel;
    }

    public void setTel(String tel) throws ReversoException{
        if(!tel.isEmpty() && tel.length() == 10){
            this.tel = tel;
        }else{
            throw new ReversoException("Le numéro de téléphone doit être renseigné et contenir 10 caractères.");
        }
    }

    public String getEmail(){
        return email;
    }

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
