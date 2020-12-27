package by.dma.service;

/**
 * Console implementation of {@code Announcer}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ConsoleAnnouncer implements Announcer {
    @Override
    public void announce(String message) {
        System.out.println(message);
    }
}
