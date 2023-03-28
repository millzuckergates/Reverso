package com.stephane.entity;

public class Contrat{
    private Integer id;
    private Integer idClient;
    private String libelleContrat;
    private double montantContrat;

    public Contrat() {
    }

    public Contrat(Integer id, Integer idClient, String libelleContrat, double montantContrat) {
        this.setId(id);
        this.setIdClient(idClient);
        this.setLibelleContrat(libelleContrat);
        this.setMontantContrat(montantContrat);
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getIdClient(){
        return idClient;
    }

    public void setIdClient(Integer idClient){
        this.idClient = idClient;
    }

    public String getLibelleContrat(){
        return libelleContrat;
    }

    public void setLibelleContrat(String libelleContrat){
        this.libelleContrat = libelleContrat;
    }

    public double getMontantContrat(){
        return montantContrat;
    }

    public void setMontantContrat(double montantContrat){
        this.montantContrat = montantContrat;
    }
}
