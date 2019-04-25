import com.google.common.collect.Iterators;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public abstract class LightSequence implements Runnable{

    public static final int ONE_SECOND = 1000;
    public static final int THIRTY_SECONDS = 4 * ONE_SECOND;

    protected List<Light> fairylights;

    /** flag to gracefully stop the thread **/
    protected boolean running;

    /**
     * Utility function to clear the console.
     * @throws IOException
     * @throws InterruptedException
     */
    protected void clearLightDisplay() throws IOException, InterruptedException {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }

    /**
     * Display the entire string of lights in the console.
     */
     void displayAllLights(){
        fairylights.stream().forEach(light ->  System.out.println(light.display()));
    }

    /**
     * Used for unit testing.
     * @param fairylights lightstring
     */
     void setFairylights(List<Light> fairylights) {
        this.fairylights = fairylights;
    }

    /** Makes the list cycle indefinitely **/
     Iterator<Light> makeLightListCyclic(List<Light> newFairylights) {
        return Iterators.cycle(newFairylights);
    }

    /** Makes the sequence cycle indefinitely **/
     Iterator<Colour> makeColourListCyclic(List<Colour> colourSequence) {
        return Iterators.cycle(colourSequence);
    }

    /** Makes the sequence cycle indefinitely **/
    Iterator<LightSequence> makeSequenceListCyclic(List<LightSequence> lightSequence) {
        return Iterators.cycle(lightSequence);
    }

    /** allows thread.sleep to be mocked for testing **/
     abstract void waitFor(Integer time);


    public void terminate() {
        running = false;
    }

    public void setRunning(){
        running = true;
    }

    /**
     * Change all lights to the same state.
     * @param isLit the new state.
     */
    void toggleAllLights(List<Light> fairylights, boolean isLit) {
        fairylights.forEach(light -> light.setLit(isLit));
    }



}