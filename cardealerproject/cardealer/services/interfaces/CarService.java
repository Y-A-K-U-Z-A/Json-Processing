package cardealerproject.cardealer.services.interfaces;

import cardealerproject.cardealer.domain.entities.Car;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CarService {
    void seedCars(Gson gson, ModelMapper modelMapper);

    Car getRandomCar();

    List<Car> getToyotaCars();

    List<Car> getAllCars();

}
