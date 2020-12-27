package by.dma;

/**
 * Initial task:
 * - 1. announce everybody about the start of disinfection and ask o leave the room
 * - 2. disperse all others after announcement
 * - 3. disinfect
 * - 4. inform everybody to return
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class CoronaDisinfector {

    public void start(Room room) {
        disinfect(room);
    }

    private void disinfect(Room room){
        System.out.println("A prayer is read out: 'Covid-19 go out!' - the prayer is read, the virus is cast into hell");
    }
}
