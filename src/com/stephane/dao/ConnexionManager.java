package com.stephane.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import static com.stephane.logs.LoggerReverso.LOGGER;

public class ConnexionManager {
        public static Connection connection = null;
        static{
         Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run()
            {
                if (connection != null) {
                    try {
                        LOGGER.info("Database fermée");
                        connection.close();

                    } catch (SQLException ex) {
                        //LOGGER.fatal(ex.getMessage());
                    }
                }
            }
         });
        }

    public ConnexionManager() throws DAOException{
        try{
            // Instanciation de la classe Properties (lire les infos du fichier)
            final Properties dataProperties = new Properties();


            File fichier = new File("database.properties");
            FileInputStream input = new FileInputStream(fichier);
            dataProperties.load(input);

            connection = DriverManager.getConnection(
                    dataProperties.getProperty("url"),
                    dataProperties.getProperty("login"),
                    dataProperties.getProperty("password")
            );

        //Exception pour la lecture/ecriture d'un fichier
        }catch(IOException ie){
            LOGGER.log(Level.SEVERE,"Probleme de lecture du fichier database.properties"
                    + ie.getMessage());
            throw new DAOException("Erreur de lecture du fichier database.properties",5);
        }catch(SQLException se){
            LOGGER.log(Level.SEVERE,"Probleme de connexion à la base de données" +
                    se.getMessage() + "=>" + se.getCause());
            throw new DAOException("Erreur de connexion à la base de données" + se.getSQLState(),5);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,"Une erreur est survenue. L'application va s'arrêter"
                    + e.getMessage() + "=>" + e.getCause());
            System.exit(1);
            throw new DAOException("Une erreur est survenue. L'application va s'arrêter",5);
        }
    }

}
