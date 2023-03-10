import com.stephane.entity.Client;
import com.stephane.entity.Prospect;
import com.stephane.exceptions.ReversoException;
import com.stephane.frame.HomeFrame;

import java.time.LocalDate;

public class Main{
    public static void main(String[] args){
        try{
            Client Corbeau = new Client("SARL Corbeau","8","rue de la grange","54345","Villefroid","0787876453","corbeau@aol.fr",150,41);
            Client Fortunado = new Client("Menuiserie Fortunado","1","grande rue","54500","Loisy","0383898745","fortune@aol.fr",150, 20);
            Prospect Cocoon = new Prospect("Menuiserie Cocoon","4","Avenue de l'Europe","93345","Cul-Aux-Vaches","0364542096","cocoon@aol.fr",LocalDate.now(), "Oui");
            Prospect Tigana = new Prospect("Verolia","65","rue de la verole","54765","Passage du cul terreux","0735640980","verolia@aol.fr",LocalDate.now(), "Non");
        }catch (ReversoException re){
            System.out.println("Erreur :" + re.getMessage());
        }

        HomeFrame homeFrame = new HomeFrame();
    }
}
