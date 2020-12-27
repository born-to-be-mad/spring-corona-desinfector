package by.dma.service;

import by.dma.annotation.InjectProperty;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class SimpleRecommendator implements Recommendator {
    @InjectProperty
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("ADVERTISING: to protect from covid-19, drink " + alcohol);
    }
}
