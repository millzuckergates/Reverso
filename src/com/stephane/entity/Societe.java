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

    public void setCodePostal(String codePostal) throws ReversoException{
        if(!codePostal.isEmpty()){
            this.codePostal = codePostal;
        }else{
            throw new ReversoException("Le numéro de rue doit être saisi");
        }
    }

    public String getVille(){
        return ville;
    }

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
