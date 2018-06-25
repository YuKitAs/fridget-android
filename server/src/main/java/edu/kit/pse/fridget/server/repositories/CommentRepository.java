package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import edu.kit.pse.fridget.server.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByCoolNoteId(String coolNoteId);
}
