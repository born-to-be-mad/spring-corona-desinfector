package by.dma.factory;

import java.util.ArrayList;
import java.util.List;

import org.reflections.Reflections;

import by.dma.ApplicationContext;
import lombok.Setter;
import lombok.SneakyThrows;

/**
 * Singleton, it runs slowly first time cause it scans all packages and prepares configurators.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ObjectFactory {
    private final List<ObjectConfigurator> configurators = new ArrayList<>();

    private final ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        Reflections scanner = context.getConfig().getScanner();
        for (Class<? extends ObjectConfigurator> aClass : scanner.getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T newInstance = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(objectConfigurator -> objectConfigurator.configure(newInstance, context));

        return newInstance;
    }
}
