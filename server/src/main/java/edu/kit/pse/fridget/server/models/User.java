package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    private String googleUserId;
    private String googleName;

    public User() {
    }

    private User(String id, String googleUserId, String googleName) {
        this.id = id;
        this.googleUserId = googleUserId;
        this.googleName = googleName;
    }

    public String getId() {
        return id;
    }

    public String getGoogleUserId() {
        return googleUserId;
    }

    public String getGoogleName() {
        return googleName;
    }

    public static User buildNew(String googleUserId, String googleName) {
        return new User(UUID.randomUUID().toString(), googleUserId, googleName);
    }
}
