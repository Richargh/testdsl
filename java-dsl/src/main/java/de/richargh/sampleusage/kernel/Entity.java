package de.richargh.sampleusage.kernel;

public interface Entity<TId extends EntityId> {
    TId id();
}
