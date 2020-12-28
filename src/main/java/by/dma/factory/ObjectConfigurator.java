package by.dma.factory;

import by.dma.ApplicationContext;

/**
 * General interface for object configurator
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public interface ObjectConfigurator {
    void configure(Object obj, ApplicationContext context);
}
