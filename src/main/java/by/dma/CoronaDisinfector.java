package by.dma;

import by.dma.annotation.InjectByType;
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
@Deprecated
public class CoronaDisinfector {

    // This is an example of LOOKUP
/*
    private final Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
    private final Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);
*/

    // IoC: don't call us, we call you!
    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

    public void start(Room room) {
        announcer.announce("Starting disinfection! Go out!");
        policeman.makePeopleLeaveRoom();
        disinfect(room);
        announcer.announce("Please return to your room!");
    }

    private void disinfect(Room room) {
        System.out.println("Disinfecting the room: " + room);
        System.out.printf("\tA prayer is read out: 'Covid-19 go out!'%n " +
                          "\t-> The prayer is read, the virus is cast into hell%n");

    }
}
