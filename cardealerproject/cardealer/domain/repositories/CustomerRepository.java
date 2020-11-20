package cardealerproject.cardealer.domain.repositories;

import cardealerproject.cardealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long>{
    Customer findById(long id);
    @Query("select s from Customer s order by s.birthDate asc")
    List<Customer> findAllCustomers();
}
