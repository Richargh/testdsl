package de.richargh.sampleusage.kernel;

public interface NameFabrications {
    String next();

    <T> T next(Class<T> tClass);
}
