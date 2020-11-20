package cardealerproject.cardealer;

import cardealerproject.cardealer.config.ApplicationConfiguration;
import cardealerproject.cardealer.domain.dtos.*;
import cardealerproject.cardealer.domain.entities.*;
import cardealerproject.cardealer.services.interfaces.*;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

@Controller
public class ApplicationController implements CommandLineRunner {
    private final CarService carService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SaleService saleService;
    private final SupplierService supplierService;
    private final ApplicationConfiguration apc;
    private final Scanner sc = new Scanner(System.in);
    private final ConfigurableApplicationContext applicationContext;

    @Autowired
    public ApplicationController(CarService carService, CustomerService customerService, PartService partService, SaleService saleService,
                                 SupplierService supplierService, ApplicationConfiguration apc, ConfigurableApplicationContext applicationContext) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
        this.apc = apc;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        this.supplierService.seedSuppliers(this.apc.gson(), this.apc.modelMapper());
        this.partService.seedParts(this.apc.gson(), this.apc.modelMapper());
        this.carService.seedCars(this.apc.gson(), this.apc.modelMapper());
        this.customerService.seedCustomers(this.apc.gson(), this.apc.modelMapper());
        this.saleService.seedSales(this.apc.modelMapper());

        this.partService.setRandomCars(this.carService.getAllCars());
        ModelMapper modelMapper = this.apc.modelMapper();

