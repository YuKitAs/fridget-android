package edu.kit.pse.fridget.server.services;

import java.util.List;

import edu.kit.pse.fridget.server.models.CoolNote;

public interface CoolNoteService {
    List<CoolNote> getAllCoolNotes(String flatshareId);

    CoolNote getCoolNote(String id);

    CoolNote saveCoolNote(CoolNote coolNote);

    void deleteCoolNote(String id);
}
