import com.stephane.dao.ConnexionManager;
import com.stephane.dao.DAOClient;
import com.stephane.dao.DAOException;
import com.stephane.exceptions.ReversoException;
import com.stephane.frame.HomeFrame;
import com.stephane.logs.FormatterLog;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import static com.stephane.logs.LoggerReverso.LOGGER;

public class Main{
    public static void main(String[] args) throws DAOException, ReversoException, IOException{
            new ConnexionManager();
//            DAOClient.save(null, "Testll", "4", "grande",
//                    "54637", "doui", "3434343434", "q@q.fr", "wer", 123.123, 1233);
            DAOClient.findAll();
            FileHandler fh = new FileHandler("LogReverso.log", true);
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(fh);

            fh.setFormatter(new FormatterLog());
            LOGGER.log(Level.SEVERE, "Un utilisateur s'est connecté");

        HomeFrame homeFrame = new HomeFrame();
    }
}
