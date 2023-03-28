package com.stephane.dao;

import com.stephane.entity.Contrat;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import static com.stephane.logs.LoggerReverso.LOGGER;

public class DAOContrat{
    public static ArrayList<Contrat> findByIdClient(int id_client) throws DAOException{
        ArrayList<Contrat> contrats = new ArrayList<>();
        try{
            Connection con = ConnexionManager.getInstance().getConnection();
            PreparedStatement stmt = null;
            String query = "SELECT * FROM contrats WHERE id_client = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id_client);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Contrat contrat = new Contrat();
                contrat.setId(rs.getInt("id"));
                contrat.setIdClient(rs.getInt("id_client"));
                contrat.setLibelleContrat(rs.getString("libelle"));
                contrat.setMontantContrat(rs.getDouble("montant"));

                contrats.add(contrat);
            }
        }catch(SQLException se){
            LOGGER.log(Level.SEVERE,"Probleme avec la base de données" +
                    se.getMessage());
            throw new DAOException("Problème avec la méthode findByIdClient()" + se.getMessage() + se.getCause());
        }

        return contrats;
    }
}
