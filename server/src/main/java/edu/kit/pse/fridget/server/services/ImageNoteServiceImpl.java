package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.kit.pse.fridget.server.models.ImageNote;
import edu.kit.pse.fridget.server.repositories.ImageNoteRepository;

@Service
public class ImageNoteServiceImpl implements ImageNoteService {
    private final ImageNoteRepository repository;

    @Autowired
    public ImageNoteServiceImpl(ImageNoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ImageNote> getAllImageNotes(String flatshareId) {
        return repository.findByFlatshareId(flatshareId);
    }

    @Override
    public ImageNote getImageNote(String id) {
        return repository.getOne(id);
    }

    @Override
    public ImageNote saveImageNote(ImageNote imageNote) {
        return repository.save(ImageNote.buildNew(imageNote.getFlatshareId(), imageNote.getImage(), imageNote.getDescription(),
                imageNote.getCreatorMembershipId(), imageNote.getPosition()));
    }

    @Override
    public void deleteImageNote(String id) {
        repository.deleteById(id);
    }
}
