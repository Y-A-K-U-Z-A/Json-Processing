package jsonprocessing.demojsonprocessing.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{
    @Size(min = 3)
    @NonNull @Getter @Setter
    private String name;
    @NonNull @Getter @Setter
    private double price;
    @ManyToMany(fetch = FetchType.EAGER) @Getter
    @Setter
    private Set<Category> categories;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotNull @Getter @Setter
    private User seller;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter @Setter
    private User buyer;

}
