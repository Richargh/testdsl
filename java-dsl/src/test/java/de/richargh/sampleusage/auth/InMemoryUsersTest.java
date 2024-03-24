package de.richargh.sampleusage.auth;

public class InMemoryUsersTest extends UsersContract {
    @Override
    Users testee() {
        return new InMemoryUsersDouble();
    }
}
