package Hibernate_Main;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Tables.*;

public class Main12
{
    private static  SessionFactory sessionFactory = null;

    public static void main(String[] args) {

        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(new Customer("Marex", "Krakow", "Kijowska", 50, 0.15));
        session.save(new Customer("Polex", "Poznan", "Lea", 50, 0.15));
        session.save(new Customer("Ergo", "Warsaw", "Grodzka", 50, 0.15));
        session.save(new Customer("Atena", "Poznan", "Narodowa", 50, 0.15));

        tx.commit();
        session.close();
    }


    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory =
                    configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
