package Hibernate_Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Tables.*;

import java.util.Scanner;

public class Main2
{
    private static  SessionFactory sessionFactory = null;

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        String prodName = inputScanner.nextLine();
        int unitsInStock = Integer.parseInt(inputScanner.nextLine());
        Product product = new Product(prodName, unitsInStock);

        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
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
