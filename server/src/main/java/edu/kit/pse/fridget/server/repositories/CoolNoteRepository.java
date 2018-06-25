package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import edu.kit.pse.fridget.server.models.CoolNote;

public interface CoolNoteRepository extends JpaRepository<CoolNote, String> {
    List<CoolNote> findByFlatshareId(String flatshareId);
}
