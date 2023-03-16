import com.stephane.entity.Client;
import com.stephane.entity.Clients;
import com.stephane.entity.Prospect;
import com.stephane.entity.Prospects;
import com.stephane.exceptions.ReversoException;
import com.stephane.frame.HomeFrame;

import java.time.LocalDate;

public class Main{
    public static void main(String[] args){
        try{
            Client corbeau = new Client("SARL Corbeau","8","rue de la grange","54345","Villefroid","0787876453","corbeau@aol.fr",null, 400, 54);
            Client fortunado = new Client("Menuiserie Fortunado","1","grande rue","54500","Loisy","0383898745","fortune@aol.fr",null, 600, 32);
            Clients.getListClients().add(corbeau);
            Clients.getListClients().add(fortunado);
//        Prospect coco = new Prospect("SARL Corbeau2","8","rue de la grange","54345","Villefroid","0787876453","corbeau@aol.fr",null, LocalDate.now(), "oui");
//        Prospect fortune = new Prospect("Menuiserie Fortunado2","1","grande rue","54500","Loisy","0383898745","fortune@aol.fr",null, LocalDate.now(), "oui");
//        Prospects.getListProspects().add(coco);
//        Prospects.getListProspects().add(fortune);



        }catch (ReversoException re){
            System.out.println("Erreur :" + re.getMessage());
        }
        HomeFrame homeFrame = new HomeFrame();
    }
}
