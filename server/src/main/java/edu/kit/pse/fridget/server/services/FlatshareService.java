package edu.kit.pse.fridget.server.services;

import edu.kit.pse.fridget.server.models.Flatshare;

public interface FlatshareService {
    Flatshare getFlatshare(String id);

    Flatshare saveFlatshare(Flatshare flatshare, String userId);
}
