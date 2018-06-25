package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "read_confirmations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReadConfirmation {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    private String userId;
    private String coolNoteId;

    public ReadConfirmation() {
    }

    private ReadConfirmation(String id, String userId, String coolNoteId) {
        this.id = id;
        this.userId = userId;
        this.coolNoteId = coolNoteId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCoolNoteId() {
        return coolNoteId;
    }

    public static ReadConfirmation buildNew(String userId, String coolNoteId) {
        return new ReadConfirmation(UUID.randomUUID().toString(), userId, coolNoteId);
    }
}
