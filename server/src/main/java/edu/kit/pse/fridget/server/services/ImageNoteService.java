package edu.kit.pse.fridget.server.services;

import java.util.List;

import edu.kit.pse.fridget.server.models.ImageNote;

public interface ImageNoteService {
    List<ImageNote> getAllImageNotes(String flatshareId);

    ImageNote getImageNote(String id);

    ImageNote saveImageNote(ImageNote imageNote);

    void deleteImageNote(String id);
}
