package de.richargh.sampleusage.auth;

import de.richargh.sampleusage.kernel.Clock;
import de.richargh.sampleusage.kernel.DataFabrications;
import de.richargh.sampleusage.kernel.Ids;
import de.richargh.sampleusage.kernel.TestIds;
import de.richargh.testdslcontrol.BaseTestBuilder;

import java.util.function.Consumer;

public final class UserBuilder extends BaseTestBuilder<User> {

    public String name = nextNameOf("Alex", "Taylor");
    public RoleId role = TestIds.role;

    public UserBuilder(Clock clock, DataFabrications fabricates, Ids ids) {
        super(clock, fabricates, ids);
    }

    public User build() {
        return new User(
                ids.next(UserId.class),
                name,
                role
        );
    }

    public UserBuilder with(Consumer<UserBuilder> action) {
        action.accept(this);
        return this;
    }

    public UserBuilder withRole(Role role) {
        this.role = role.id();
        return this;
    }
}
