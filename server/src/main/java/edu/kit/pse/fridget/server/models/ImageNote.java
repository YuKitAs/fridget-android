package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.kit.pse.fridget.server.services.ClockProvider;

@Entity
@Table(name = "image_notes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageNote {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    private String flatshareId;
    private byte[] image;
    private String description;
    private String creatorUserId;
    private int position;
    private Instant createdAt;

    public ImageNote() {
    }

    private ImageNote(String id, String flatshareId, byte[] image, String description, String creatorUserId, int position,
            Instant createdAt) {
        this.id = id;
        this.flatshareId = flatshareId;
        this.image = image;
        this.description = description;
        this.creatorUserId = creatorUserId;
        this.position = position;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getFlatshareId() {
        return flatshareId;
    }

    public byte[] getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public int getPosition() {
        return position;
    }

    public String getCreateAt() {
        return createdAt.toString();
    }

    public static ImageNote buildNew(String flatshareId, byte[] image, String description, String creatorUserId, int position) {
        return new ImageNote(UUID.randomUUID().toString(), flatshareId, image, description, creatorUserId, position,
                ClockProvider.getCurrentTime());
    }
}
