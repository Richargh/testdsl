package de.richargh.sampleusage.kernel;

import java.util.Optional;

public interface Repository<TId extends EntityId, TEntity extends Entity<TId>> {
    void add(TEntity entity);

    Optional<TEntity> findById(TId id);
}
