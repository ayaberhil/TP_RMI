package clientrmi;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientRMI {

    public static void main(String[] args) {

        try {
            IDao<Salle> dao2 = (IDao<Salle>) Naming.lookup("rmi://localhost:1099/dao2");
            dao2.create(new Salle("B1"));
            dao2.create(new Salle("A8"));
            dao2.create(new Salle("B12"));
            dao2.create(new Salle("A1"));

            for (Salle s : dao2.findAll()) {
                System.out.println(s);
            }

            IDao<Machine> dao = (IDao<Machine>) Naming.lookup("rmi://localhost:1099/dao");
            dao.create(new Machine("ER23", "DELL", 4500.0, dao2.findById(1)));
            dao.create(new Machine("ER12", "HP", 3000.0, dao2.findById(2)));
            dao.create(new Machine("ER09", "MAC", 6500.0, dao2.findById(3)));

            for (Machine m : dao.findAll()) {
                System.out.println(m);
            }

        } catch (NotBoundException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
