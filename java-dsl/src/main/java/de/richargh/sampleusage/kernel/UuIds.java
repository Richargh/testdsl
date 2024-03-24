package de.richargh.sampleusage.kernel;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class UuIds implements Ids {
    public <T> T next(Class<T> tClass) {
        try {
            return tClass
                    .getDeclaredConstructor(String.class)
                    .newInstance(UUID.randomUUID().toString());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException ex) {
            throw new IdGeneratorException(ex);
        }
    }
}
