package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "memberships")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Membership {
    @Id
    private String id;
    private String userId;
    private String flatshareId;
    private String magnetColor;

    public Membership() {
    }

    private Membership(String id, String userId, String flatshareId, String magnetColor) {
        this.id = id;
        this.userId = userId;
        this.flatshareId = flatshareId;
        this.magnetColor = magnetColor;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFlatshareId() {
        return flatshareId;
    }

    public String getMagnetColor() {
        return magnetColor;
    }

    public static class Builder {
        private String id = null;
        private String userId = null;
        private String flatshareId = null;
        private String magnetColor = null;

        public Builder setRandomId() {
            this.id = UUID.randomUUID().toString();
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setFlatshareId(String flatshareId) {
            this.flatshareId = flatshareId;
            return this;
        }

        public Builder setMagnetColor(String color) {
            this.magnetColor = color;
            return this;
        }

        public Membership build() {
            return new Membership(Objects.requireNonNull(id), Objects.requireNonNull(userId), Objects.requireNonNull(flatshareId),
                    Objects.requireNonNull(magnetColor));
        }
    }
}
