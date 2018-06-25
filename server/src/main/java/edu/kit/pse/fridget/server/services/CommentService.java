package edu.kit.pse.fridget.server.services;

import java.util.List;

import edu.kit.pse.fridget.server.models.Comment;

public interface CommentService {
    List<Comment> getAllComments(String coolNoteId);

    Comment saveComment(Comment comment);

    void deleteComment(String id);
}
