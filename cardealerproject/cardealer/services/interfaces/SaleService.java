package cardealerproject.cardealer.services.interfaces;

import cardealerproject.cardealer.domain.entities.Sale;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;

public interface SaleService {
    void seedSales(ModelMapper modelMapper);

    List<Sale> getAllSales();
}
