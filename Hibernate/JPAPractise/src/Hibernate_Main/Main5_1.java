package Hibernate_Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Tables.*;

public class Main5_1
{
    private static  SessionFactory sessionFactory = null;

    public static void main(String[] args)
    {

        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Supplier supplier = new Supplier("Qwerty", "Medieval", "Torun");

        Category category = new Category("Clothes");

        Product product = new Product("Shirt",14, supplier, category, session);

        session.save(supplier);
        session.save(category);


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
