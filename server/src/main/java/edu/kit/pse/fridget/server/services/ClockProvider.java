package edu.kit.pse.fridget.server.services;

import java.time.Instant;

public class ClockProvider {
    public static Instant getCurrentTime() {
        return Instant.now();
    }
}
