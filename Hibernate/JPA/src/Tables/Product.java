package Tables;
import org.hibernate.Session;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String productName;
    private int unitsInStock;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier isSuppliedBy;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    Set<Invoice> includes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Ordered> orders;


    public Product(String productName, int unitsInStock, Supplier supplier, Category category, Session session)
    {
        this.includes = new LinkedHashSet<>();
        this.productName = productName;
        this.unitsInStock = unitsInStock;
        this.isSuppliedBy = supplier;
        this.category = category;
        category.add(this);
        supplier.add(this);
        session.save(this);
    }

    public Product(String productName, int unitsInStock, Supplier supplier, Category category, EntityManager manager)
    {
        this.includes = new LinkedHashSet<>();
        this.productName = productName;
        this.unitsInStock = unitsInStock;
        this.isSuppliedBy = supplier;
        this.category = category;
        category.add(this);
        supplier.add(this);
        manager.persist(this);
    }

    public Product(String productName, int unitsInStock, Supplier supplier)
    {
        this.includes = new LinkedHashSet<>();
        this.productName = productName;
        this.unitsInStock = unitsInStock;
        this.isSuppliedBy = supplier;
    }

    public Product(String productName, int unitsInStock)
    {
        this.includes = new LinkedHashSet<>();
        this.productName = productName;
        this.unitsInStock = unitsInStock;
    }

    public Product() {
    }

    public void setSupplier(Supplier supplier)
    {
        this.isSuppliedBy = supplier;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public void add(Invoice invoice)
    {
        includes.add(invoice);
    }

    public String getName()
    {
        return productName;
    }

    public boolean order(Ordered order)
    {
        if(unitsInStock > 0)
        {
            unitsInStock = unitsInStock - 1;
            orders.add(order);
            return true;
        }
        return false;
    }

    public String toString()
    {
        return productName + " " + unitsInStock;
    }
}

