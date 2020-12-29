package by.dma.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import by.dma.ApplicationContext;
import by.dma.annotation.InjectProperty;
import lombok.SneakyThrows;

import static java.util.stream.Collectors.toMap;

/**
 * InjectObject implementation of {@code ObjectConfigurator}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class InjectObjectConfigurator implements ObjectConfigurator {

    public static final String PROPERTY_FILE = "application.properties";

    private final Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectObjectConfigurator() {
        String propertiesPath = ClassLoader.getSystemClassLoader().getResource(PROPERTY_FILE).getPath();
        Stream<String> lines = new BufferedReader(new FileReader(propertiesPath)).lines();
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @SneakyThrows
    @Override
    public void configure(Object obj, ApplicationContext context) {
        Class<?> implClass = obj.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);

            if (annotation != null) {
                String value = annotation.value().isEmpty()
                               ? propertiesMap.get(field.getName())
                               : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(obj, value);
            }
        }
    }
}
