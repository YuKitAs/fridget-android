package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.kit.pse.fridget.server.services.ClockProvider;

@Entity
@Table(name = "cool_notes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CoolNote {
    @Id
    private String id;
    private String title;
    private String content;
    private String creatorMembershipId;
    private int position;
    private int importance;
    private Instant createdAt;
    @JsonInclude
    @Transient
    private List<String> taggedMembershipIds;

    public CoolNote() {
    }

    private CoolNote(String id, String title, String content, String creatorMembershipId, int position, int importance, Instant createdAt,
            List<String> taggedMembershipIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creatorMembershipId = creatorMembershipId;
        this.position = position;
        this.importance = importance;
        this.createdAt = createdAt;
        this.taggedMembershipIds = taggedMembershipIds;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatorMembershipId() {
        return creatorMembershipId;
    }

    public int getPosition() {
        return position;
    }

    public int getImportance() {
        return importance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<String> getTaggedMembershipIds() {
        return taggedMembershipIds;
    }

    public static CoolNote buildForCreation(CoolNote coolNote) {
        return new CoolNote(UUID.randomUUID().toString(), coolNote.getTitle(), coolNote.getContent(), coolNote.getCreatorMembershipId(),
                coolNote.getPosition(), coolNote.getImportance(), ClockProvider.getCurrentTime(), coolNote.getTaggedMembershipIds());
    }

    public static CoolNote buildForFetching(CoolNote coolNote, List<String> taggedMembershipIds) {
        return new CoolNote(coolNote.getId(), coolNote.getTitle(), coolNote.getContent(), coolNote.getCreatorMembershipId(),
                coolNote.getPosition(), coolNote.getImportance(), coolNote.getCreatedAt(), taggedMembershipIds);
    }
}
