package Hibernate_Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Tables.*;

public class Main4
{
    private static  SessionFactory sessionFactory = null;

    public static void main(String[] args)
    {
        Product product;
        Supplier supplier;
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        supplier = new Supplier("Marble", "Baker", "London");
        session.save(supplier);
        product = new Product("Book", 10, supplier);
        supplier.add(product);
        session.save(product);
        product = new Product("Staff", 15, supplier);
        supplier.add(product);
        session.save(product);

        supplier = new Supplier("Carbon", "Grocer", "Glasgow");
        session.save(supplier);
        product = new Product("Car", 10, supplier);
        supplier.add(product);
        session.save(product);
        product = new Product("Bike", 15, supplier);
        supplier.add(product);
        session.save(product);

        supplier = new Supplier("Gesture", "Bachelor", "Edinburgh");
        session.save(supplier);
        product = new Product("Stick", 10, supplier);
        session.save(product);
        supplier.add(product);
        product = new Product("Brick", 15, supplier);
        supplier.add(product);
        session.save(product);

        supplier = new Supplier("Cargo", "Farmer", "Warsaw");
        session.save(supplier);
        product = new Product("Robot", 10, supplier);
        supplier.add(product);
        session.save(product);
        product = new Product("Snake", 15, supplier);
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
