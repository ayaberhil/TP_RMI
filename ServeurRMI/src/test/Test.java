package test;

import com.sun.istack.internal.logging.Logger;
import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.rmi.RemoteException;
import java.util.logging.Level;
import service.MachineService;
import service.SalleService;
import sun.util.logging.PlatformLogger;
import util.HibernateUtil;

public class Test {

    public static void main(String[] args) {

        try {

            IDao<Salle> dao2 = new SalleService();
            dao2.create(new Salle("B1"));
            dao2.create(new Salle("B8"));
            dao2.create(new Salle("B12"));
            dao2.create(new Salle("A1"));

            for (Salle s : dao2.findAll()) {
                System.out.println(s);
            }
            IDao<Machine> dao = new MachineService();
            dao.create(new Machine("ER23", "DELL", 4500.0, dao2.findById(1)));
            dao.create(new Machine("ER12", "HP", 3000.0, dao2.findById(2)));
            dao.create(new Machine("ER09", "MAC", 6500.0, dao2.findById(3)));

            for (Machine m : dao.findAll()) {
                System.out.println(m);
            }

        } catch (RemoteException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
