package de.richargh.sampleusage.auth;

import java.util.List;

public record UserCombo(
        User user,
        Role role,
        List<Permission> permissions
) {
}
