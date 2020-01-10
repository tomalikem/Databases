package Tables;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int invoiceNumber;
    private int quantity;


    @ManyToMany(mappedBy = "includes", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Product> canBeSoldIn;

    public Invoice(int invoiceNumber, int quantity)
    {
        this.invoiceNumber = invoiceNumber;
        this.quantity = quantity;
        this.canBeSoldIn = new LinkedHashSet<>();
    }

    public Invoice(){};

    public void add(Product product)
    {
        canBeSoldIn.add(product);
    }
}
