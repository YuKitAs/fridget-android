package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import edu.kit.pse.fridget.server.models.ReadConfirmation;

public interface ReadConfirmationRepository extends JpaRepository<ReadConfirmation, String> {
    List<ReadConfirmation> findByCoolNoteId(String id);

    void deleteByCoolNoteIdAndMembershipId(String coolNoteId, String membershipId);
}
