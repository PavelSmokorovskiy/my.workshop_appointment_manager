package wam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wam.model.Appointment;
import wam.model.User;

import java.util.List;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByAppointmentId(Long appointmentId);

    List<Appointment> findByUser_UserId(Long uerId);

//    List<Appointment> findByUserContains(User user);
//    List<Appointment> findByDateTimeContains(String dateTime);

}
