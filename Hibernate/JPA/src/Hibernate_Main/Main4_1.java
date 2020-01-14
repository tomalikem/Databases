package Hibernate_Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Tables.*;

public class Main4_1
{
    private static  SessionFactory sessionFactory = null;

    public static void main(String[] args)
    {
        Product product;
        Supplier supplier;
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        supplier = new Supplier("Toys", "Rainbow", "Wroclaw");
        session.save(supplier);
        product = new Product("Ball", 10, supplier);
        supplier.add(product);
        session.save(product);
        product = new Product("Doll", 15, supplier);
        supplier.add(product);
        session.save(product);

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
