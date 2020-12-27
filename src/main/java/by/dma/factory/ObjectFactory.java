package by.dma.factory;

import java.util.HashMap;
import java.util.Map;

import by.dma.service.Policeman;
import by.dma.service.PolitePoliceman;
import lombok.SneakyThrows;

/**
 * Universal
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ObjectFactory {
    public static ObjectFactory instance = new ObjectFactory();

    private Config config;

    public static ObjectFactory getInstance() {
        return instance;
    }

    private ObjectFactory() {
        config = new JavaConfig("by.dma",
                                new HashMap<>(Map.of(Policeman.class, PolitePoliceman.class)));
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T newInstance = implClass.getDeclaredConstructor().newInstance();

        // TODO

        return newInstance;
    }
}
