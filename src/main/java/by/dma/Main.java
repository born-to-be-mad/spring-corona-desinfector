package by.dma;

/**
 * Main runner.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class Main {
    public static void main(String[] args) {
        CoronaDisinfector disinfector = new CoronaDisinfector();

        Room roomToDisinfect = new Room();
        disinfector.start(roomToDisinfect);
    }
}
