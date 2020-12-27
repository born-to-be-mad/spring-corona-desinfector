package by.dma.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.dma.annotation.InjectProperty;
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

        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);

            String propertiesPath = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            Stream<String> lines = new BufferedReader(new FileReader(propertiesPath)).lines();
            Map<String, String> propertiesMap =
                lines.map(line -> line.split("=")).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
            if (annotation != null) {
                String value = annotation.value().isEmpty()
                               ? propertiesMap.get(field.getName())
                               : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(newInstance, value);
            }
        }


        return newInstance;
    }
}
