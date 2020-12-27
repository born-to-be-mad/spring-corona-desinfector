package by.dma.factory;

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

    public JavaConfig(String packageToScan) {
        scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> interfaceType) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(interfaceType);
        if (classes.size() != 1) {
            throw new RuntimeException(interfaceType + " has 0 or more than one implementation");
        }
        return classes.iterator().next();
    }
}
