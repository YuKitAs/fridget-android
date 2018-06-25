package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.kit.pse.fridget.server.models.TaggedUser;
import edu.kit.pse.fridget.server.repositories.TaggedUserRepository;

@Service
public class TaggedUserServiceImpl implements TaggedUserService {
    private final TaggedUserRepository repository;

    @Autowired
    public TaggedUserServiceImpl(TaggedUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TaggedUser> getAllTaggedUsers(String coolNoteId) {
        return repository.findByCoolNoteId(coolNoteId);
    }

    @Override
    public TaggedUser saveTaggedUser(String userId, String coolNoteId) {
        return repository.save(TaggedUser.buildNew(userId, coolNoteId));
    }
}
