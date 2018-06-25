package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String dummyAccessCode = "ab35e";
        return repository.save(AccessCode.buildNew(dummyAccessCode, flatshareId));
    }
}
