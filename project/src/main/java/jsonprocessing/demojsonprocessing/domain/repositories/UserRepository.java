package jsonprocessing.demojsonprocessing.domain.repositories;

import jsonprocessing.demojsonprocessing.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    //have at least 1 item sold with a buyer. Order them by last name, then by first name
    @Query("select u from User as u join u.sold as s group by u.firstName having s.size >= 1  " +
            " order by u.lastName, u.firstName")
    List<User> findAllSuccessfulSellers();
}
