package jsonprocessing.demojsonprocessing.services.interfaces;

import com.google.gson.Gson;
import jsonprocessing.demojsonprocessing.domain.entities.Category;
import jsonprocessing.demojsonprocessing.domain.entities.Product;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface ProductService {
    void seedProducts(Gson gson, ModelMapper modelMapper, List<Category> categories);

    Set<Product> getRandomProducts();

    Product[] getProductsInRange();
}
