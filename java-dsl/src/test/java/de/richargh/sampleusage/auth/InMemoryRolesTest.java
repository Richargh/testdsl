package de.richargh.sampleusage.auth;

public class InMemoryRolesTest extends RolesContract {
    @Override
    Roles testee() {
        return new InMemoryRolesDouble();
    }
}
