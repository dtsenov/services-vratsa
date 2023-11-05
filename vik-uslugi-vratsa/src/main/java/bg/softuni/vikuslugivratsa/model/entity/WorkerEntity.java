package bg.softuni.vikuslugivratsa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "workers")
public class WorkerEntity extends BaseEntity {

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

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "worker")
    private Set<ClientEntity> clients;

    @OneToMany(mappedBy = "worker")
    private Set<WaterMeterEntity> waterMeters;

    @OneToMany(mappedBy = "worker")
    private Set<WaterTapEntity> waterTaps;

    @OneToMany(mappedBy = "worker")
    private Set<ServiceEntity> services;

    public WorkerEntity() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ClientEntity> getClients() {
        return clients;
    }

    public void setClients(Set<ClientEntity> clients) {
        this.clients = clients;
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
