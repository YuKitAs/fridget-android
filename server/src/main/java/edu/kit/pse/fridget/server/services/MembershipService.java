package edu.kit.pse.fridget.server.services;

import java.util.List;

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.models.representations.UserMembershipRepresentation;

public interface MembershipService {
    List<UserMembershipRepresentation> getAllUsers(String flatshareId);

    UserMembershipRepresentation getUser(String flatshareId, String userId);

    Membership saveMembership(String accessCode, String userId, Membership.Builder membershipBuilder);

    Membership saveMembership(Membership membership);

    void deleteMembership(String flatshareId, String userId);
}
