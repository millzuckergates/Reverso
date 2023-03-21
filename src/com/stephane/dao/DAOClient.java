package com.stephane.dao;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOClient{
    public static void findAll()
            throws SQLException {
        // On récupère la connexion à la db
        Connection con = ConnexionManager.connection;
        PreparedStatement stmt = null;
        String query = "SELECT * FROM client";
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String raisonSociale = rs.getString("raison_sociale");
                String numRue = rs.getString("num_rue");
                String rue = rs.getString("rue");
                String codePostal = rs.getString("code_postal");
                String ville = rs.getString("ville");
                String tel = rs.getString("tel");
                String email = rs.getString("email");
                String commentaire = rs.getString("commentaire");
                Double chiffreAffaires = rs.getDouble("chiffre_affaires");
                Integer nb_employes = rs.getInt("nb_employes");
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    public static void find(int id) throws SQLException {
        Connection con = ConnexionManager.connection;
        PreparedStatement stmt = null;
        String query = "SELECT * FROM client WHERE id = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            System.out.println(query);

            // Traiter le ResultSet ici (par exemple, afficher les résultats)
            if(rs.next()){
                int resultId = rs.getInt("id");
                String raisonSociale = rs.getString("raison_sociale");
                String numRue = rs.getString("num_rue");
                String rue = rs.getString("rue");
                String codePostal = rs.getString("code_postal");
                String ville = rs.getString("ville");
                String tel = rs.getString("tel");
                String email = rs.getString("email");
                String commentaire = rs.getString("commentaire");
                Double chiffreAffaires = rs.getDouble("chiffre_affaires");
                Integer nb_employes = rs.getInt("nb_employes");

                System.out.println(raisonSociale);
            }

        } catch (SQLException e) {
            //JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

}
