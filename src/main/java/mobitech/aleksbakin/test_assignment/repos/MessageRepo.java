package mobitech.aleksbakin.test_assignment.repos;

import mobitech.aleksbakin.test_assignment.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
