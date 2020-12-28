package by.dma.proxy;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public interface ProxyConfigurator {
    Object replaceWithProxyIfNeeded(Object type, Class implClass);
}
