package de.richargh.sampleusage.auth;

import de.richargh.sampleusage.kernel.Clock;
import de.richargh.sampleusage.kernel.DataFabrications;
import de.richargh.sampleusage.kernel.Ids;
import de.richargh.testdslcontrol.BaseTestBuilder;

import java.util.List;
import java.util.stream.Stream;

public final class UserComboBuilder extends BaseTestBuilder<UserCombo> {
    // combination fields
    private List<Permission> permissions = List.of(new PermissionBuilder(clock, fabrications, ids).build());

    public UserComboBuilder(Clock clock, DataFabrications fabrications, Ids ids) {
        super(clock, fabrications, ids);
    }

    public UserCombo build() {
        var role = new RoleBuilder(clock, fabrications, ids).withPermissions(permissions).build();
        var user = new UserBuilder(clock, fabrications, ids).withRole(role).build();

        return new UserCombo(user, role, permissions);
    }

    public UserComboBuilder hasPermissions(PermissionCode... permissionCode) {
        this.permissions = Stream.of(permissionCode)
                .map(code -> new Permission(ids.next(PermissionId.class), code))
                .toList();
        return this;
    }
}
