import com.mysql.jdbc.Connection;
import com.stephane.dao.ConnexionManager;
import com.stephane.dao.DAOClient;
import com.stephane.entity.Client;
import com.stephane.entity.Clients;
import com.stephane.entity.Prospect;
import com.stephane.entity.Prospects;
import com.stephane.exceptions.ReversoException;
import com.stephane.frame.HomeFrame;
import com.stephane.logs.FormatterLog;
import com.stephane.logs.LoggerReverso;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import static com.stephane.logs.LoggerReverso.LOGGER;

public class Main{
    public static void main(String[] args) throws SQLException {
        try{
            new ConnexionManager();
            DAOClient.create("Test", "9", "grande", "59450", "Douai",
                    "1212121212", "a@a.fr", "test", 200.000, 123456);
            FileHandler fh = new FileHandler("LogReverso.log", true);
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(fh);

            fh.setFormatter(new FormatterLog());
            LOGGER.log(Level.INFO, "Un utilisateur s'est connecté");
        }catch(IOException e){
            e.printStackTrace();
        }
        HomeFrame homeFrame = new HomeFrame();
    }
}
