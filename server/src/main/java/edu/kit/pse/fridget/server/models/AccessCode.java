package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "access_codes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccessCode {
    @Id
    private String id;
    private String content;
    private String flatshareId;

    public AccessCode() {
    }

    private AccessCode(String id, String content, String flatshareId) {
        this.id = id;
        this.content = content;
        this.flatshareId = flatshareId;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getFlatshareId() {
        return flatshareId;
    }

    public static AccessCode buildNew(String content, String flatshareId) {
        return new AccessCode(UUID.randomUUID().toString(), content, flatshareId);
    }
}
