package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "frozen_notes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FrozenNote {
    @Id
    private String id;
    private String flatshareId;
    private String title;
    private String content;
    private int position;

    public FrozenNote() {
    }

    public FrozenNote(String id, String flatshareId, String title, String content, int position) {
        this.id = id;
        this.flatshareId = flatshareId;
        this.title = title;
        this.content = content;
        this.position = position;
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

    public int getPosition() {
        return position;
    }
}
