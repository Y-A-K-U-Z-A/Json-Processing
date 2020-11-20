package cardealerproject.cardealer.domain.repositories;

import cardealerproject.cardealer.domain.entities.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends BaseRepository<Car, Long>{
    Car findById(long id);

    List<Car> findByMakeOrderByModel(String toyota);
}
