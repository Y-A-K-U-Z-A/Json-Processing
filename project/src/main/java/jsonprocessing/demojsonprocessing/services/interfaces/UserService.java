package jsonprocessing.demojsonprocessing.services.interfaces;

import com.google.gson.Gson;
import jsonprocessing.demojsonprocessing.domain.entities.Product;
import jsonprocessing.demojsonprocessing.domain.entities.User;
import org.modelmapper.ModelMapper;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {
    void seedUsers(Gson gson, ModelMapper modelMapper) throws FileNotFoundException;

    User getRandomUser(Product product, long id, String type);

    List<User> getAllSuccessfulSellers();
}
