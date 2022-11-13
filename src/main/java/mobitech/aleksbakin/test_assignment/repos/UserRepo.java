package mobitech.aleksbakin.test_assignment.repos;

import mobitech.aleksbakin.test_assignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername (String username);
}
