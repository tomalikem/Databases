package Hibernate_Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Tables.*;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

public class Main6
{
    private static  SessionFactory sessionFactory = null;

    public static void main(String[] args) {

        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        TypedQuery<Product> q = session.createQuery("from Product as product");

        List<Product> products = q.getResultList();
        List<Invoice> invoices = new LinkedList<>();
        invoices.add(new Invoice(44, 345));
        invoices.add(new Invoice(56, 543));
        invoices.add(new Invoice(78, 787));
        invoices.add(new Invoice(84, 8678));
        invoices.add(new Invoice(12, 5634));

        for(Product product: products)
        {
            session.evict(product);
        }

        for(int i = 0; i < 30; i++)
        {
            Product product = products.get(i%products.size());
            Invoice invoice = invoices.get(i%invoices.size());
            session.evict(product);
            product.add(invoice);
            invoice.add(product);
        }

        for(Product product: products)
        {
            session.update(product);
        }

        for(Invoice invoice: invoices)
        {
            session.save(invoice);
        }

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
