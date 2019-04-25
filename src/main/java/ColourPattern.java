import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Class to handle toggling lights by colour group.
 */
public class ColourPattern extends LightSequence {

    private List<Colour> colourSequence;

    public ColourPattern(List<Light> newfairylights, List<Colour> newColourSequence) {
       setFairylights(newfairylights);
       setSequence(newColourSequence);
    }

    @Override
    public void run() {
        System.out.println("--- Colour ---");
        running = true;
        Iterator<Colour> IColourSequence = makeColourListCyclic(colourSequence);
        try {
            while (IColourSequence.hasNext() && running) {
                Colour currentColour = IColourSequence.next();
                clearLightDisplay();
                displayAllLights();
                fairylights.stream()
                           .filter(light -> light.getColour()
                                                 .equals(currentColour))
                           .forEach(light -> light.toggle());
                clearLightDisplay();
                displayAllLights();
                waitFor(ONE_SECOND);
                fairylights.stream()
                           .filter(light -> light.getColour()
                                                 .equals(currentColour))
                           .forEach(light -> light.toggle());
                clearLightDisplay();
                displayAllLights();
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    void waitFor(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            //fail silently as calling the interrupt purposefully to
            //make sure the thread exits gracefully.
        }
    }

    protected void setSequence(List<Colour> newColourSequence) {
        colourSequence = newColourSequence;
    }
}
