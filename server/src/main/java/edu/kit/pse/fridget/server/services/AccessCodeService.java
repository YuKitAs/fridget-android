package edu.kit.pse.fridget.server.services;

import edu.kit.pse.fridget.server.models.AccessCode;

public interface AccessCodeService {
    AccessCode generateAccessCode(String flatshareId);
}
