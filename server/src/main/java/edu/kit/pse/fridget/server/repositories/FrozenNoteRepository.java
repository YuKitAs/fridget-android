package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import edu.kit.pse.fridget.server.models.FrozenNote;

public interface FrozenNoteRepository extends JpaRepository<FrozenNote, String> {
    List<FrozenNote> findByFlatshareId(String flatshareId);
}
