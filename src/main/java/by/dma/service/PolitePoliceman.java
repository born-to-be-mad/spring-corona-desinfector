package by.dma.service;

/**
 * Polite Policeman implementation.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class PolitePoliceman implements Policeman {
    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("Please the room, we want to resque you!");
    }
}
