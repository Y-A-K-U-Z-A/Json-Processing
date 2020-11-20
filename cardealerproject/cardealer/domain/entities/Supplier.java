package cardealerproject.cardealer.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
public class Supplier extends BaseEntity{
//       •	One supplier can supply many parts and each part can be delivered by only one supplier
    @Getter @Setter
    private String name;
    @Getter @Setter
    private boolean isImporter;
    //•	Part supplier have name and info whether he uses imported parts.
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    @Getter @Setter
    private Set<Part> parts;
}
