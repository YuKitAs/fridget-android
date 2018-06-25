package edu.kit.pse.fridget.server.services;

import java.util.List;

import edu.kit.pse.fridget.server.models.TaggedUser;

public interface TaggedUserService {
    List<TaggedUser> getAllTaggedUsers(String coolNoteId);

    TaggedUser saveTaggedUser(String userId, String coolNoteId);
}
