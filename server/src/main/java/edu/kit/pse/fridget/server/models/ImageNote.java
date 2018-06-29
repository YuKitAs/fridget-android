package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.kit.pse.fridget.server.services.ClockProvider;

@Entity
@Table(name = "image_notes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageNote {
    @Id
    private String id;
    private String flatshareId;
    private byte[] image;
    private String description;
    private String creatorMembershipId;
    private int position;
    private Instant createdAt;

    public ImageNote() {
    }

    private ImageNote(String id, String flatshareId, byte[] image, String description, String creatorMembershipId, int position,
            Instant createdAt) {
        this.id = id;
        this.flatshareId = flatshareId;
        this.image = image;
        this.description = description;
        this.creatorMembershipId = creatorMembershipId;
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

    public String getCreatorMembershipId() {
        return creatorMembershipId;
    }

    public int getPosition() {
        return position;
    }

    public String getCreateAt() {
        return createdAt.toString();
    }

    public static ImageNote buildNew(String flatshareId, byte[] image, String description, String creatorMembershipId, int position) {
        return new ImageNote(UUID.randomUUID().toString(), flatshareId, image, description, creatorMembershipId, position,
                ClockProvider.getCurrentTime());
    }
}
