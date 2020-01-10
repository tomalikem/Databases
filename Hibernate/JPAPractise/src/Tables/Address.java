package Tables;

import javax.persistence.Embeddable;

@Embeddable
public class Address
{
    private String city;
    private String street;
    private int number;

    public Address(String city, String street, int number)
    {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Address(){}
}

