package by.dma.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIfNeeded(Object type, Class implClass) {
        if(implClass.isAnnotationPresent(Deprecated.class)) {
            return Proxy.newProxyInstance(
                implClass.getClassLoader(), implClass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                        throws Throwable {
                        System.out.println("******************* Deprecated method is called!!!!");
                        // call real method
                        return method.invoke(type, args);
                    }
                });
        }
        return type;
    }
}
