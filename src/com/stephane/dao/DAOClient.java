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

    public static void create(String raisonSociale, String numRue, String rue
    , String codePostal, String ville, String tel, String email, String commentaire,
    Double chiffreAffaires, int nb_employes) throws SQLException {
        Connection con = ConnexionManager.connection;
        PreparedStatement stmt = null;
        String query = "INSERT INTO client (raison_sociale, num_rue, rue, code_postal, ville, tel, email, commentaire, " +
                "chiffre_affaires, nb_employes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(query);
                stmt.setString(1, raisonSociale);
                stmt.setString(2, numRue);
                stmt.setString(3, rue);
                stmt.setString(4, codePostal);
                stmt.setString(5, ville);
                stmt.setString(6, tel);
                stmt.setString(7, email);
                stmt.setString(8, commentaire);
                stmt.setDouble(9, chiffreAffaires);
                stmt.setInt(10, nb_employes);
                stmt.executeUpdate();

        } catch (SQLException e) {
            //JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

}
