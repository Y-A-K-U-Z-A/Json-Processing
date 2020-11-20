package cardealerproject.cardealer.services;

import cardealerproject.cardealer.domain.entities.Car;
import cardealerproject.cardealer.domain.entities.Customer;
import cardealerproject.cardealer.domain.entities.Sale;
import cardealerproject.cardealer.domain.repositories.SaleRepository;
import cardealerproject.cardealer.services.interfaces.CarService;
import cardealerproject.cardealer.services.interfaces.CustomerService;
import cardealerproject.cardealer.services.interfaces.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final double[] discounts = new double[]{0, 5, 10, 15, 20, 30, 40, 50};

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void seedSales(ModelMapper modelMapper) {
//        sales records by randomly selecting a car, customer and the amount of discount to be applied
        for (int i = 0; i < 100; i++) {
            Car car = this.carService.getRandomCar();
            Customer customer = this.customerService.getRandomCustomer();
            Random random = new Random();
            int index = random.nextInt(discounts.length) - 1;
            if (index == -1) {
                index = 0;
            }else if (index == discounts.length){
                index = discounts.length-1;
            }
            double discount = discounts[index];
            this.saleRepository.saveAndFlush(new Sale(discount, car, customer));
        }
        System.out.println("Operation seeding sales is successful.");
    }

    @Override
    public List<Sale> getAllSales() {
        return this.saleRepository.findAll();
    }
}
