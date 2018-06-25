package edu.kit.pse.fridget.server.services;

import java.util.List;

import edu.kit.pse.fridget.server.models.FrozenNote;

public interface FrozenNoteService {
    List<FrozenNote> getAllFrozenNotes(String flatshareId);

    FrozenNote getFrozenNote(String id);

    FrozenNote updateFrozenNote(String id, FrozenNote frozenNote);

    FrozenNote saveFrozenNote(FrozenNote frozenNote);
}
