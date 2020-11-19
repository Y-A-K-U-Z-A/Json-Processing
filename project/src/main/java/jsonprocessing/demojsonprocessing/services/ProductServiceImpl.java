package jsonprocessing.demojsonprocessing.services;

import com.google.gson.Gson;
import jsonprocessing.demojsonprocessing.domain.dtos.ProductSeedDto;
import jsonprocessing.demojsonprocessing.domain.entities.Category;
import jsonprocessing.demojsonprocessing.domain.entities.Product;
import jsonprocessing.demojsonprocessing.domain.entities.User;
import jsonprocessing.demojsonprocessing.domain.repositories.ProductRepository;
import jsonprocessing.demojsonprocessing.services.interfaces.CategoryService;
import jsonprocessing.demojsonprocessing.services.interfaces.ProductService;
import jsonprocessing.demojsonprocessing.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void seedProducts(Gson gson, ModelMapper modelMapper, List<Category> categories) {
        try {
            File file = new File("src/main/resources/files/products.json");
            ProductSeedDto[] productSeedDtos = gson.fromJson(new FileReader(file), ProductSeedDto[].class);

            for (int i = 0; i < productSeedDtos.length; i++) {
                ProductSeedDto current = productSeedDtos[i];
                Product product = modelMapper.map(current, Product.class);
                User seller = this.userService.getRandomUser(product, 0, "seller");
                if (i <= productSeedDtos.length - 20) {
                    User buyer = this.userService.getRandomUser(product, seller.getId(), "buyer");
                    product.setBuyer(buyer);
                }
                product.setSeller(seller);
                product.setCategories(getRandomCategories(categories));
                productRepository.save(product);
                productRepository.flush();
            }

            System.out.println("Тhe  S E E D  operation is successful");
        } catch (FileNotFoundException e) {
            System.out.println("Please make sure you've added the files to seed into the right directory!");
            System.out.println("(The right directory is src/main/resources/files)");
            System.out.println("((Create directory files if such does not exist OR change the path is ProductServiceImpl seedUsers method.))");
        }
    }

    private Set<Category> getRandomCategories(List<Category> categories) {
        Random random = new Random();
        int id = random.nextInt(categories.size())-1;
        if (id == -1 ){
            id = 0;
        }
        if (id == categories.size()){
            id = categories.size()-1;
        }
        Set<Category> categoriesSet = new HashSet<>();
        categoriesSet.add(categories.get(id));
        id = random.nextInt(categories.size()-1);
        if (id == -1 ){
            id = 0;
        }
        if (id == categories.size()){
            id = categories.size()-1;
        }
        categoriesSet.add(categories.get(id));
        return categoriesSet;
    }

    @Override
    public Set<Product> getRandomProducts() {
        Random random = new Random();
        int id = random.nextInt((int) this.productRepository.count())+1;
        Product product = this.productRepository.findById(id);
        id = random.nextInt((int) this.productRepository.count())+1;
        Product product1 = this.productRepository.findById(id);
        Set<Product> products = new HashSet<>();
        products.add(product);
        products.add(product1);
        return products;
    }

    @Override
    public Product[] getProductsInRange() {
        return this.productRepository.findByPrice().toArray(Product[]::new);
    }


}
