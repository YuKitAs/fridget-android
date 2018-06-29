package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import edu.kit.pse.fridget.server.models.CoolNote;
import edu.kit.pse.fridget.server.models.TaggedMember;
import edu.kit.pse.fridget.server.repositories.CoolNoteRepository;
import edu.kit.pse.fridget.server.repositories.TaggedMemberRepository;

@Service
public class CoolNoteServiceImpl implements CoolNoteService {
    private final CoolNoteRepository coolNoteRepository;
    private final TaggedMemberRepository taggedMemberRepository;

    @Autowired
    public CoolNoteServiceImpl(CoolNoteRepository coolNoteRepository, TaggedMemberRepository taggedMemberRepository) {
        this.coolNoteRepository = coolNoteRepository;
        this.taggedMemberRepository = taggedMemberRepository;
    }

    @Override
    public List<CoolNote> getAllCoolNotes(String flatshareId) {
        /*return coolNoteRepository.findByFlatshareId(flatshareId).stream().map(coolNote -> {
            List<TaggedMember> taggedUsers = taggedMemberRepository.findByCoolNoteId(coolNote.getId());
            return CoolNote.buildForFetching(coolNote, taggedUsers.stream().map(TaggedMember::getId).collect(Collectors.toList()));
        }).collect(Collectors.toList());*/
        return coolNoteRepository.findByFlatshareId(flatshareId);
    }

    @Override
    public CoolNote getCoolNote(String id) {
        return CoolNote.buildForFetching(coolNoteRepository.getOne(id),
                taggedMemberRepository.findByCoolNoteId(id).stream().map(TaggedMember::getId).collect(Collectors.toList()));
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
