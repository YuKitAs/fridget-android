package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import edu.kit.pse.fridget.server.models.TaggedUser;

public interface TaggedUserRepository extends JpaRepository<TaggedUser, String> {
    List<TaggedUser> findByCoolNoteId(String coolNoteId);
}
