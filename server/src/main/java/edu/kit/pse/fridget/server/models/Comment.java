package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    private String userId;
    private String coolNoteId;
    private String content;

    public Comment() {
    }

    private Comment(String id, String userId, String coolNoteId, String content) {
        this.id = id;
        this.userId = userId;
        this.coolNoteId = coolNoteId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public static Comment buildNew(String userId, String coolNoteId, String content) {
        return new Comment(UUID.randomUUID().toString(), userId, coolNoteId, content);
    }
}
