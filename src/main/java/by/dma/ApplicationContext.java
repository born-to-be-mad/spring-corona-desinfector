package by.dma;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.dma.annotation.Singleton;
import by.dma.factory.Config;
import by.dma.factory.ObjectFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ApplicationContext {
    @Setter
    private ObjectFactory factory;

    private Map<Class, Object> cache = new ConcurrentHashMap<>();

    @Getter
    private final Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T newObject = factory.createObject(implClass);
        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, newObject);
        }
        return newObject;
    }
}
