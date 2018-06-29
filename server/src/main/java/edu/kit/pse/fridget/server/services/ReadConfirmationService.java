package edu.kit.pse.fridget.server.services;

import java.util.List;

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.models.ReadConfirmation;
import edu.kit.pse.fridget.server.models.User;

public interface ReadConfirmationService {
    List<Membership> getAllMemberships(String coolNoteId);

    ReadConfirmation saveReadConfirmation(ReadConfirmation readConfirmation);

    void deleteReadConfirmation(String coolNoteId, String membershipId);
}
