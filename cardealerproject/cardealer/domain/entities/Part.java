package cardealerproject.cardealer.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parts")
@AllArgsConstructor
@NoArgsConstructor
public class Part extends BaseEntity{
    //•	Parts have name, price and quantity.
    @Getter @Setter
    private String name;
    @Getter @Setter
    private double price;
    @Getter @Setter
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @Getter @Setter
    private Supplier supplier;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parts_cars", joinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"),
                                    inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    @Getter @Setter
    private Set<Car> cars;
}
