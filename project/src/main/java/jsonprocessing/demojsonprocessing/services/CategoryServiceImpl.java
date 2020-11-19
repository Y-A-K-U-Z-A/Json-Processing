package jsonprocessing.demojsonprocessing.services;

import com.google.gson.Gson;
import jsonprocessing.demojsonprocessing.domain.dtos.CategorySeedDto;
import jsonprocessing.demojsonprocessing.domain.entities.Category;
import jsonprocessing.demojsonprocessing.domain.entities.Product;
import jsonprocessing.demojsonprocessing.domain.repositories.CategoryRepository;
import jsonprocessing.demojsonprocessing.services.interfaces.CategoryService;
import jsonprocessing.demojsonprocessing.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public void seedCategories(Gson gson, ModelMapper modelMapper) {
        try {
            File file = new File("src/main/resources/files/categories.json");
            CategorySeedDto[] categorySeedDtos = gson.fromJson(new FileReader(file), CategorySeedDto[].class);
            Category[] categories = modelMapper.map(categorySeedDtos, Category[].class);

            for (Category category : categories) {
                categoryRepository.saveAndFlush(category);
            }
            System.out.println("Тhe  S E E D  operation is successful");
        } catch (Exception e) {
            System.out.println("Please make sure you've added the files to seed into the right directory!");
            System.out.println("(The right directory is src/main/resources/files)");
            System.out.println("((Create directory files if such does not exist OR change the path is UserServiceImpl seedUsers method.))");
        }
    }

    public void setRandomProducts() {
        List<Category> categories = this.categoryRepository.findAll();
        for (Category category : categories) {
            category.setProducts(productService.getRandomProducts());
            this.categoryRepository.saveAndFlush(category);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Set<Category> getRandomCategories(Product product) {
        Random random = new Random();
        int id = random.nextInt((int) categoryRepository.count())+1;
        Category category = this.categoryRepository.findById((long) id);
        id = random.nextInt((int) categoryRepository.count())+1;
        Category category1 = this.categoryRepository.findById(id);
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        categories.add(category1);
        return categories;
    }
}
