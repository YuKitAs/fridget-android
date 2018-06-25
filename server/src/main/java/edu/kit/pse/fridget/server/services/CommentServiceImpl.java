package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.kit.pse.fridget.server.models.Comment;
import edu.kit.pse.fridget.server.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> getAllComments(String coolNoteId) {
        return repository.findByCoolNoteId(coolNoteId);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return repository.save(Comment.buildNew(comment.getUserId(), comment.getCoolNoteId(), comment.getContent()));
    }

    @Override
    public void deleteComment(String id) {
        repository.deleteById(id);
    }
}
