package wam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wam.model.User;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);

}
