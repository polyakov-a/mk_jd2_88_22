package by.it_academy.jd2.mk_jd2_88_22.messenger.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_audit", schema = "app")
public class UserAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    private String text;

    @ManyToOne
    @JoinColumn(name = "\"user\"")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;

    public UserAuditEntity() {
    }

    public UserAuditEntity(LocalDateTime dtCreate, String text, UserEntity user, UserEntity author) {
        this.dtCreate = dtCreate;
        this.text = text;
        this.user = user;
        this.author = author;
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

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
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

    public UserEntity getUser() {
        return user;
    }

    public UserEntity getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "UserAuditEntity{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", author=" + author +
                '}';
    }

    public static class Builder {

        private LocalDateTime dt_create;
        private String text;
        private UserEntity user;
        private UserEntity author;

        private Builder() {
        }

        public Builder setDt_create(LocalDateTime dt_create) {
            this.dt_create = dt_create;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public Builder setAuthor(UserEntity author) {
            this.author = author;
            return this;
        }

        public static Builder build() {
            return new Builder();
        }

        public UserAuditEntity createUserAuditEntity() {
            return new UserAuditEntity(dt_create, text, user, author);
        }
    }
}
