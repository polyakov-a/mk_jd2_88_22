package by.it_academy.jd2.mk_jd2_88_22.messenger.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_audit", schema = "app")
public class UserAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "app.users_audit_id_seq")
    private Long id;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    private String text;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "author_login")
    private String authorLogin;

    public UserAuditEntity() {
    }

    public UserAuditEntity(Long id, LocalDateTime dtCreate, String text, String userLogin, String authorLogin) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.userLogin = userLogin;
        this.authorLogin = authorLogin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public String getText() {
        return text;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    @Override
    public String toString() {
        return "UserAuditEntity{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", text='" + text + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", authorLogin='" + authorLogin + '\'' +
                '}';
    }

    public static class Builder {

        private Long id;
        private LocalDateTime dt_create;
        private String text;
        private String userLogin;
        private String authorLogin;

        private Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDt_create(LocalDateTime dt_create) {
            this.dt_create = dt_create;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setUserLogin(String userLogin) {
            this.userLogin = userLogin;
            return this;
        }

        public Builder setAuthorLogin(String authorLogin) {
            this.authorLogin = authorLogin;
            return this;
        }

        public static Builder build() {
            return new Builder();
        }

        public UserAuditEntity createUserAuditEntity() {
            return new UserAuditEntity(id, dt_create, text, userLogin, authorLogin);
        }
    }
}
