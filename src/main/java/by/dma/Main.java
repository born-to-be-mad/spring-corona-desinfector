package by.dma;

import java.util.HashMap;
import java.util.Map;

import by.dma.service.AngryPoliceman;
import by.dma.service.Policeman;

/**
 * Main runner.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class Main {
    public static void main(String[] args) {
//        CoronaDisinfector disinfector = ObjectFactory.getInstance().createObject(CoronaDisinfector.class);
        ApplicationContext context =
            Application.run("by.dma", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));

        Room roomToDisinfect = new Room();
        CoronaDisinfector disinfector = context.getObject(CoronaDisinfector.class);
        disinfector.start(roomToDisinfect);
    }
}
