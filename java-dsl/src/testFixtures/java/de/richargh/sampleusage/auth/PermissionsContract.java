package de.richargh.sampleusage.auth;

import de.richargh.testdslcontrol.AppTestState;
import de.richargh.testdslcontrol.Small;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class PermissionsContract {
    abstract Permissions testee();

    @Small
    @Test
    void should_remember_permission(AppTestState a) {
        // given
        var permission = a.permission();
        var testee = testee();
        // when
        testee.add(permission);
        // then
        assertThat(testee.findById(permission.id())).contains(permission);
    }
}
