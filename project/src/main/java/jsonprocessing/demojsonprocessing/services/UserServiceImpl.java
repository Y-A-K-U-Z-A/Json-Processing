package jsonprocessing.demojsonprocessing.services;

import com.google.gson.Gson;
import jsonprocessing.demojsonprocessing.configurations.ApplicationConfiguration;
import jsonprocessing.demojsonprocessing.domain.dtos.UserSeedDto;
import jsonprocessing.demojsonprocessing.domain.entities.Product;
import jsonprocessing.demojsonprocessing.domain.entities.User;
import jsonprocessing.demojsonprocessing.domain.repositories.UserRepository;
import jsonprocessing.demojsonprocessing.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationConfiguration applicationConfiguration;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ApplicationConfiguration applicationConfiguration) {
        this.userRepository = userRepository;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    public void seedUsers(Gson gson, ModelMapper modelMapper) {
        try {
            File file = new File("src/main/resources/files/users.json");
            UserSeedDto[] usersDto = gson.fromJson(new FileReader(file), UserSeedDto[].class);

            User[] users = modelMapper.map(usersDto, User[].class);
            Arrays.stream(users).forEach(userRepository::saveAndFlush);

            System.out.println("Тhe  S E E D  operation is successful");
        } catch (Exception e) {
            System.out.println("Please make sure you've added the files to seed into the right directory!");
            System.out.println("(The right directory is src/main/resources/files)");
            System.out.println("((Create directory files if such does not exist OR change the path is UserServiceImpl seedUsers method.))");
        }
    }

    @Override
    public User getRandomUser(Product product, long ids, String type) {
        Random random = new Random();
        int id = random.nextInt((int) this.userRepository.count())+1;
        if (id == ids){
            id = random.nextInt((int) this.userRepository.count()) +1;
        }

        User user = this.userRepository.findById(id);

        switch (type){
            case "seller":
                if (!user.getSold().isEmpty()){
                    Set<Product> sold = user.getSold();
                    sold.add(product);
                    user.setSold(sold);
                }else {
                    Set<Product> set = new LinkedHashSet<>();
                    set.add(product);
                    user.setSold(set);
                }
                break;
            case "buyer":
                if (!user.getBought().isEmpty()){
                    Set<Product> sold = user.getBought();
                    sold.add(product);
                    user.setBought(sold);
                }else {
                    Set<Product> set = new HashSet<>();
                    set.add(product);
                    user.setBought(set);
                }
                 break;
        }

        this.userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public List<User> getAllSuccessfulSellers() {
        return this.userRepository.findAllSuccessfulSellers();
    }


}
