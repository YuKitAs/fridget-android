package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import edu.kit.pse.fridget.server.models.ReadConfirmation;
import edu.kit.pse.fridget.server.models.User;
import edu.kit.pse.fridget.server.repositories.ReadConfirmationRepository;
import edu.kit.pse.fridget.server.repositories.UserRepository;

@Service
public class ReadConfirmationServiceImpl implements ReadConfirmationService {
    private final ReadConfirmationRepository readConfirmationRepository;

    private final UserRepository userRepository;

    @Autowired
    public ReadConfirmationServiceImpl(ReadConfirmationRepository readConfirmationRepository, UserRepository userRepository) {
        this.readConfirmationRepository = readConfirmationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(String coolNoteId) {
        return readConfirmationRepository.findByCoolNoteId(coolNoteId)
                .stream()
                .map(readConfirmation -> userRepository.getOne(readConfirmation.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public ReadConfirmation saveReadConfirmation(ReadConfirmation readConfirmation) {
        return readConfirmationRepository.save(ReadConfirmation.buildNew(readConfirmation.getUserId(), readConfirmation.getCoolNoteId()));
    }

    @Override
    public void deleteReadConfirmation(String coolNoteId, String userId) {
        readConfirmationRepository.deleteByCoolNoteIdAndUserId(coolNoteId, userId);
    }
}
