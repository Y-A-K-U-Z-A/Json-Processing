package cardealerproject.cardealer.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity{
//    •	Cars have make, model, and travelled distance in kilometers.
    @Getter @Setter
    private String make;
    @Getter @Setter
    private String model;
    @Getter @Setter
    private long travelledDistance;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "cars")
    @Getter @Setter
    private Set<Part> parts;
    @OneToOne(mappedBy = "car")
    private Sale sale;
}
