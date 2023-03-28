package com.stephane.dao;

import com.stephane.entity.Client;
import com.stephane.entity.Contrat;
import com.stephane.exceptions.ReversoException;
import com.stephane.logs.LoggerReverso;
import static com.stephane.logs.LoggerReverso.LOGGER;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

public class DAOClient{
    public static ArrayList<Client> findAll()
            throws ReversoException, DAOException {
        ArrayList<Client> clients = new ArrayList<>();
        // On récupère la connexion à la db
        Connection con = ConnexionManager.connection;
        PreparedStatement stmt = null;
        String query = "SELECT * FROM clients";

        try{
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Client client = new Client();
                client.setIdSociete(rs.getInt("id"));
                client.setRaisonSociale(rs.getString("raison_sociale"));
                client.setNumRue(rs.getString("num_rue"));
                client.setRue(rs.getString("rue"));
                client.setCodePostal(rs.getString("code_postal"));
                client.setVille(rs.getString("ville"));
                client.setTel(rs.getString("tel"));
                client.setEmail(rs.getString("email"));
                client.setCommentaire(rs.getString("commentaire"));
                client.setChiffreAffaires(rs.getDouble("chiffre_affaires"));
                client.setNbEmployes(rs.getInt("nb_employes"));

                clients.add(client);
            }
        }catch(SQLException se){
            LOGGER.log(Level.SEVERE,"Probleme avec la base de données" +
                    se.getMessage());
            throw new DAOException("Problème avec la méthode findAll()");
        }

        return clients;
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
                Double chiffreAffaires = rs.getDouble("chiffre_affaires");
                Integer nb_employes = rs.getInt("nb_employes");

                ArrayList<Contrat> contrats = new ArrayList<>();
                PreparedStatement contratsStmt = con.prepareStatement("SELECT * FROM contrats WHERE id_client = ?");
                contratsStmt.setInt(1, resultId);
                ResultSet contratsRs = contratsStmt.executeQuery();
                while (contratsRs.next()) {
                    Contrat contrat = new Contrat();
                    contrat.setId(contratsRs.getInt("id"));
                    contrat.setIdClient(contratsRs.getInt("id_client"));
                    contrat.setLibelleContrat(contratsRs.getString("libelle_contrat"));
                    contrat.setMontantContrat(contratsRs.getDouble("montant_contrat"));
                    contrats.add(contrat);
                }

                // Création de l'objet Client avec la liste des contrats récupérée
                    Client client = new Client();
                    client.setIdSociete(resultId);
                    client.setRaisonSociale(raisonSociale);
                    client.setNumRue(numRue);
                    client.setRue(rue);
                    client.setCodePostal(codePostal);
                    client.setVille(ville);
                    client.setTel(tel);
                    client.setEmail(email);
                    client.setCommentaire(commentaire);
                    client.setChiffreAffaires(chiffreAffaires);
                    client.setNbEmployes(nb_employes);
                    client.setListContrats(contrats);
            }
        }catch(SQLException se){
            LOGGER.log(Level.SEVERE, "Erreur : Problème avec la méthode find");
            throw new DAOException("Erreur : Problème avec la méthode find");
        }
    }

    public static void save(Integer id, String raisonSociale, String numRue,
            String rue, String codePostal, String ville, String tel,
            String email, String commentaire, Double chiffreAffaires,
            int nb_employes) throws ReversoException, DAOException{
        try {
            Connection con = ConnexionManager.connection;
            PreparedStatement stmt = null;
            String query;

            if (id == null) {
                query = "INSERT INTO clients (raison_sociale, num_rue, rue, code_postal, ville, tel, email, commentaire, " +
                        "chiffre_affaires, nb_employes) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                query = "UPDATE clients SET raison_sociale = ?, num_rue = ?, " +
                        "rue = ?, code_postal = ?, ville = ?, tel = ?, email = ?, " +
                        "commentaire = ?, chiffre_affaires = ?, nb_employes = ? " +
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
            stmt.setDouble(9, chiffreAffaires);
            stmt.setInt(10, nb_employes);

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
        String query = "DELETE FROM clients WHERE id = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch(SQLException e) {
            throw new DAOException("Erreur lors de la suppression du client", 5);
        }
    }

}
