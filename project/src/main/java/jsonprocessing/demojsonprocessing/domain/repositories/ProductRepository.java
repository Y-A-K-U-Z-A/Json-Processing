package jsonprocessing.demojsonprocessing.domain.repositories;

import jsonprocessing.demojsonprocessing.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);
//    e.g. 500 to 1000), which have no buyer. Order them by price (from lowest to highest). Select only the product name, price and the full name of the seller.
    @Query("select p from Product as p where p.price > 500 and p.price < 1000 and p.buyer is null order by p.price desc")
    List<Product> findByPrice();
}
