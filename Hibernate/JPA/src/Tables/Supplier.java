package Tables;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Supplier extends Company
{
    private String bankAccountNumber;

    @OneToMany
    @JoinColumn(name = "SupplierId")
    private Set<Product> supplies;

    public Supplier(String companyName, String city, String street, int number)
    {
        this.companyName = companyName;
        this.supplies = new LinkedHashSet<Product>();
        this.address = new Address(city, street, number);
    }

    public Supplier(String companyName, String street, String city)
    {
        this.companyName = companyName;
        this.supplies = new LinkedHashSet<Product>();
        this.address = new Address(city, street, 0);
    }

    public  Supplier(){}

    public void add(Product product)
    {
        supplies.add(product);
    }

}

