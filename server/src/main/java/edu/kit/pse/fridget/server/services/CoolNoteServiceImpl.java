package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import edu.kit.pse.fridget.server.models.CoolNote;
import edu.kit.pse.fridget.server.models.TaggedUser;
import edu.kit.pse.fridget.server.repositories.CoolNoteRepository;
import edu.kit.pse.fridget.server.repositories.TaggedUserRepository;

@Service
public class CoolNoteServiceImpl implements CoolNoteService {
    private final CoolNoteRepository coolNoteRepository;
    private final TaggedUserRepository taggedUserRepository;

    @Autowired
    public CoolNoteServiceImpl(CoolNoteRepository coolNoteRepository, TaggedUserRepository taggedUserRepository) {
        this.coolNoteRepository = coolNoteRepository;
        this.taggedUserRepository = taggedUserRepository;
    }

    @Override
    public List<CoolNote> getAllCoolNotes(String flatshareId) {
        return coolNoteRepository.findByFlatshareId(flatshareId).stream().map(coolNote -> {
            List<TaggedUser> taggedUsers = taggedUserRepository.findByCoolNoteId(coolNote.getId());
            return CoolNote.buildForFetching(coolNote, taggedUsers.stream().map(TaggedUser::getId).collect(Collectors.toList()));
        }).collect(Collectors.toList());
    }

    @Override
    public CoolNote getCoolNote(String id) {
        return CoolNote.buildForFetching(coolNoteRepository.getOne(id),
                taggedUserRepository.findByCoolNoteId(id).stream().map(TaggedUser::getId).collect(Collectors.toList()));
    }

    @Override
    public CoolNote saveCoolNote(CoolNote coolNote) {
        return coolNoteRepository.save(coolNote);
    }

    @Override
    public void deleteCoolNote(String id) {
        coolNoteRepository.deleteById(id);
    }
}
