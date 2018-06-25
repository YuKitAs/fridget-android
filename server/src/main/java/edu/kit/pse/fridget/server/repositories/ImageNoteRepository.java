package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import edu.kit.pse.fridget.server.models.ImageNote;

public interface ImageNoteRepository extends JpaRepository<ImageNote, String> {
    List<ImageNote> findByFlatshareId(String flatshareId);
}
