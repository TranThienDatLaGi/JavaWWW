package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "grant_access")
public class GrantAccess {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(columnDefinition = "ENUM('1','0','-1')")
    private boolean is_grant;
    @Column(columnDefinition = "varchar(250)")
    private String note;

    public GrantAccess(Role role, Account account, boolean is_grant, String note) {
        this.role = role;
        this.account = account;
        this.is_grant = is_grant;
        this.note = note;
    }

    public GrantAccess() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isIs_grant() {
        return is_grant;
    }

    public void setIs_grant(boolean is_grant) {
        this.is_grant = is_grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "role=" + role +
                ", account=" + account +
                ", is_grant=" + is_grant +
                ", note='" + note + '\'' +
                '}';
    }
}