        System.out.println("To get quering for Ordered Customers, please press 1.");
        System.out.println("To get quering for Cars from Make Toyota, please press 2.");
        System.out.println("To get quering for Local Suppliers, please press 3.");
        System.out.println("To get quering for Cars with Their List of Parts, please press 4.");
        System.out.println("To get quering for Total Sales by Customer, please press 5.");
        System.out.println("To get quering for Sales with Applied Discount, please press 6.");
        String line = sc.nextLine();
        while (!"exit".equals(line)) {
            switch (line) {
                case "1":
                    Customer[] customers = this.customerService.getOlderDrivers();
                    CustomerDto[] mappedCustomers = getMappedCustomers(modelMapper, customers);
                    writeToJson(mappedCustomers, "ordered-customers");
                    System.out.println("The result from word document is different of the application result!!!");
                    break;
                case "2":
                    Car[] cars = this.carService.getToyotaCars().toArray(Car[]::new);
                    CarDto[] carDtos = modelMapper.map(cars, CarDto[].class);
                    writeToJson(carDtos, "toyota-cars");
                    break;
                case "3":
                    Supplier[] suppliers = this.supplierService.getSuppliersWithoutImporter().toArray(Supplier[]::new);
                    SupplierDto[] supplierDtos = getSupplierDtos(suppliers);
                    writeToJson(supplierDtos, "local-suppliers");
                    System.out.println("The result from word document is different of the application result!!!");
                    break;
                case "4":
                    Car[] cars1 = this.carService.getAllCars().toArray(Car[]::new);
                    CarPartsDto[] carPartsDtos = getCarPartsDto(cars1, modelMapper);
                    writeToJson(carPartsDtos, "cars-and-parts");
                    System.out.println("The result from word document is different of the application result!!!");
                    break;
                case "5":
                    Customer[] customers1 = this.customerService.getAllCustomers().toArray(Customer[]::new);
                    CustomerCarDto[] customerCarDtos = getCustomerCarDto(customers1);
                    writeToJson(customerCarDtos, "customers-total-sales");
                    System.out.println("The result from word document is different of the application result!!!");
                    break;
                case "6":
                    Sale[] sales = this.saleService.getAllSales().toArray(Sale[]::new);
                    SaleCarDto[] saleCarDtos = getSaleCarDtos(sales, modelMapper);
                    writeToJson(saleCarDtos, "sales-discounts");
                    System.out.println("The result from word document is different of the application result!!!");
                    break;
            }
            System.out.println("To get quering for Ordered Customers, please press 1.");
            System.out.println("To get quering for Cars from Make Toyota, please press 2.");
            System.out.println("To get quering for Local Suppliers, please press 3.");
            System.out.println("To get quering for Cars with Their List of Parts, please press 4.");
            System.out.println("To get quering for Total Sales by Customer, please press 5.");
            System.out.println("To get quering for Sales with Applied Discount, please press 6.");
            System.out.println("For exiting the application, please type 'exit'.");
            line = sc.nextLine();
        }
        this.applicationContext.close();
    }

    private SaleCarDto[] getSaleCarDtos(Sale[] sales, ModelMapper mapper) {
        SaleCarDto[] saleCarDtos = new SaleCarDto[sales.length];
        int k = 0;
        for (Sale s : sales) {
            Car car = s.getCar();
            CarSeedDto carDto = mapper.map(car, CarSeedDto.class);
            Customer customer = s.getCustomer();
            double price = getPrice(s);
            double discount = s.getDiscountPercentage();
            if (s.getDiscountPercentage() != 0) {
                saleCarDtos[k++] = new SaleCarDto(carDto, customer.getName(), s.getDiscountPercentage(), price, price);
            }else {
                saleCarDtos[k++] = new SaleCarDto(carDto, customer.getName(), s.getDiscountPercentage(), price, price);
            }
        }
        return saleCarDtos;
    }

    private double getPrice(Sale sale) {
        double total = 0;
        for (Part part : sale.getCar().getParts()) {
            total += part.getPrice();
        }
        return total;
    }

    private CustomerCarDto[] getCustomerCarDto(Customer[] customers1) {
        CustomerCarDto[] customerCarDtos = new CustomerCarDto[customers1.length];
        int k = 0;
        for (Customer c : customers1) {
            customerCarDtos[k++] = new CustomerCarDto(c.getName(), c.getSales().size(), getSpentMoney(c.getSales()));
        }
        return customerCarDtos;
    }

    private double getSpentMoney(Set<Sale> sales) {
        double total = 0;
        for (Sale sale : sales) {
            for (Part part : sale.getCar().getParts()) {
                total += part.getPrice();
            }
        }
        return total;
    }

    private CarPartsDto[] getCarPartsDto(Car[] cars1, ModelMapper modelMapper) {
        CarPartsDto[] carPartsDtos = new CarPartsDto[cars1.length];
        int i = 0;
        for (Car car : cars1) {
            PartDto[] parts = modelMapper.map(car.getParts().toArray(Part[]::new), PartDto[].class);
            carPartsDtos[i++] = new CarPartsDto(car.getId(), car.getMake(), car.getModel(), car.getTravelledDistance(), parts);
        }
        return carPartsDtos;
    }

    private SupplierDto[] getSupplierDtos(Supplier[] suppliers) {
        SupplierDto[] supplierDtos = new SupplierDto[suppliers.length];
        int k = 0;
        for (Supplier s : suppliers) {
            try {
                supplierDtos[k++] = new SupplierDto(s.getId(), s.getName(), s.getParts().size() + 1);
            } catch (Exception e) {
                supplierDtos[k++] = new SupplierDto(s.getId(), s.getName(), 0);
            }
        }
        return supplierDtos;
    }

    private CustomerDto[] getMappedCustomers(ModelMapper modelMapper, Customer[] customers) {
        CustomerDto[] customerDtos = new CustomerDto[customers.length];
        int i = 0;
        for (Customer c : customers) {
            Sale[] sales = c.getSales().toArray(Sale[]::new);
            SaleDto[] saleDtos = new SaleDto[sales.length];
            int k = 0;
            for (Sale s : sales) {
                CarDto carDto = modelMapper.map(s.getCar(), CarDto.class);
                saleDtos[k++] = new SaleDto(s.getId(), carDto);
            }
            customerDtos[i] = new CustomerDto(c.getId(), c.getName(), c.getBirthDate().toString(), c.isYoungDriver(), saleDtos);
            i++;
        }
        return customerDtos;
    }

    private <E> void writeToJson(E[] array, String fileName) {
        Gson gson = this.apc.gson();
        String s = gson.toJson(array);
        BufferedWriter writer = this.apc.writer(fileName);
        try {
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
