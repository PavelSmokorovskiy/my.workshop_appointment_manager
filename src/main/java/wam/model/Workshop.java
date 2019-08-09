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

    @Column(name = "workshop_company_name")
    @NotEmpty(message = "Please provide Company name")
    private String workshopCompanyName;

    @Column(name = "workshop_trademarks")
    private String workshopTrademarks;

    @Column(name = "workshop_city")
    private String workshopCity;

    @Column(name = "workshop_postal_code")
    private String workshopPostalCode;

    @Column(name = "workshop_country")
    private String workshopCountry;

    @OneToMany(mappedBy = "user")
    private Set<Appointment> appointments = new HashSet<>();

    public Workshop() {
    }

    public Workshop(@NotEmpty(message = "Please provide Company name")
                            String workshopCompanyName,
                    String workshopTrademarks,
                    String workshopCity,
                    String workshopPostalCode,
                    String workshopCountry,
                    Set<Appointment> appointments) {
        this.workshopCompanyName = workshopCompanyName;
        this.workshopTrademarks = workshopTrademarks;
        this.workshopCity = workshopCity;
        this.workshopPostalCode = workshopPostalCode;
        this.workshopCountry = workshopCountry;
        this.appointments = appointments;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(Long workshopId) {
        this.workshopId = workshopId;
    }

    public String getWorkshopCompanyName() {
        return workshopCompanyName;
    }

    public void setWorkshopCompanyName(String workshopCompanyName) {
        this.workshopCompanyName = workshopCompanyName;
    }

    public String getWorkshopTrademarks() {
        return workshopTrademarks;
    }

    public void setWorkshopTrademarks(String workshopTrademarks) {
        this.workshopTrademarks = workshopTrademarks;
    }

    public String getWorkshopCity() {
        return workshopCity;
    }

    public void setWorkshopCity(String workshopCity) {
        this.workshopCity = workshopCity;
    }

    public String getWorkshopPostalCode() {
        return workshopPostalCode;
    }

    public void setWorkshopPostalCode(String workshopPostalCode) {
        this.workshopPostalCode = workshopPostalCode;
    }

    public String getWorkshopCountry() {
        return workshopCountry;
    }

    public void setWorkshopCountry(String workshopCountry) {
        this.workshopCountry = workshopCountry;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
