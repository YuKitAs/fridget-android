package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.kit.pse.fridget.server.models.FrozenNote;
import edu.kit.pse.fridget.server.repositories.FrozenNoteRepository;

@Service
public class FrozenNoteServiceImpl implements FrozenNoteService {
    private final FrozenNoteRepository repository;

    @Autowired
    public FrozenNoteServiceImpl(FrozenNoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FrozenNote> getAllFrozenNotes(String flatshareId) {
        return repository.findByFlatshareId(flatshareId);
    }

    @Override
    public FrozenNote getFrozenNote(String id) {
        return repository.getOne(id);
    }

    @Override
    public FrozenNote updateFrozenNote(String id, FrozenNote frozenNote) {
        return repository.save(
                new FrozenNote(id, frozenNote.getFlatshareId(), frozenNote.getTitle(), frozenNote.getContent(), frozenNote.getPosition()));
    }

    @Override
    public FrozenNote saveFrozenNote(FrozenNote frozenNote) {
        return repository.save(frozenNote);
    }
}