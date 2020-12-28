package by.dma.service;

import by.dma.annotation.InjectProperty;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class DrinkRecommendator implements Recommendator {
    @InjectProperty("whiskey")
    private String alcohol;

    @Override
    public void recommend() {
        System.out.printf("ADVERTISING: to protect from covid-19, drink '%s'%n", alcohol);
    }
}
