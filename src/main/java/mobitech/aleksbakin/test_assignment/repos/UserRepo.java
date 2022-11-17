package mobitech.aleksbakin.test_assignment.repos;

import mobitech.aleksbakin.test_assignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    //User findByUsername (String username);
    User findByEmail(String email);
}
