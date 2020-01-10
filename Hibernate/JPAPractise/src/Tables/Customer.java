package Tables;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Customer extends Company
{
    double discount = 0;

    @OneToMany
    @JoinColumn(name = "CustomerId")
    private Set<Ordered> orders;

    public void setDiscount(double discount)
    {
        if(discount > 0 && discount < 1)
        {
            this.discount = discount;
        }
    }

    public Customer(String companyName, String city, String street, int number, double discount)
    {
        this.companyName = companyName;
        this.address = new Address(city, street, number);
        this.orders  = new LinkedHashSet<>();
        setDiscount(discount);
    }

    public void addOrder(Ordered order)
    {
        if(orders == null) orders = new LinkedHashSet<>();
        orders.add(order);
    }

    public Customer(){}
}

