package de.richargh.sampleusage.kernel;

import java.time.Instant;

public interface Clock {
    Instant now();
}
