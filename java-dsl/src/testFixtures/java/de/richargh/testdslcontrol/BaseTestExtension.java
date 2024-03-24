package de.richargh.testdslcontrol;

import de.richargh.sampleusage.app.AppFloor;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace;

public abstract class BaseTestExtension implements ParameterResolver {

    protected abstract AppFloor floor();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return isTestState(parameterContext) || isFloor(parameterContext);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var storeNamespace = Namespace.create(
                getClass(), extensionContext.getRequiredTestMethod());
        var store = extensionContext.getStore(storeNamespace);

        var dsl = store.getOrComputeIfAbsent(
                "UNIT_TEST_DSL",
                (key) -> LowLevelDsl.of(floor()), // <4>
                LowLevelDsl.class
        );

        if (isTestState(parameterContext))
            return dsl.testState();
        else if (isFloor(parameterContext))
            return dsl.floor();
        else
            throw new ParameterResolutionException("Cannot resolve parameter " + parameterContext.getParameter());
    }

    private static boolean isTestState(ParameterContext parameterContext) {
        return parameterContext.getParameter().getType().equals(AppTestState.class);
    }

    private static boolean isFloor(ParameterContext parameterContext) {
        return parameterContext.getParameter().getType().equals(AppFloor.class);
    }
}