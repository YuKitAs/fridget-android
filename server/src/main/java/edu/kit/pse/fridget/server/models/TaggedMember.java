package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tagged_members")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TaggedMember {
    @Id
    private String id;
    private String membershipId;
    private String coolNoteId;

    public TaggedMember() {
    }

    private TaggedMember(String id, String membershipId, String coolNoteId) {
        this.id = id;
        this.membershipId = membershipId;
        this.coolNoteId = coolNoteId;
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

    public static TaggedMember buildNew(String membershipId, String coolNoteId) {
        return new TaggedMember(UUID.randomUUID().toString(), membershipId, coolNoteId);
    }
}
