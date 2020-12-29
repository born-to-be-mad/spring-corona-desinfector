package by.dma;

import java.util.Random;

/**
 * Simple room entity.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class Room {

    private int number;

    public Room() {
        number = new Random().nextInt(100);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("number=").append(number);
        sb.append('}');
        return sb.toString();
    }
}
