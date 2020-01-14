package Hibernate_Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Tables.*;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class Main3
{
    private static  SessionFactory sessionFactory = null;

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String companyName = inputScanner.nextLine();
        String street = inputScanner.nextLine();
        String city = inputScanner.nextLine();
        Supplier supplier = new Supplier(companyName,street,city);

        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
            session.save(supplier);

            String requiredProductName = "wand";
            TypedQuery<Product> q = session.createQuery("from Product as product"
                + " where lower(product.productName)=:productName", Product.class);
            q.setParameter("productName", requiredProductName);
            List<Product> products = q.getResultList();
            Product product = products.get(0);

            session.evict(product);
            product.setSupplier(supplier);
            session.update(product);

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
