package by.dma.service;

import by.dma.factory.ObjectFactory;

/**
 * Console implementation of {@code Announcer}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ConsoleAnnouncer implements Announcer {
    private Recommendator recommendator = ObjectFactory.getInstance().createObject(Recommendator.class);

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
