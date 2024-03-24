package de.richargh.sampleusage.app;

import de.richargh.sampleusage.auth.*;
import de.richargh.sampleusage.kernel.Clock;
import de.richargh.sampleusage.kernel.Ids;
import de.richargh.sampleusage.kernel.IncrementingClock;
import de.richargh.sampleusage.kernel.UuIds;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

public class SmallAppFloor implements AppFloor {

    private final ConcurrentHashMap<FloorKey, Object> ports = new ConcurrentHashMap<>();

    @Override
    public Books books() {
        return (Books) ports.computeIfAbsent(FloorKey.BOOKS, key -> new InMemoryBooksDouble());
    }

    @Override
    public Clock clock() {
        return (Clock) ports.computeIfAbsent(FloorKey.CLOCK, key -> new IncrementingClock(Instant.now()));
    }

    @Override
    public Ids ids() {
        return (Ids) ports.computeIfAbsent(FloorKey.IDs, key -> new UuIds());
    }

    @Override
    public Permissions permissions() {
        return (Permissions) ports.computeIfAbsent(FloorKey.PERMISSIONS, key -> new InMemoryPermissionsDouble());
    }

    @Override
    public Roles roles() {
        return (Roles) ports.computeIfAbsent(FloorKey.ROLES, key -> new InMemoryRolesDouble());
    }

    @Override
    public Users users() {
        return (Users) ports.computeIfAbsent(FloorKey.USERS, key -> new InMemoryUsersDouble());
    }

    private enum FloorKey {
        BOOKS,
        CLOCK,
        IDs,
        PERMISSIONS,
        ROLES,
        USERS
    }
}
