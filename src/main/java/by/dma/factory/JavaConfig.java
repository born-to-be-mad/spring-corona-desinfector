package by.dma.factory;

import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class JavaConfig implements Config {
    private Reflections scanner;

    private Map<Class, Class> implementations;

    public JavaConfig(String packageToScan, Map<Class, Class> implementations) {
        scanner = new Reflections(packageToScan);
        this.implementations = implementations;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> interfaceType) {
        return implementations.computeIfAbsent(interfaceType, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(interfaceType);
            if (classes.size() != 1) {
                throw new RuntimeException(
                    interfaceType + " has 0 or more than one implementation, please update your config");
            }
            return classes.iterator().next();
        });
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}
