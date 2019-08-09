package wam.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * Workshop entity
 */
@Entity
@Table(name = "workshops")
public class Workshop {

    @Id
    @Column(name = "workshop_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workshopId;

    @Column(name = "company_name")
    @NotEmpty(message = "Please provide Company name")
    private String companyName;

    @Column(name = "trademarks")
    private String trademarks;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "user")
    private Set<Appointment> appointments = new HashSet<>();

    public Workshop() {
    }

    public Workshop(@NotEmpty(message = "Please provide Company name")
                            String companyName,
                    String trademarks,
                    String city,
                    String postalCode,
                    String country) {
        this.companyName = companyName;
        this.trademarks = trademarks;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(Long workshopId) {
        this.workshopId = workshopId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTrademarks() {
        return trademarks;
    }

    public void setTrademarks(String trademarks) {
        this.trademarks = trademarks;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
