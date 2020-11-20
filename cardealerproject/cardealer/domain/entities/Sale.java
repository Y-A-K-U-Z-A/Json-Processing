package cardealerproject.cardealer.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
public class Sale extends BaseEntity{
    //•	Sale has car, customer and discount percentage.
    @Getter @Setter
    private double discountPercentage;
//    •	Each sale has one customer and a customer can buy many cars
    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @Getter @Setter
    private Car car;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @Getter @Setter
    private Customer customer;
}
