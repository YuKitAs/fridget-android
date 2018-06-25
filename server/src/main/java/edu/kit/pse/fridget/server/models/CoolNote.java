package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
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
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;
    private String title;
    private String content;
    private String flatshareId;
    private String creatorUserId;
    private int position;
    private int importance;
    private Instant createdAt;
    @JsonInclude
    @Transient
    private List<String> taggedUserIds;

    public CoolNote() {
    }

    private CoolNote(String id, String title, String content, String flatshareId, String creatorUserId, int position, int importance,
            Instant createdAt, List<String> taggedUserIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.flatshareId = flatshareId;
        this.creatorUserId = creatorUserId;
        this.position = position;
        this.importance = importance;
        this.createdAt = createdAt;
        this.taggedUserIds = taggedUserIds;
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

    public String getFlatshareId() {
        return flatshareId;
    }

    public String getCreatorUserId() {
        return creatorUserId;
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

    public List<String> getTaggedUserIds() {
        return taggedUserIds;
    }

    public static CoolNote buildForCreation(CoolNote coolNote) {
        return new CoolNote(UUID.randomUUID().toString(), coolNote.getTitle(), coolNote.getContent(), coolNote.getFlatshareId(),
                coolNote.getCreatorUserId(), coolNote.getPosition(), coolNote.getImportance(), ClockProvider.getCurrentTime(),
                coolNote.getTaggedUserIds());
    }

    public static CoolNote buildForFetching(CoolNote coolNote, List<String> taggedUserIds) {
        return new CoolNote(coolNote.getId(), coolNote.getTitle(), coolNote.getContent(), coolNote.getFlatshareId(),
                coolNote.getCreatorUserId(), coolNote.getPosition(), coolNote.getImportance(), coolNote.getCreatedAt(), taggedUserIds);
    }
}
