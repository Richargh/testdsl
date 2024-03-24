package de.richargh.sampleusage.auth;

import de.richargh.sampleusage.kernel.Entity;

public record Permission(PermissionId id, PermissionCode code) implements Entity<PermissionId> {
}
