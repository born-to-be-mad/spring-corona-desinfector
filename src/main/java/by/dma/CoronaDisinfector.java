package by.dma;

import by.dma.factory.ObjectFactory;
import by.dma.service.Announcer;
import by.dma.service.Policeman;

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

    private Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
    private Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);

    public void start(Room room) {
        announcer.announce("Starting disinfection! Go out!");
        policeman.makePeopleLeveRoom();
        disinfect(room);
        announcer.announce("Please return to your room!");
    }

    private void disinfect(Room room) {
        System.out.println("Disinfecting the room: " + room);
        System.out.printf(
            "\tA prayer is read out: 'Covid-19 go out!'%n \t-> The prayer is read, the virus is cast into hell%n");
    }
}
