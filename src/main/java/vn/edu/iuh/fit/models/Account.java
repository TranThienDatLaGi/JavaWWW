package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
//@NamedQueries(
//        @NamedQuery(name = "Account.findAll",query = "select a from Account a where a.status=1")
//)
public class Account {
    @Id
    @Column(name = "account_id",columnDefinition = "varchar(50)")
    private String account_id;
    @Column(name = "full_name",columnDefinition = "varchar(50)")
    private String full_name;
    @Column(name = "password",columnDefinition = "varchar(50)")
    private String password;
    @Column(name = "email",columnDefinition = "varchar(50)")
    private String email;
    @Column(name = "phone",columnDefinition = "varchar(50)")
    private String phone;

    @Column(columnDefinition = "TINYINT(4) SIGNED", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @Convert(converter = StatusConverter.class)
    private Status status;

    public Account() {
    }

    public Account(String account_id, String full_name, String password, String email, String phone, Status status) {
        this.account_id = account_id;
        this.full_name = full_name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id='" + account_id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
