package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import edu.kit.pse.fridget.server.models.CoolNote;

public interface CoolNoteRepository extends JpaRepository<CoolNote, String> {
    @Query(value = "SELECT cool_notes.* FROM cool_notes " //
            + "JOIN memberships ON memberships.id = cool_notes.creator_membership_id " //
            + "JOIN flatshares ON memberships.flatshare_id = flatshares.id " //
            + "WHERE flatshares.id = :flatshareId", nativeQuery = true)
    List<CoolNote> findByFlatshareId(@Param("flatshareId") String flatshareId);
}
