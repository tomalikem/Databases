package Tables;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany
    @JoinColumn(name = "CategoryId")
    private Set<Product> products;

    public Category(String name)
    {
        this.name = name;
        this.products = new LinkedHashSet<Product>();
    }

    public Category()
    {

    }

    public void add(Product product)
    {
        products.add(product);
    }

    public String getName()
    {
        return name;
    }

    public void showProducts()
    {
        for(Product product: products)
        {
            System.out.println(product.toString());
        }
    }

    public Product getProduct(String name)
    {
        for(Product product: products)
        {
            if(product.getName().equals(name)) return product;
        }
        return null;
    }
}
