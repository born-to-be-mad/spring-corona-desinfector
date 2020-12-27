package by.dma.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private final List<ObjectConfigurator> configurators = new ArrayList<>();
    private final Config config;

    public static ObjectFactory getInstance() {
        return instance;
    }

    @SneakyThrows
    private ObjectFactory() {
        config = new JavaConfig("by.dma",
                                new HashMap<>(Map.of(Policeman.class, PolitePoliceman.class)));
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T newInstance = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(objectConfigurator -> objectConfigurator.coinfigure(newInstance));

        return newInstance;
    }
}
