package bg.softuni.vikuslugivratsa.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private RoleEntity role;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<WaterMeterEntity> waterMeters;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<WaterTapEntity> waterTaps;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ServiceEntity> services;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<PlumbingWrenchEntity> plumbingWrenches;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
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

    public Set<PlumbingWrenchEntity> getPlumbingWrenches() {
        return plumbingWrenches;
    }

    public void setPlumbingWrenches(Set<PlumbingWrenchEntity> plumbingWrenches) {
        this.plumbingWrenches = plumbingWrenches;
    }
}
