package wam.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Appointment entity
 */
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "workshop_id", nullable = false)
//    private Workshop workshop;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Appointment() {
    }

    public Appointment(User user
//            , Workshop workshop
            , LocalDateTime dateTime) {
        this.user = user;
//        this.workshop = workshop;
        this.dateTime = dateTime;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Workshop getWorkshop() {
//        return workshop;
//    }
//
//    public void setWorkshop(Workshop workshop) {
//        this.workshop = workshop;
//    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
