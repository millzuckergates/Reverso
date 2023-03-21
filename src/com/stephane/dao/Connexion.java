package com.stephane.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.stephane.logs.LoggerReverso.LOGGER;

public class Connexion{
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

    private Connexion(){
        try{
            final Properties dataProperties = new Properties();

            File fichier = new File("database.properties");
            FileInputStream input = new FileInputStream(fichier);
            dataProperties.load(input);

            connection = DriverManager.getConnection(
                    dataProperties.getProperty("url"),
                    dataProperties.getProperty("login"),
                    dataProperties.getProperty("password")
            );

        }catch(IOException ie){

        }catch(SQLException se){

        }
    }

}
