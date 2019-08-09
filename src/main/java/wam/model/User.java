package wam.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "user_username")
    @NotEmpty(message = "Please provide username")
    private String userUsername;

    @Column(name = "user_email")
    @NotEmpty(message = "Please provide Email")
    private String userEmail;

    @Column(name = "user_city")
    private String userCity;

    @Column(name = "user_postal_code")
    private String userPostalCode;

    @Column(name = "user_country")
    private String userCountry;

    @OneToMany(mappedBy = "user")
    private Set<Appointment> appointments = new HashSet<>();

    public User() {
    }

    public User(@NotEmpty(message = "Please provide username")
                        String userUsername, @NotEmpty(message = "Please provide Email")
                        String userEmail,
                String userCity,
                String userPostalCode,
                String userCountry,
                Set<Appointment> appointments) {
        this.userUsername = userUsername;
        this.userEmail = userEmail;
        this.userCity = userCity;
        this.userPostalCode = userPostalCode;
        this.userCountry = userCountry;
        this.appointments = appointments;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserPostalCode() {
        return userPostalCode;
    }

    public void setUserPostalCode(String userPostalCode) {
        this.userPostalCode = userPostalCode;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
