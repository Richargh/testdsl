package de.richargh.sampleusage.auth;

public class InMemoryPermissionsTest extends PermissionsContract {
    @Override
    Permissions testee() {
        return new InMemoryPermissionsDouble();
    }
}
