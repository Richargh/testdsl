package de.richargh.sampleusage.auth;

import de.richargh.sampleusage.kernel.Entity;

import java.util.List;

public record Role(
        RoleId id,
        List<PermissionId> permissions) implements Entity<RoleId> {
}
