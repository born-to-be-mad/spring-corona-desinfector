package by.dma.service;

import by.dma.annotation.InjectByType;

/**
 * Console implementation of {@code Announcer}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommendator recommendator;

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
