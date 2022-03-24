package by.it_academy.jd2.mk_jd2_88_22.messenger.model;

import java.time.LocalDateTime;

public class UserAudit {

    private Long id;
    private LocalDateTime dt_create;
    private String text;
    private String userLogin;
    private String authorLogin;

    public UserAudit() {
    }

    public UserAudit(Long id, LocalDateTime dt_create, String text, String userLogin, String authorLogin) {
        this.id = id;
        this.dt_create = dt_create;
        this.text = text;
        this.userLogin = userLogin;
        this.authorLogin = authorLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
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

    public LocalDateTime getDt_create() {
        return dt_create;
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
        return "UserAudit{" +
                "id=" + id +
                ", dt_create=" + dt_create +
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
            return new UserAudit.Builder();
        }

        public UserAudit createUserAudit() {
            return new UserAudit(id, dt_create, text, userLogin, authorLogin);
        }
    }
}
