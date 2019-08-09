package wam.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wam.config.Logging;
import wam.model.Appointment;
import wam.model.ResponseDescription;
import wam.model.User;
import wam.model.Workshop;
import wam.repository.AppointmentRepository;
import wam.repository.UserRepository;
import wam.repository.WorkshopRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for Appointments
 */
@RestController
@RequestMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {

    private final Logging logger = new Logging(LogManager.getLogger(this.getClass()));

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    /**
     * Get total number of appointments
     *
     * @link localhost:8080/appointment/total
     */
    @GetMapping(value = "/total")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Long total() {
        return workshopRepository.count();
    }

    /**
     * Get all appointments
     *
     * @link http://localhost:8080/appointment/
     */
    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Appointment> getAllAppointments(HttpServletRequest request) {

        logger.requestStart(request);

        List<Appointment> appointments = appointmentRepository.findAll();

        logger.requestStop(appointments);
        return appointments;
    }

    /**
     * Get a appointment by id
     *
     * @link localhost:8080/appointment/id/1
     */
    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity getIdAppointment(@PathVariable("id") final Long id, HttpServletRequest request) {

        logger.requestStart(request);

        Appointment appointment = appointmentRepository.findByAppointmentId(id);

        logger.requestStop(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    /**
     * Create an appointment
     *
     * @link http://localhost:8080/appointment/userId/userId/workshopId/workshopId/time/time
     */
    @PostMapping("/userId/{userId}/workshopId/{workshopId}/time/{time}")
    @ResponseBody
    public ResponseEntity createWorkshop(
            @PathVariable("userId") final Long userId
            , @PathVariable("workshopId") final Long workshopId
            , @PathVariable("time") final LocalDateTime time
            , HttpServletRequest request) {

        logger.requestStart(request);

        User user = userRepository.findByUserId(userId);
        Workshop workshop = workshopRepository.findByWorkshopId(workshopId);

        if (userId != null && user == null) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("User with id "
                    + userId + " was not found."), HttpStatus.NOT_FOUND);
        }

        if (workshop != null && workshop == null) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Workshop with id "
                    + workshopId + " was not found."), HttpStatus.NOT_FOUND);
        }

        try {
            Appointment appointment = new Appointment(
                    user
                    , workshop
                    , time);
            appointmentRepository.save(appointment);

            logger.requestStop(appointment);
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Didn't create")
                    , HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Update a appointment data
     *
     * @link localhost:8080/appointment/userId/userId/workshopId/workshopId/time/time
     */
    @PutMapping(value = "/id/{id}/userId/{userId}/workshopId/{workshopId}/time/{time}"
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateWorkshop(
            @PathVariable("id") final Long id
            , @PathVariable("userId") final Long userId
            , @PathVariable("workshopId") final Long workshopId
            , @PathVariable("time") final LocalDateTime time
            , HttpServletRequest request) {

        logger.requestStart(request);

        User user = userRepository.findByUserId(userId);
        Workshop workshop = workshopRepository.findByWorkshopId(workshopId);

        if (userId != null && user == null) {
            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("User with id "
                    + userId + " was not found."), HttpStatus.NOT_FOUND);
        }

        if (workshop != null && workshop == null) {
            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Workshop with id "
                    + workshopId + " was not found."), HttpStatus.NOT_FOUND);
        }

        try {

            Appointment appointment = appointmentRepository.findByAppointmentId(id);
            appointment.setUser(user);
            appointment.setWorkshop(workshop);
            appointment.setTime(time);
            appointmentRepository.save(appointment);

            logger.requestStop();
            return new ResponseEntity<>(workshop, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Didn't update")
                    , HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Delete an appointment
     *
     * @link localhost:8080/appointment/id/1
     */
    @DeleteMapping(value = "/id/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteWorkshop(@PathVariable("id") final Long id, HttpServletRequest request) {

        logger.requestStart(request);

        Appointment appointment = appointmentRepository.findByAppointmentId(id);

        if (appointment == null) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Appointment with id "
                    + id + " was not found."), HttpStatus.NOT_FOUND);
        }

        appointmentRepository.deleteById(id);
        logger.requestStop();
        return new ResponseEntity<>(new ResponseDescription("Appointment with id "
                + id + " was deleted. " + total() + " Appointments total"), HttpStatus.OK);
    }

    /**
     * Delete all appointments
     *
     * @link localhost:8080/appointment/
     */
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<?> deleteAllAppointments(HttpServletRequest request) {

        logger.requestStart(request);

        appointmentRepository.deleteAll();
        logger.requestStop();
        return new ResponseEntity<>(new ResponseDescription("All Appointments was deleted.")
                , HttpStatus.OK);
    }

    /**
     * Filter an appointment by userId
     *
     * @link localhost:8080/appointment/filter/userId/userId
     */
    @GetMapping("/filter/userId/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Appointment> getUserIdFilterAppointment(@PathVariable("userId") final Long userId, HttpServletRequest request) {

        logger.requestStart(request);

        User user = userRepository.findByUserId(userId);
        List<Appointment> appointment = appointmentRepository.findByUserContains(user);

        logger.requestStop(appointment);
        return appointment;
    }

    /**
     * Filter an appointment by time
     *
     * @link localhost:8080/appointment/filter/time/time
     */
    @GetMapping("/filter/time/{time}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Appointment> getTimeFilterAppointment(@PathVariable("time") final String time, HttpServletRequest request) {

        logger.requestStart(request);

        List<Appointment> appointment = appointmentRepository.findByTimeContains(time);

        logger.requestStop(appointment);
        return appointment;
    }

}
