package de.richargh.sampleusage.auth;

import de.richargh.sampleusage.kernel.Entity;

public record User(UserId id, String name, RoleId roleId) implements Entity<UserId> {
}
