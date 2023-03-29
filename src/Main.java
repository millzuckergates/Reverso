import com.stephane.dao.ConnexionManager;
import com.stephane.dao.DAOClient;
import com.stephane.dao.DAOException;
import com.stephane.exceptions.ReversoException;
import com.stephane.frame.HomeFrame;
import com.stephane.logs.FormatterLog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import static com.stephane.logs.LoggerReverso.LOGGER;

public class Main{
    public static void main(String[] args) throws DAOException, ReversoException, IOException{
        Connection con = ConnexionManager.getInstance().getConnection();

            FileHandler fh = new FileHandler("LogReverso.log", true);
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(fh);

            fh.setFormatter(new FormatterLog());
            LOGGER.log(Level.SEVERE, "Un utilisateur s'est connecté");

        HomeFrame homeFrame = new HomeFrame();
    }
}
