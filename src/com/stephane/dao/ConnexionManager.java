package com.stephane.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    public ConnexionManager(){
        try{
            // Instanciation de la classe Properties (lire les infos d'un fichier)
            final Properties dataProperties = new Properties();


            File fichier = new File("database.properties");
            FileInputStream input = new FileInputStream(fichier);
            dataProperties.load(input);

            connection = DriverManager.getConnection(
                    dataProperties.getProperty("url"),
                    dataProperties.getProperty("login"),
                    dataProperties.getProperty("password")
            );
            System.out.println(connection);

        }catch(IOException ie){
            System.out.println(ie.getMessage());
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
