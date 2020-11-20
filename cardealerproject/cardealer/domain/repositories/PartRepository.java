package cardealerproject.cardealer.domain.repositories;

import cardealerproject.cardealer.domain.entities.Part;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends BaseRepository<Part, Long> {
    Part findById(long id);
}
