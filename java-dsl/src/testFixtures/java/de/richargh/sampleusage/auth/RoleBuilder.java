package de.richargh.sampleusage.auth;

import de.richargh.sampleusage.kernel.Clock;
import de.richargh.sampleusage.kernel.DataFabrications;
import de.richargh.sampleusage.kernel.Ids;
import de.richargh.testdslcontrol.BaseTestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public final class RoleBuilder extends BaseTestBuilder<Role> {

    public List<PermissionId> permissions = List.of();

    public RoleBuilder(Clock clock, DataFabrications fabrications, Ids ids) {
        super(clock, fabrications, ids);
    }

    public Role build() {
        return new Role(
                ids.next(RoleId.class),
                this.permissions
        );
    }

    public RoleBuilder with(Consumer<RoleBuilder> action) {
        action.accept(this);
        return this;
    }

    public RoleBuilder withPermissions(List<Permission> permissions) {
        this.permissions = permissions.stream().map(Permission::id).toList();
        return this;
    }

    public RoleBuilder withPermissions(PermissionId... permissions) {
        this.permissions = Arrays.stream(permissions).toList();
        return this;
    }
}
