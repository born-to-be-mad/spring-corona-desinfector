package by.dma.factory;

import java.lang.reflect.Field;

import by.dma.ApplicationContext;
import by.dma.annotation.InjectByType;
import lombok.SneakyThrows;

/**
 * Infrastructure class.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {
    @SneakyThrows
    @Override
    public void configure(Object type, ApplicationContext context) {
        for (Field field : type.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(type, object);
            }
        }

    }
}
