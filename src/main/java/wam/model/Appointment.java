package wam.model;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    @Column(name = "appointment_time")
    private LocalDateTime appointmentTime;

    public Appointment() {
    }

    public Appointment(User user, Workshop workshop, LocalDateTime appointmentTime) {
        this.user = user;
        this.workshop = workshop;
        this.appointmentTime = appointmentTime;
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

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
