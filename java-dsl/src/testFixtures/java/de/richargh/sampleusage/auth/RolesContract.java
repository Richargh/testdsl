package de.richargh.sampleusage.auth;

import de.richargh.testdslcontrol.AppTestState;
import de.richargh.testdslcontrol.Small;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class RolesContract {
    abstract Roles testee();

    @Small
    @Test
    void should_remember_permission(AppTestState a) {
        // given
        var role = a.role();
        var testee = testee();
        // when
        testee.add(role);
        // then
        assertThat(testee.findById(role.id())).contains(role);
    }
}
