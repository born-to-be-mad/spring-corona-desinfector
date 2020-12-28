package by.dma.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;

import by.dma.ApplicationContext;
import by.dma.proxy.ProxyConfigurator;
import lombok.SneakyThrows;

/**
 * Singleton, it runs slowly first time cause it scans all packages and prepares configurators.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ObjectFactory {
    private final List<ObjectConfigurator> configurators = new ArrayList<>();
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    private final ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        Reflections scanner = context.getConfig().getScanner();
        for (Class<? extends ObjectConfigurator> aClass : scanner.getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

        for (Class<? extends ProxyConfigurator> aClass : scanner.getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T newInstance = create(implClass);

        configure(newInstance);

        invokeInit(implClass, newInstance);

        newInstance = wrapWithProxyIfNeeded(implClass, newInstance);

        return newInstance;
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T newInstance) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            newInstance = (T) proxyConfigurator.replaceWithProxyIfNeeded(newInstance, implClass);
        } return newInstance;
    }

    private <T> void invokeInit(Class<T> implClass, T newInstance) throws InvocationTargetException, IllegalAccessException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(newInstance);
            }
        }
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException,
                                                    InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }

    private <T> void configure(T newInstance) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(newInstance, context));
    }
}
