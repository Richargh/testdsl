package de.richargh.sampleusage.auth;

import de.richargh.sampleusage.kernel.EntityId;

public record UserId(String rawValue) implements EntityId {
}
