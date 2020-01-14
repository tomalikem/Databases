package Tables;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Ordered
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(mappedBy = "orders")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    public Ordered(Customer customer)
    {
        this.customer = customer;
        this.products  = new LinkedHashSet<>();
    }

    public void add(Product product)
    {
        if(product.order(this))
        {
            products.add(product);
        }
        else
        {
            System.out.println("Couldnt order " + product.getName() + ", no units in stock");
        }
    }

    public Ordered(){}
}

