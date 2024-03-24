package de.richargh.testdslcontrol;

import de.richargh.sampleusage.app.AppFloor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SmallTestExtensionMethodTest {

    @Small
    @Test
    public void should_resolve_TestState_when_Method_is_annotated(AppTestState a) {
        // then
        assertThat(a).isInstanceOf(AppTestState.class);
        assertThat(a.book()).isNotNull();
    }

    @Small
    @Test
    public void should_resolve_Floor_when_Method_is_annotated(AppFloor floor) {
        // then
        assertThat(floor).isInstanceOf(AppFloor.class);
        assertThat(floor.books()).isNotNull();
    }

    @Small
    @Test
    public void should_resolve_Dsl_when_Method_is_annotated(AppTestState a, AppFloor floor) {
        // then
        assertThat(a).isInstanceOf(AppTestState.class);
        assertThat(floor).isInstanceOf(AppFloor.class);
    }

    @Small
    @ParameterizedTest
    @ValueSource(strings = {"foo", "bar"})
    public void should_resolve_Dsl_and_String_Parameters_when_Method_is_annotated(String someStr, AppTestState a, AppFloor floor) {
        // then
        assertThat(a).isInstanceOf(AppTestState.class);
        assertThat(floor).isInstanceOf(AppFloor.class);
        assertThat(someStr).isNotBlank();
    }

    @Small
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void should_resolve_Dsl_and_Number_Parameters_when_Method_is_annotated(int someNum, AppTestState a, AppFloor floor) {
        // then
        assertThat(a).isInstanceOf(AppTestState.class);
        assertThat(floor).isInstanceOf(AppFloor.class);
        assertThat(someNum).isIn(1, 2);
    }

    @Nested
    class TheNest {
        @Small
        @Test
        public void should_resolve_Teststate_when_nested_Method_is_annotated(AppTestState a) {
            // then
            assertThat(a).isInstanceOf(AppTestState.class);
            assertThat(a.book()).isNotNull();
        }

        @Small
        @Test
        public void should_resolve_Floor_when_nested_Method_is_annotated(AppFloor floor) {
            // then
            assertThat(floor).isInstanceOf(AppFloor.class);
            assertThat(floor.books()).isNotNull();
        }

        @Small
        @Test
        public void should_resolve_Dsl_when_nested_Method_is_annotated(AppTestState a, AppFloor floor) {
            // then
            assertThat(a).isInstanceOf(AppTestState.class);
            assertThat(floor).isInstanceOf(AppFloor.class);
        }

        @Small
        @ParameterizedTest
        @ValueSource(strings = {"foo", "bar"})
        public void should_resolve_Dsl_and_String_Parameters_when_nested_Method_is_annotated(String someStr, AppTestState a, AppFloor floor) {
            // then
            assertThat(a).isInstanceOf(AppTestState.class);
            assertThat(floor).isInstanceOf(AppFloor.class);
            assertThat(someStr).isNotBlank();
        }

        @Small
        @ParameterizedTest
        @ValueSource(ints = {1, 2})
        public void should_resolve_Dsl_and_Number_Parameters_when_nested_Method_is_annotated(int someNum, AppTestState a, AppFloor floor) {
            // then
            assertThat(a).isInstanceOf(AppTestState.class);
            assertThat(floor).isInstanceOf(AppFloor.class);
            assertThat(someNum).isIn(1, 2);
        }
    }

}
