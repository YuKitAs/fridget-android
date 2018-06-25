package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "devices")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Device {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    private String userId;
    private String instanceIdToken;

    public Device() {
    }

    private Device(String id, String userId, String instanceIdToken) {
        this.id = id;
        this.userId = userId;
        this.instanceIdToken = instanceIdToken;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getInstanceIdToken() {
        return instanceIdToken;
    }

    public static Device buildNew(String userId, String instanceIdToken) {
        return new Device(UUID.randomUUID().toString(), userId, instanceIdToken);
    }
}
