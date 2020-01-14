package Tables;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected String companyName;

    @Embedded
    protected Address address;

    public Company(){};
}

