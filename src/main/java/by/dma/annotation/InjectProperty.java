package by.dma.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Custom annotation for injecting property with value.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectProperty {
    String value() default "";
}
