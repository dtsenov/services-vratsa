package bg.softuni.vikuslugivratsa.model.entity;

import bg.softuni.vikuslugivratsa.model.enums.RoleNameEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = true)
    private RoleNameEnum role;

    @Column(name = "description", nullable = false)
    private String description;


    public RoleEntity() {
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public void setRole(RoleNameEnum role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
