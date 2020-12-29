package by.dma.factory;

import org.reflections.Reflections;

/**
 * Config interface
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> interfaceType);

    Reflections getScanner();
}
