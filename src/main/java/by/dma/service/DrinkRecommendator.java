package by.dma.service;

import by.dma.annotation.InjectProperty;
import by.dma.annotation.Singleton;

/**
 * Drink implementation of {@code Recommendator}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
@Singleton
@Deprecated
public class DrinkRecommendator implements Recommendator {
    @InjectProperty("whiskey")
    private String alcohol;

    public DrinkRecommendator() {
        System.out.println("Drink Recommendator was created(only once cause it is @Singleton=" + this.toString());
    }

    @Override
    public void recommend() {
        System.out.printf("ADVERTISING: to protect from covid-19, drink '%s'%n", alcohol);
    }
}
