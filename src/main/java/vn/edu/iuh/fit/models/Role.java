package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    private String role_id;
    @Column(columnDefinition = "VARCHAR(50)")
    private String role_name;
    @Column(columnDefinition = "VARCHAR(50)")
    private String description;
    @Column(columnDefinition = "TINYINT(4) SIGNED", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @Convert(converter = StatusConverter.class)
    private Status status;

    public Role() {
    }

    public Role(String role_id, String role_name, String description, Status status) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.description = description;
        this.status = status;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", role_name='" + role_name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
