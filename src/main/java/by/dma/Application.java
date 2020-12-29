package by.dma;

import java.util.Map;

import by.dma.annotation.Singleton;
import by.dma.factory.JavaConfig;
import by.dma.factory.ObjectFactory;

/**
 * Application runner.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> implementationClasses) {
        System.out.println("### RUNNING.... ###");
        JavaConfig config = new JavaConfig(packageToScan, implementationClasses);
        ApplicationContext context =  new ApplicationContext(config);

        ObjectFactory objectFactory =  new ObjectFactory(context);
        for (Class<?> aClass : config.getScanner().getTypesAnnotatedWith(Singleton.class)) {
            System.out.println("Init singleton: " + aClass.getName());
            context.cache(aClass, objectFactory.createObject(aClass));
        }

        context.setFactory(objectFactory);
        // context is created, we can send even 'ContextCreated'

        return context;
    }
}
