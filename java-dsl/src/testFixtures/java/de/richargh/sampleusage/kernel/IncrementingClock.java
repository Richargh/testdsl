package de.richargh.sampleusage.kernel;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class IncrementingClock implements Clock {

    private final AtomicLong now;

    public IncrementingClock(Instant now) {
        this.now = new AtomicLong(now.toEpochMilli());
    }

    @Override
    public Instant now() {
        return Instant.ofEpochMilli(this.now.getAndIncrement());
    }
}
