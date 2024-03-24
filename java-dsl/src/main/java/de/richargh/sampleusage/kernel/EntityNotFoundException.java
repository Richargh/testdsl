package de.richargh.sampleusage.kernel;

public class EntityNotFoundException extends RuntimeException {

    public <TId extends EntityId, TEntity extends Entity<TId>> EntityNotFoundException(TId id, Class<TEntity> type) {
        super(String.format("Could not find %s with id %s", type, id));
    }
}
