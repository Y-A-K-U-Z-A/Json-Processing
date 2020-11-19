package jsonprocessing.demojsonprocessing;

import com.google.gson.Gson;
import jsonprocessing.demojsonprocessing.configurations.ApplicationConfiguration;
import jsonprocessing.demojsonprocessing.domain.dtos.CategoryDto;
import jsonprocessing.demojsonprocessing.domain.dtos.ProductInRangeDto;
import jsonprocessing.demojsonprocessing.domain.dtos.ProductsInRangeNamesDto;
import jsonprocessing.demojsonprocessing.domain.dtos.UserSellerBuyerDto;
import jsonprocessing.demojsonprocessing.domain.entities.Category;
import jsonprocessing.demojsonprocessing.domain.entities.Product;
import jsonprocessing.demojsonprocessing.domain.entities.User;
import jsonprocessing.demojsonprocessing.services.interfaces.CategoryService;
import jsonprocessing.demojsonprocessing.services.interfaces.ProductService;
import jsonprocessing.demojsonprocessing.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ApplicationController implements CommandLineRunner {
    private final UserService userService;
    private final ApplicationConfiguration apc;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Scanner sc = new Scanner(System.in);
    @Autowired
    ConfigurableApplicationContext applicationContext;


    @Autowired
    public ApplicationController(UserService userService, ApplicationConfiguration apc, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.apc = apc;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsers(apc.gson(), apc.modelMapper());
        this.categoryService.seedCategories(apc.gson(), apc.modelMapper());
        this.productService.seedProducts(apc.gson(), apc.modelMapper(), this.categoryService.getAllCategories());
        this.categoryService.setRandomProducts();

        ModelMapper modelMapper = this.apc.modelMapper();

        System.out.println("For quering products in range, please press 1:");
        System.out.println("For quering successfully sold products, please press 2:");
        System.out.println("For quering categories by products count, please press 3:");
        System.out.println("For quering users and products, please press 4:");
        String line = sc.nextLine();
        while (!"exit".equals(line)) {
            switch (line) {
                case "1":
                    System.out.println("The values from Word document ARE different with these from db.");
                    Product[] productsToMap = this.productService.getProductsInRange();
                    List<ProductInRangeDto> products = getProductsMapped(productsToMap);
                    writeToJson(products.toArray(ProductInRangeDto[]::new), "products-in-range");
                    System.out.println("Please check the file products-in-range in project folder.");
                    break;
                case "2":
                    System.out.println("The values from Word document ARE different with these from db.");
                    User[] toMap = this.userService.getAllSuccessfulSellers().toArray(User[]::new);
                    UserSellerBuyerDto[] users = getUserArrayDto(toMap, modelMapper);
                    writeToJson(users, "users-sold-products");
                    System.out.println("Please check the file users-sold-products in project folder.");
                    break;
                case "3":
                    System.out.println("The values from Word document ARE different with these from db.");
                    Category[] allCategories = this.categoryService.getAllCategories().toArray(Category[]::new);
                    CategoryDto[] categoryDtos = getCategoryArrayDto(allCategories);
                    writeToJson(categoryDtos, "categories-by-products");
                    break;
                case "4":
                    System.out.println("Нямах нервите да я правя 4-та.");

                    break;
            }
            System.out.println("For quering products in range, please press 1:");
            System.out.println("For quering successfully sold products, please press 2:");
            System.out.println("For quering categories by products count, please press 3:");
            System.out.println("For quering users and products, please press 4:");
            System.out.println("To quit please press 'exit'");
            line = sc.nextLine();
        }
        this.applicationContext.close();
    }

    private CategoryDto[] getCategoryArrayDto(Category[] allCategories) {
        CategoryDto[] categoryDtos = new CategoryDto[allCategories.length];
        int i = 0;
        for (Category c : allCategories) {
            categoryDtos[i] = new CategoryDto(c.getName(), c.getProducts().size(), getCategoryProductsInfo(c, "avg"),
                    getCategoryProductsInfo(c, "total"));
            i++;
        }
        return categoryDtos;
    }

    private double getCategoryProductsInfo(Category c, String type) {
        double total = 0;
        for (Product p : c.getProducts()) {
            total += p.getPrice();
        }
        switch (type) {
            case "avg":
                return total / c.getProducts().size();
            case "total":
                return total;
        }
        return 0;
    }

    private UserSellerBuyerDto[] getUserArrayDto(User[] toMap, ModelMapper modelMapper) {
        UserSellerBuyerDto[] users = new UserSellerBuyerDto[toMap.length];
        int i = 0;
        for (User user : toMap) {
            Product[] p = user.getSold().toArray(Product[]::new);
            ProductsInRangeNamesDto[] soldProducts = modelMapper.map(Arrays.stream(p).filter(pi -> pi.getBuyer() != null).toArray(),
                    ProductsInRangeNamesDto[].class);

            users[i] = modelMapper.map(user, UserSellerBuyerDto.class);
            users[i].setSoldProducts(Arrays.stream(soldProducts).collect(Collectors.toList()));
            i++;
        }
        return users;
    }

    private <E> void writeToJson(E[] array, String fileName) throws IOException {
        Gson gson = this.apc.gson().newBuilder().serializeSpecialFloatingPointValues().setPrettyPrinting().create();
        String s = gson.toJson(array);
        BufferedWriter writer = this.apc.writer(fileName);
        writer.write(s);
        writer.close();
    }

    private List<ProductInRangeDto> getProductsMapped(Product[] productsToMap) {
        List<ProductInRangeDto> products = new LinkedList<>();
        for (Product p : productsToMap) {
            products.add(new ProductInRangeDto(p.getName(), p.getPrice(), p.getSeller().getFirstName() + " " + p.getSeller().getLastName()));
        }
        return products;
    }
}
