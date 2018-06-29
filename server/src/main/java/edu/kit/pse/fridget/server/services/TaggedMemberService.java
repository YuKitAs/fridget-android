package edu.kit.pse.fridget.server.services;

import java.util.List;

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.models.TaggedMember;

public interface TaggedMemberService {
    List<Membership> getAllTaggedMembers(String coolNoteId);

    TaggedMember saveTaggedMember(String membershipId, String coolNoteId);
}
