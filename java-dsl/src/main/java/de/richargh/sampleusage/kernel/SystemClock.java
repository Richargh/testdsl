package de.richargh.sampleusage.kernel;

import java.time.Instant;

public class SystemClock implements Clock {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
