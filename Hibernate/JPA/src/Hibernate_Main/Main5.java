package Hibernate_Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Tables.*;

public class Main5
{
    private static  SessionFactory sessionFactory = null;

    public static void main(String[] args)
    {

        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Supplier supplier1 = new Supplier("Exiled", "Rainbow", "Wroclaw");
        Supplier supplier2 = new Supplier("Marble", "Bamboo", "Krakow");
        Supplier supplier3 = new Supplier("Trim", "Cloudy", "Warsaw");
        Supplier supplier4 = new Supplier("Waters", "Rainy", "Poznan");

        Category category1 = new Category("Toys");
        Category category2 = new Category("Garden");
        Category category3 = new Category("Home");
        Category category4 = new Category("Food");

        Product product;
        product = new Product("Doll",10, supplier1, category1, session);
        product = new Product("Ball",5, supplier2, category1, session);
        product = new Product("Stick",15, supplier3, category1, session);
        product = new Product("Wand",8, supplier4, category2, session);
        product = new Product("Cat",9, supplier1, category2, session);
        product = new Product("Dog",6, supplier2, category2, session);
        product = new Product("Lantern",73, supplier3, category3, session);
        product = new Product("Hook",12, supplier4, category3, session);
        product = new Product("Script",16, supplier1, category3, session);
        product = new Product("Flower",51, supplier2, category4, session);
        product = new Product("Phone",2, supplier3, category4, session);

        session.save(supplier1);
        session.save(supplier2);
        session.save(supplier3);
        session.save(supplier4);

        session.save(category1);
        session.save(category2);
        session.save(category3);
        session.save(category4);

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
