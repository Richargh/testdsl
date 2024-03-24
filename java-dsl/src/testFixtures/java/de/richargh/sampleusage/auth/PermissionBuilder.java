package de.richargh.sampleusage.auth;

import de.richargh.sampleusage.kernel.Clock;
import de.richargh.sampleusage.kernel.DataFabrications;
import de.richargh.sampleusage.kernel.Ids;
import de.richargh.testdslcontrol.BaseTestBuilder;

import java.util.function.Consumer;

public final class PermissionBuilder extends BaseTestBuilder<Permission> {

    public PermissionCode code = PermissionCode.SOME_PERMISSION;

    public PermissionBuilder(Clock clock, DataFabrications fabrications, Ids ids) {
        super(clock, fabrications, ids);
    }

    public Permission build() {
        return new Permission(
                ids.next(PermissionId.class),
                code
        );
    }

    public PermissionBuilder with(Consumer<PermissionBuilder> action) {
        action.accept(this);
        return this;
    }
}
