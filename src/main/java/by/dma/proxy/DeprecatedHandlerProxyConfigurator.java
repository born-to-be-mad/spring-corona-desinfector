package by.dma.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

/**
 * DeprecatedHandlerProxy implementation of {@code ProxyConfigurator}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIfNeeded(Object type, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {
            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(implClass,
                                       (InvocationHandler) (proxy, method, args) -> getInvocationHandlerLogic(type, method, args));
            }

            return Proxy.newProxyInstance(
                implClass.getClassLoader(), implClass.getInterfaces(),
                (proxy, method, args) -> getInvocationHandlerLogic(type, method, args));
        }
        return type;
    }

    private Object getInvocationHandlerLogic(Object type, Method method, Object[] args)
        throws IllegalAccessException, InvocationTargetException {
        System.out.printf("******************* Deprecated method '#%s' is called!!!!%n", method.getName());
        // call real method
        return method.invoke(type, args);
    }
}
