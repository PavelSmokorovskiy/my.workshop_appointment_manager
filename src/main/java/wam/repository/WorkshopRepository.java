package wam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wam.model.Workshop;

import java.util.List;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {

    Workshop findByWorkshopId(Long workshopId);

    List<Workshop> findByCompanyNameContains(String companyName);
    List<Workshop> findByCityContains(String city);

}
