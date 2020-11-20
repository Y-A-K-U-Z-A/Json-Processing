package cardealerproject.cardealer.services;

import cardealerproject.cardealer.domain.dtos.CarSeedDto;
import cardealerproject.cardealer.domain.entities.Car;
import cardealerproject.cardealer.domain.repositories.CarRepository;
import cardealerproject.cardealer.services.interfaces.CarService;
import cardealerproject.cardealer.services.interfaces.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartService partService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartService partService) {
        this.carRepository = carRepository;
        this.partService = partService;
    }

    @Override
    public void seedCars(Gson gson, ModelMapper modelMapper) {
        try{
            File file = new File("src/main/resources/files/cars.json");
            CarSeedDto[] carSeedDtos = gson.fromJson(new FileReader(file), CarSeedDto[].class);
            Car[] cars = modelMapper.map(carSeedDtos, Car[].class);
            for (Car car : cars) {
                car.setParts(this.partService.getRandomParts());
                this.carRepository.saveAndFlush(car);
            }
            System.out.println("Operation seeding cars is successful.");
        }catch (FileNotFoundException e){
            System.out.println("Please make sure you've added the files to seed into the right directory!");
            System.out.println("(The right directory is src/main/resources/files)");
            System.out.println("((Create directory files if such does not exist OR change the path is CarServiceImpl seedCars method.))");
        }
    }

    @Override
    public Car getRandomCar() {
        Random random = new Random();
        int id = random.nextInt((int) this.carRepository.count())+1;
        return this.carRepository.findById(id);
    }

    @Override
    public List<Car> getToyotaCars() {
        return this.carRepository.findByMakeOrderByModel("Toyota");
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

}
