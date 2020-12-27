package by.dma.factory;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> interfaceType);
}
