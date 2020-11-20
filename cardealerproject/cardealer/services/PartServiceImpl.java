package cardealerproject.cardealer.services;

import cardealerproject.cardealer.domain.dtos.PartSeedDto;
import cardealerproject.cardealer.domain.entities.Car;
import cardealerproject.cardealer.domain.entities.Part;
import cardealerproject.cardealer.domain.repositories.PartRepository;
import cardealerproject.cardealer.services.interfaces.CarService;
import cardealerproject.cardealer.services.interfaces.PartService;
import cardealerproject.cardealer.services.interfaces.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierService supplierService;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.supplierService = supplierService;
    }

    @Override
    public void seedParts(Gson gson, ModelMapper modelMapper) {
        try {
            File file = new File("src/main/resources/files/parts.json");
            PartSeedDto[] partSeedDtos = gson.fromJson(new FileReader(file), PartSeedDto[].class);
            Part[] parts = modelMapper.map(partSeedDtos, Part[].class);
            for (Part part : parts) {
                part.setSupplier(this.supplierService.getRandomSupplier());
                this.partRepository.saveAndFlush(part);
            }
            System.out.println("Operation seeding parts is successful.");
        } catch (FileNotFoundException e) {
            System.out.println("Please make sure you've added the files to seed into the right directory!");
            System.out.println("(The right directory is src/main/resources/files)");
            System.out.println("((Create directory files if such does not exist OR change the path is PartServiceImpl seedParts method.))");

        }
    }

    @Override
    public Set<Part> getRandomParts() {
        Random random = new Random();
        int bound = random.nextInt(10) + 10;
        Set<Part> parts = new HashSet<>();
        for (int i = 0; i < bound; i++) {
            int id = random.nextInt((int) this.partRepository.count())+1;
            parts.add(this.partRepository.findById(id));
        }
        return parts;
    }

    @Override
    public void setRandomCars(List<Car> cars) {
        List<Part> all = this.partRepository.findAll();
        for (Part part : all) {
            part.setCars(getRandomCars(cars));
            this.partRepository.saveAndFlush(part);
        }
    }

    private Set<Car> getRandomCars(List<Car> cars) {
        Random random  = new Random();
        int bound = random.nextInt(10)+10;
        Set<Car> c = new LinkedHashSet<>();

        for (int i = 0; i <bound; i++) {
            int id = random.nextInt((int) cars.size())-1;
            if (id == -1){
                id = 0;
            }
            c.add(cars.get(id));
        }
        return c;
    }

}
