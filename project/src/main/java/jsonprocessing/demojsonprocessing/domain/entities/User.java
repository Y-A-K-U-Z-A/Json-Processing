package jsonprocessing.demojsonprocessing.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private int age;
    @ManyToMany(fetch = FetchType.EAGER) @Getter @Setter
    @JoinTable(name = "users_friends", joinColumns ={@JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "friend_id", referencedColumnName = "id")})
    private Set<User> friends;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller")     @Getter @Setter
    private Set<Product> sold = new HashSet<>();
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "buyer")    @Getter @Setter
    private Set<Product> bought = new HashSet<>();

}
