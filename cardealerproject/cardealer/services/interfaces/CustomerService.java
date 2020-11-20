package cardealerproject.cardealer.services.interfaces;

import cardealerproject.cardealer.domain.entities.Customer;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;

public interface CustomerService {
    void seedCustomers(Gson gson, ModelMapper modelMapper);

    Customer getRandomCustomer();

    Customer[] getOlderDrivers();

    List<Customer> getAllCustomers();
}
