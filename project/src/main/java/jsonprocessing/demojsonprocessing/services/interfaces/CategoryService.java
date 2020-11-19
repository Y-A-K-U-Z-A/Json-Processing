package jsonprocessing.demojsonprocessing.services.interfaces;

import com.google.gson.Gson;
import jsonprocessing.demojsonprocessing.domain.entities.Category;
import jsonprocessing.demojsonprocessing.domain.entities.Product;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(Gson gson, ModelMapper modelMapper);

    Set<Category> getRandomCategories(Product product);

    void setRandomProducts();

    List<Category> getAllCategories();
}
