import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Class to handle toggle lights sequentially.
 */
public class SequencePattern extends LightSequence{

    public void run() {
        System.out.println("--- Sequential ---");
        running = true;
        Iterator<Light> Ifairylights = makeLightListCyclic(fairylights);
            try {
                while(Ifairylights.hasNext() && running) {
                    final Light currentLight = Ifairylights.next();
                    currentLight.toggle();
                    clearLightDisplay();
                    displayAllLights();
                    waitFor(ONE_SECOND);
                    currentLight.toggle();
                    clearLightDisplay();
                    displayAllLights();

                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }

    }

    /** Constructor **/
    public SequencePattern(List<Light> fairylights) {
        this.fairylights = fairylights;
    }

    void waitFor(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            //fail silently as calling the interrupt purposefully to
            //make sure the thread exits gracefully.
        }
    }
}
