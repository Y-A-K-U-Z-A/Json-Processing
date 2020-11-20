package cardealerproject.cardealer.services;

import cardealerproject.cardealer.domain.dtos.CustomerSeedDto;
import cardealerproject.cardealer.domain.entities.Customer;
import cardealerproject.cardealer.domain.repositories.CustomerRepository;
import cardealerproject.cardealer.services.interfaces.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedCustomers(Gson gson, ModelMapper modelMapper) {
        try {
            File file = new File("src/main/resources/files/customers.json");
            CustomerSeedDto[] customerSeedDtos = gson.fromJson(new FileReader(file), CustomerSeedDto[].class);

            Customer[] customers = modelMapper.map(customerSeedDtos, Customer[].class);
            for (Customer c : customers) {
                this.customerRepository.saveAndFlush(c);
            }

            System.out.println("Operation seeding customers is successful.");
        } catch (FileNotFoundException e) {
            System.out.println("Please make sure you've added the files to seed into the right directory!");
            System.out.println("(The right directory is src/main/resources/files)");
            System.out.println("((Create directory files if such does not exist OR change the path is CustomerServiceImpl seedCustomers method.))");
        }
    }

    @Override
    public Customer getRandomCustomer() {
        Random random = new Random();
        int id = random.nextInt((int) this.customerRepository.count()) + 1;
        return this.customerRepository.findById(id);
    }

    @Override
    public Customer[] getOlderDrivers() {
        List<Customer> allCustomers = this.customerRepository.findAllCustomers();
        return allCustomers.stream().sorted((c, c1) -> {
            LocalDate first = LocalDate.of(c.getBirthDate().getYear(), c.getBirthDate().getMonth(), c.getBirthDate().getDayOfMonth());
            LocalDate second = LocalDate.of(c1.getBirthDate().getYear(), c1.getBirthDate().getMonth(), c1.getBirthDate().getDayOfMonth());
                if (first.equals(second)) {
                if (!c.isYoungDriver()) {
                    return -1;
                } return 0;
            }
            return 0;
        }).toArray(Customer[]::new);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAllCustomers();
    }
}
