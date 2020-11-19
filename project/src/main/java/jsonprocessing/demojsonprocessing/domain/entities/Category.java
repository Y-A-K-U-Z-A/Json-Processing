package jsonprocessing.demojsonprocessing.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity{
    @Size(min = 3, max = 15)
    @NonNull @Getter @Setter
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER) @Getter @Setter
    private Set<Product> products;
}
