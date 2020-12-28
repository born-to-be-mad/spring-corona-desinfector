package by.dma.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;

import by.dma.ApplicationContext;
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
        T newInstance = create(implClass);

        configure(newInstance);

        invokeInit(implClass, newInstance);

        if(implClass.isAnnotationPresent(Deprecated.class)) {
            return (T) Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
                      new InvocationHandler() {
               @Override
               public Object invoke(Object proxy, Method method, Object[] args)
                   throws Throwable {
                   System.out.println("******************* Deprecated method is called!!!!");
                   // call real method
                   return method.invoke(newInstance, args);
               }
           });
        }
        return newInstance;
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
