package by.it_academy.jd2.mk_jd2_88_22.messenger.model;

import java.time.LocalDateTime;

public class UserAudit {

    private Long id;
    private LocalDateTime dt_create;
    private String text;
    private User user;
    private User author;

    public UserAudit() {
    }

    public UserAudit(LocalDateTime dt_create, String text, User user, User author) {
        this.dt_create = dt_create;
        this.text = text;
        this.user = user;
        this.author = author;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "UserAudit{" +
                "id=" + id +
                ", dt_create=" + dt_create +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", author=" + author +
                '}';
    }

    public static class Builder {

        private LocalDateTime dt_create;
        private String text;
        private User user;
        private User author;

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

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setAuthor(User author) {
            this.author = author;
            return this;
        }

        public static Builder build() {
            return new UserAudit.Builder();
        }

        public UserAudit createUserAudit() {
            return new UserAudit(dt_create, text, user, author);
        }
    }
}
