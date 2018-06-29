package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {
    @Id
    private String id;
    private String membershipId;
    private String coolNoteId;
    private String content;

    public Comment() {
    }

    private Comment(String id, String membershipId, String coolNoteId, String content) {
        this.id = id;
        this.membershipId = membershipId;
        this.coolNoteId = coolNoteId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getCoolNoteId() {
        return coolNoteId;
    }

    public String getContent() {
        return content;
    }

    public static Comment buildNew(String membershipId, String coolNoteId, String content) {
        return new Comment(UUID.randomUUID().toString(), membershipId, coolNoteId, content);
    }
}
