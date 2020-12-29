package by.dma;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.dma.annotation.Singleton;
import by.dma.factory.Config;
import by.dma.factory.ObjectFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * Application Context.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ApplicationContext {
    @Setter
    private ObjectFactory factory;

    private final Map<Class, Object> cache = new ConcurrentHashMap<>();

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
            cache(type, newObject);
        }
        return newObject;
    }

    public void cache(Class type, Object newObject) {
        if (type.isInterface()) {
            cache.put(type, newObject);
        } else {
            for (Class classInterface : type.getInterfaces()) {
                cache.put(classInterface, newObject);
            }
        }
    }
}
