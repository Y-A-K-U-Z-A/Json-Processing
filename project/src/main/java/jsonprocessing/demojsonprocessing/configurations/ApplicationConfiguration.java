package jsonprocessing.demojsonprocessing.configurations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class ApplicationConfiguration implements Configuration{

    private Validator validator;

    public ApplicationConfiguration(){}

    @Override
    public Gson gson() {
        return new Gson();
    }

    @Override
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public Validator validator() {
        return this.validator;
    }

    @Override
    public BufferedWriter writer(String fileName) {
        try{
        return new BufferedWriter(new FileWriter(fileName));
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
