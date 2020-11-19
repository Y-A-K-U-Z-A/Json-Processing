package jsonprocessing.demojsonprocessing.configurations;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.BufferedWriter;

public interface Configuration {
    Gson gson();
    ModelMapper modelMapper();
    Validator validator();
    BufferedWriter writer(String fileName);
}
