package cardealerproject.cardealer.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{
    //•	Customer has name, date of birth and info whether he/she is a young driver
    // (Young driver is a driver that has less than 2 years of experience. Those customers get additional 5% off for the sale.).
    @Getter @Setter
    private String name;
    @Getter @Setter
    private LocalDateTime birthDate;
    @Getter @Setter
    private boolean isYoungDriver;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @Getter @Setter
    private Set<Sale>  sales;
}
