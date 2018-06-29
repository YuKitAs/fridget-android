package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.models.ReadConfirmation;
import edu.kit.pse.fridget.server.repositories.MembershipRepository;
import edu.kit.pse.fridget.server.repositories.ReadConfirmationRepository;

@Service
public class ReadConfirmationServiceImpl implements ReadConfirmationService {
    private final ReadConfirmationRepository readConfirmationRepository;
    private final MembershipRepository membershipRepository;

    @Autowired
    public ReadConfirmationServiceImpl(ReadConfirmationRepository readConfirmationRepository, MembershipRepository membershipRepository) {
        this.readConfirmationRepository = readConfirmationRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Membership> getAllMemberships(String coolNoteId) {
        return readConfirmationRepository.findByCoolNoteId(coolNoteId)
                .stream()
                .map(readConfirmation -> membershipRepository.getOne(readConfirmation.getMembershipId()))
                .collect(Collectors.toList());
    }

    @Override
    public ReadConfirmation saveReadConfirmation(ReadConfirmation readConfirmation) {
        return readConfirmationRepository.save(
                ReadConfirmation.buildNew(readConfirmation.getMembershipId(), readConfirmation.getCoolNoteId()));
    }

    @Override
    public void deleteReadConfirmation(String coolNoteId, String membershipId) {
        readConfirmationRepository.deleteByCoolNoteIdAndMembershipId(coolNoteId, membershipId);
    }
}
