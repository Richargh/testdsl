package de.richargh.testdslcontrol;

import de.richargh.sampleusage.kernel.Clock;
import de.richargh.sampleusage.kernel.DataFabrications;
import de.richargh.sampleusage.kernel.Ids;
import de.richargh.sampleusage.kernel.TestBuilder;

import java.util.Arrays;

public abstract class BaseTestBuilder<Element> implements TestBuilder<Element> {

    protected final Clock clock;
    protected final DataFabrications fabrications;
    protected final Ids ids;

    protected BaseTestBuilder(Clock clock, DataFabrications fabrications, Ids ids) {
        this.clock = clock;
        this.fabrications = fabrications;
        this.ids = ids;
    }

    protected String nextNameOf(String... backlog) {
        return this.fabrications.namesOf(getClass(), Arrays.asList(backlog)).next();
    }

    protected <T> T nextNameOf(Class<T> tClass, String... backlog) {
        return this.fabrications.namesOf(getClass(), Arrays.asList(backlog)).next(tClass);
    }
}
