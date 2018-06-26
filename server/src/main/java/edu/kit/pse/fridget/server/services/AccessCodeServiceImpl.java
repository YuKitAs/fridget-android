package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;

import edu.kit.pse.fridget.server.models.AccessCode;
import edu.kit.pse.fridget.server.repositories.AccessCodeRepository;

@Service
public class AccessCodeServiceImpl implements AccessCodeService {
    private final AccessCodeRepository repository;

    @Autowired
    public AccessCodeServiceImpl(AccessCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccessCode generateAccessCode(String flatshareId) {
        return repository.save(AccessCode.buildNew(generateRandomContent(), flatshareId));
    }

    private String generateRandomContent() {
        String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String LOWER = UPPER.toLowerCase();
        String DIGITS = "0123456789";
        String ALPHANUM = UPPER + LOWER + DIGITS;

        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 5).forEach(i -> sb.append(ALPHANUM.charAt(new Random().nextInt(ALPHANUM.length()))));

        return sb.toString();
    }
}
