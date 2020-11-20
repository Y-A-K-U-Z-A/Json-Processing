package cardealerproject.cardealer.services.interfaces;

import cardealerproject.cardealer.domain.entities.Car;
import cardealerproject.cardealer.domain.entities.Part;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;

public interface PartService {
    void seedParts(Gson gson, ModelMapper modelMapper);
    Set<Part> getRandomParts();
    void setRandomCars(List<Car> cars);
}
