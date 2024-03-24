package de.richargh.sampleusage.kernel;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UuidFallbackNameFabrications implements NameFabrications {
    private final ConcurrentLinkedQueue<String> backlog;

    public UuidFallbackNameFabrications(List<String> backlog) {
        this.backlog = new ConcurrentLinkedQueue<>(backlog);
    }

    public String next() {
        var result = backlog.poll();
        if (result != null)
            return result;
        else
            return UUID.randomUUID().toString();
    }

    public <T> T next(Class<T> tClass) {
        try {
            return tClass
                    .getDeclaredConstructor(String.class)
                    .newInstance(next());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException ex) {
            throw new NameRandomsException(ex);
        }
    }
}
