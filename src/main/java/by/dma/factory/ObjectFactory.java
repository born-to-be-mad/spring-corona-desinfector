package by.dma.factory;

import lombok.SneakyThrows;

/**
 * Universal
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ObjectFactory {
    public static ObjectFactory instance = new ObjectFactory();

    private Config config = new JavaConfig("by.dma");

    public static ObjectFactory getInstance() {
        return instance;
    }

    private ObjectFactory() {
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
