package com.stephane.dao;

import com.stephane.entity.Prospect;
import com.stephane.exceptions.ReversoException;
import static com.stephane.logs.LoggerReverso.LOGGER;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class DAOProspect{
    public static ArrayList<Prospect> findAll()
            throws ReversoException, DAOException {
        ArrayList<Prospect> prospects = new ArrayList<>();
        // On récupère la connexion à la db
        Connection con = ConnexionManager.connection;
        PreparedStatement stmt = null;
        String query = "SELECT * FROM prospects";


        try{
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Prospect prospect = new Prospect();
                prospect.setIdSociete(rs.getInt("id"));
                prospect.setRaisonSociale(rs.getString("raison_sociale"));
                prospect.setNumRue(rs.getString("num_rue"));
                prospect.setRue(rs.getString("rue"));
                prospect.setCodePostal(rs.getString("code_postal"));
                prospect.setVille(rs.getString("ville"));
                prospect.setTel(rs.getString("tel"));
                prospect.setEmail(rs.getString("email"));
                prospect.setCommentaire(rs.getString("commentaire"));
                Date dateProspectionSql = rs.getDate("date_prospection");
                LocalDate dateProspection = dateProspectionSql.toLocalDate();
                prospect.setDateProspection(dateProspection);
                prospect.setInteret(rs.getString("interet"));

                prospects.add(prospect);
            }
        }catch(SQLException se){
            LOGGER.log(Level.SEVERE,"Probleme avec la base de données" +
                    se.getMessage());
            throw new DAOException("Problème avec la méthode findAll()");
        }

        return prospects;
    }

    public static void find(int id) throws ReversoException, DAOException{
        Connection con = ConnexionManager.connection;
        PreparedStatement stmt = null;
        String query = "SELECT * FROM clients WHERE id = ?";
        try{
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
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
                Date dateProspectionSql = rs.getDate("date_prospection");
                LocalDate dateProspection = dateProspectionSql.toLocalDate();
                String interet = rs.getString("interet");
            }
        }catch(SQLException se){
            LOGGER.log(Level.SEVERE, "Erreur : Problème avec la méthode find");
            throw new DAOException("Erreur : Problème avec la méthode find");
        }
    }

    public static void save(Integer id, String raisonSociale, String numRue,
            String rue, String codePostal, String ville, String tel,
            String email, String commentaire, LocalDate dateProspection,
            String interet) throws ReversoException, DAOException{
        try {
            Connection con = ConnexionManager.connection;
            PreparedStatement stmt = null;
            String query;

            if (id == null) {
                query = "INSERT INTO prospects (raison_sociale, num_rue, rue, code_postal, ville, tel, email, commentaire, " +
                        "date_prospection, interet) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                query = "UPDATE prospects SET raison_sociale = ?, num_rue = ?, " +
                        "rue = ?, code_postal = ?, ville = ?, tel = ?, email = ?, " +
                        "commentaire = ?, date_prospection = ?, interet = ? " +
                        "WHERE id = ?";
            }
            stmt = con.prepareStatement(query);
            stmt.setString(1, raisonSociale);
            stmt.setString(2, numRue);
            stmt.setString(3, rue);
            stmt.setString(4, codePostal);
            stmt.setString(5, ville);
            stmt.setString(6, tel);
            stmt.setString(7, email);
            stmt.setString(8, commentaire);
            Date dateProspectionSql = Date.valueOf(dateProspection);
            stmt.setDate(9, dateProspectionSql);
            stmt.setString(10, interet);

            if (id != null) {
                stmt.setInt(11, id);
            }

            stmt.executeUpdate();

        }catch(SQLException se) {
            if(se.getErrorCode() == 1062){
                LOGGER.log(Level.INFO,("Erreur : " + se.getErrorCode() + " " + se.getCause()));
                throw new DAOException("La raison sociale '" + raisonSociale + "existe déjà.", 1);
            }else{
                LOGGER.log(Level.SEVERE, ("Problème avec la base " + se.getMessage()));
                throw new DAOException(se.getMessage(), 5);
            }
        }
    }

    public static void delete(int id) throws DAOException {
        Connection con = ConnexionManager.connection;
        PreparedStatement stmt = null;
        String query = "DELETE FROM prospects WHERE id = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch(SQLException e) {
            throw new DAOException("Erreur lors de la suppression du client", 5);
        }
    }

}
