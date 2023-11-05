package bg.softuni.vikuslugivratsa.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "clients")
public class ClientEntity extends BaseEntity{

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne
    private WorkerEntity worker;

    @OneToMany(mappedBy = "client")
    private Set<WaterMeterEntity> waterMeters;

    @OneToMany(mappedBy = "client")
    private Set<WaterTapEntity> waterTaps;

    @OneToMany(mappedBy = "client")
    private Set<ServiceEntity> services;

    @OneToMany(mappedBy = "client")
    private Set<PlumbingWrenchEntity> plumbingWrenches;

    public ClientEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public WorkerEntity getWorker() {
        return worker;
    }

    public void setWorker(WorkerEntity worker) {
        this.worker = worker;
    }

    public Set<WaterMeterEntity> getWaterMeters() {
        return waterMeters;
    }

    public void setWaterMeters(Set<WaterMeterEntity> waterMeters) {
        this.waterMeters = waterMeters;
    }

    public Set<WaterTapEntity> getWaterTaps() {
        return waterTaps;
    }

    public void setWaterTaps(Set<WaterTapEntity> waterTaps) {
        this.waterTaps = waterTaps;
    }

    public Set<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(Set<ServiceEntity> services) {
        this.services = services;
    }
}
