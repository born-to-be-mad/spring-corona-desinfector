package by.dma;

import java.util.Map;

import by.dma.factory.JavaConfig;
import by.dma.factory.ObjectFactory;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> implementationClasses) {
        JavaConfig config = new JavaConfig(packageToScan, implementationClasses);
        ApplicationContext context =  new ApplicationContext(config);
        ObjectFactory objectFactory =  new ObjectFactory(context);
        //TODO: init all singletons which are not lazy(scan, search for Singleton, crate objects and put into context)
        context.setFactory(objectFactory);
        // context create, we can send even 'ContextCreated'

        return context;
    }
}
