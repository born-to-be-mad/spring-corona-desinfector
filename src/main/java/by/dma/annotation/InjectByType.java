package by.dma.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Custom annotation for injecting by type.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectByType {
}
