package cardealerproject.cardealer.services;

import cardealerproject.cardealer.domain.dtos.SupplierSeedDto;
import cardealerproject.cardealer.domain.entities.Supplier;
import cardealerproject.cardealer.domain.repositories.SupplierRepository;
import cardealerproject.cardealer.services.interfaces.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Random;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedSuppliers(Gson gson, ModelMapper modelMapper) {
        try {
            File file = new File("src/main/resources/files/suppliers.json");
            SupplierSeedDto[] supplierSeedDtos = gson.fromJson(new FileReader(file), SupplierSeedDto[].class);

            Supplier[] suppliers = modelMapper.map(supplierSeedDtos, Supplier[].class);
            for (Supplier supplier : suppliers) {
                this.supplierRepository.saveAndFlush(supplier);
            }
            System.out.println("Operation seeding suppliers is successful.");
        }catch (FileNotFoundException e) {
            System.out.println("Please make sure you've added the files to seed into the right directory!");
            System.out.println("(The right directory is src/main/resources/files)");
            System.out.println("((Create directory files if such does not exist OR change the path is SupplierServiceImpl seedSuppliers method.))");
        }
    }

    @Override
    public Supplier getRandomSupplier(){
        Random random = new Random();
        int id  = random.nextInt((int) supplierRepository.count())+1;
        return this.supplierRepository.findById(id);
    }

    @Override
    public List<Supplier> getSuppliersWithoutImporter() {
        return this.supplierRepository.findAllByImporterIsFalse();
    }

}
