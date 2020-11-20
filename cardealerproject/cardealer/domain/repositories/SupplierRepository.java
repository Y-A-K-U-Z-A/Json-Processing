package cardealerproject.cardealer.domain.repositories;

import cardealerproject.cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends BaseRepository<Supplier, Long>{
    Supplier findById(long id);
    @Query("select s from Supplier s where s.isImporter = false")
    List<Supplier> findAllByImporterIsFalse();
}
