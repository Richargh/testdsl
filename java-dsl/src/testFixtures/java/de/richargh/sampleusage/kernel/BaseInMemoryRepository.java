package de.richargh.sampleusage.kernel;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class BaseInMemoryRepository<TId extends EntityId, TEntity extends Entity<TId>> implements Repository<TId, TEntity> {

    private final ConcurrentHashMap<TId, TEntity> entities = new ConcurrentHashMap<>();

    @Override
    public void add(TEntity entity) {
        this.entities.put(entity.id(), entity);
    }

    @Override
    public Optional<TEntity> findById(TId id) {
        return Optional.ofNullable(entities.get(id));
    }

    public List<TEntity> filter(Predicate<TEntity> predicate) {
        return this.entities.values().stream()
                .filter(predicate)
                .toList();
    }

}
