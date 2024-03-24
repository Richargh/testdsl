package de.richargh.sampleusage.kernel;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface DataFabrications {

    NameFabrications namesOf(Object namespace, List<String> backlog);

    static DataFabrications create() {
        return new PersistentDataFabrications();
    }

    class PersistentDataFabrications implements DataFabrications {

        private final ConcurrentHashMap<Object, NameFabrications> names = new ConcurrentHashMap<>();

        public NameFabrications namesOf(Object namespace, List<String> backlog) {
            return names.computeIfAbsent(namespace, key -> new UuidFallbackNameFabrications(backlog));
        }
    }
}
