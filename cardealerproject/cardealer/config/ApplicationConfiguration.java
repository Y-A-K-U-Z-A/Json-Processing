package cardealerproject.cardealer.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;

import java.io.BufferedWriter;

public interface ApplicationConfiguration {
    ModelMapper modelMapper();
    Gson gson();
    BufferedWriter writer(String fileName);
}
