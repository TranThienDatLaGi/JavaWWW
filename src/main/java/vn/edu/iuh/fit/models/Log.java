package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20)")
    private String id;
    @Column(columnDefinition = "varchar(50)")
    private String account_id;
    @Column(columnDefinition = "datetime")
    private Timestamp login_time;
    @Column(columnDefinition = "datetime")
    private Timestamp logout_time;
    @Column(columnDefinition = "varchar(250)")
    private String notes;

    public Log() {
    }

      public Log(String account_id, Timestamp login_time, Timestamp logout_time, String note) {
        this.account_id = account_id;
        this.login_time = login_time;
        this.logout_time = logout_time;
        this.notes = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Timestamp getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Timestamp login_time) {
        this.login_time = login_time;
    }

    public Timestamp getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(Timestamp logout_time) {
        this.logout_time = logout_time;
    }

    public String getNote() {
        return notes;
    }

    public void setNote(String note) {
        this.notes = note;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", login_time=" + login_time +
                ", logout_time=" + logout_time +
                ", note='" + notes + '\'' +
                '}';
    }
}
