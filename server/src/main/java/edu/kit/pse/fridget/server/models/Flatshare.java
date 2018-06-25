package edu.kit.pse.fridget.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "flatshares")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flatshare {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    @NotBlank
    private String name;

    public Flatshare() {
    }

    private Flatshare(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Flatshare buildNew(String name) {
        return new Flatshare(UUID.randomUUID().toString(), name);
    }
}
