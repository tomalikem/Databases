package JPA_Main;

import Tables.Category;
import Tables.Invoice;
import Tables.Product;
import Tables.Supplier;
//import MetaInf.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main9 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("myDatabaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();

        Supplier supplier1 = new Supplier("Burned", "Wroclaw", "First", 1);
        Supplier supplier2 = new Supplier("Fabricated", "Krakow", "Second", 2);
        Supplier supplier3 = new Supplier("Doomed", "Warsaw", "Third", 3);
        Supplier supplier4 = new Supplier("Angry", "Poznan", "Forth", 4);

        Category category1 = new Category("Children");
        Category category2 = new Category("Gardening");
        Category category3 = new Category("House");
        Category category4 = new Category("Dishes");

        Invoice invoice1 = new Invoice(42, 345);
        Invoice invoice2 = new Invoice(52, 543);
        Invoice invoice3 = new Invoice(89, 987);
        Invoice invoice4 = new Invoice(78, 123);

        Product product;
        product = new Product("Doll",11, supplier1, category1, em);
        connect(product, invoice2, invoice3);
        em.persist(product);
        product = new Product("Ball",6, supplier2, category1, em);
        connect(product, invoice1, invoice3);
        em.persist(product);
        product = new Product("Stick",12, supplier3, category1, em);
        connect(product, invoice3, invoice2);
        em.persist(product);
        product = new Product("Wand",9, supplier4, category2, em);
        connect(product, invoice4, invoice2);
        em.persist(product);
        product = new Product("Cat",1, supplier1, category2, em);
        connect(product, invoice3, invoice4);
        em.persist(product);
        product = new Product("Lantern",72, supplier3, category3, em);
        connect(product, invoice1, invoice2);
        em.persist(product);
        product = new Product("Script",19, supplier1, category3, em);
        connect(product, invoice4, invoice2);
        em.persist(product);
        product = new Product("Flower",51, supplier2, category4, em);
        connect(product, invoice1, invoice3);
        em.persist(product);
        product = new Product("Phone",2, supplier3, category4, em);
        connect(product, invoice3, invoice2);
        em.persist(product);

        em.persist(supplier1);
        em.persist(supplier2);
        em.persist(supplier3);
        em.persist(supplier4);

        em.persist(category1);
        em.persist(category2);
        em.persist(category3);
        em.persist(category4);

        em.persist(invoice1);
        em.persist(invoice2);
        em.persist(invoice3);
        em.persist(invoice4);

        etx.commit();
        em.close();
    }

    private static void connect(Product product, Invoice invoice1, Invoice invoice2)
    {
        product.add(invoice1);
        product.add(invoice2);
        invoice1.add(product);
        invoice2.add(product);
    }
}
