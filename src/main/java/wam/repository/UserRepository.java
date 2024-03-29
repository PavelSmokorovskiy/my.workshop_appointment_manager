package wam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wam.model.User;

import java.util.List;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);

    List<User> findByUsernameContains(String username);
}
