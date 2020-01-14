import Tables.*;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class CustomerSession
{
    private static Session session;
    Scanner inputScanner;
    Ordered order;
    Customer customer;

    public void start()
    {
        customer = initialize();
        order = new Ordered(customer);
        categoryMenu();
    }

    public void categoryMenu()
    {
        showCategories();
        System.out.println("Type name of the category to show products of that category or F to finalize transaction.");
        String categoryName = inputScanner.nextLine();
        if(categoryName.equals("F"))
        {
            session.save(order);
            customer.addOrder(order);
            session.update(customer);
            return;
        }

        Category category = selectCategory(categoryName);
        category.showProducts();
        System.out.println("Write product name to add it to the cart, R to return to category menu");
        String productName = inputScanner.nextLine();
        if(productName.equals("R")) categoryMenu();
        else
        {
            Product product = category.getProduct(productName);
            if(product != null)
            {
                session.evict(product);
                product.order(order);
                session.update(product);
                order.add(product);
            }

        }
        categoryMenu();
    }


    public CustomerSession(Session session)
    {
        this.session = session;
        this. inputScanner  = new Scanner(System.in);
    }

    private Customer initialize()
    {
        System.out.println("If you want to register type R");
        System.out.println("If you want to login type L");
        String x = inputScanner.nextLine();

        if(x.equals("L"))return logIn();
        else
        {
            if (x.equals("R"))return register();
            else return initialize();
        }
    }

    private void showCategories()
    {
        TypedQuery<Category> q = session.createQuery("from Category as category");
        List<Category> categories = q.getResultList();
        for (Category category: categories)
        {
            System.out.println(category.getName());
        }
    }

    private Category selectCategory(String name)
    {

        TypedQuery<Category> q = session.createQuery("from Category as category"
                + " where (category.name)=:categoryName", Category.class);
        q.setParameter("categoryName", name);

        List<Category> categories = q.getResultList();

        if(categories.isEmpty())
        {
            System.out.println("Wrong category name, write it again");
            return  selectCategory(inputScanner.nextLine());
        }

        return categories.get(0);
    }

    private Customer logIn()
    {
        System.out.println("Write name of your company");
        String customerName = inputScanner.nextLine();

        TypedQuery<Customer> q = session.createQuery("from Customer as customer"
                + " where lower(customer.companyName)=:currentCustomer", Customer.class);
        q.setParameter("currentCustomer", customerName);
        List<Customer> customers = q.getResultList();

        if(customers.isEmpty())
        {
            System.out.println("Looks like you havent created account yet, register now.");
            return register();
        }
        session.evict(customers.get(0));
        return customers.get(0);
    }

    private Customer register()
    {
        System.out.println("Write data regarding your company in same form as below");
        System.out.println("CompanyName City Street Number");
        String customerName = inputScanner.next();
        String city = inputScanner.next();
        String street = inputScanner.next();
        String number = inputScanner.next();

        Customer customer = new Customer(customerName, city, street, Integer.parseInt(number), 0);
        session.save(customer);
        return customer;
    }
}
