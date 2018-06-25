package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tagged_users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TaggedUser {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    private String userId;
    private String coolNoteId;

    public TaggedUser() {
    }

    private TaggedUser(String id, String userId, String coolNoteId) {
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

    public static TaggedUser buildNew(String userId, String coolNoteId) {
        return new TaggedUser(UUID.randomUUID().toString(), userId, coolNoteId);
    }
}
