package cardealerproject.cardealer.services.interfaces;

import cardealerproject.cardealer.domain.entities.Supplier;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;

public interface SupplierService {
    void seedSuppliers(Gson gson, ModelMapper modelMapper);
    Supplier getRandomSupplier();

    List<Supplier> getSuppliersWithoutImporter();
}
