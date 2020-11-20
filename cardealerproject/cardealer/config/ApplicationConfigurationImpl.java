package cardealerproject.cardealer.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationConfigurationImpl implements ApplicationConfiguration {


    @Bean
    @Override
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Override
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>(){
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
                        return LocalDateTime
                                .parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    }
        })
            .create();
    }

    @Override
    public BufferedWriter writer(String fileName) {
        try {
            return new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Something went wrong with file writer.");
            return null;
        }
    }


}
