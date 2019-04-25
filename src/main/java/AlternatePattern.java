import java.util.Iterator;
import java.util.List;

public class AlternatePattern extends LightSequence {

    /** Holds the string of coloured lights. **/
    private List<Light> fairylights;

    /** The list of sequences to be alternated between. **/
    private List<LightSequence> sequences;

    @Override
    public void run() {
        running = true;
        Iterator<LightSequence> Isequence = makeSequenceListCyclic(sequences);
        while (Isequence.hasNext() && running) {
            LightSequence currentSequence = Isequence.next();
            Thread sequenceThread = runSequenceInNewThread(currentSequence);

            sequenceThread.start();
            waitFor(THIRTY_SECONDS);
            if (sequenceThread.isAlive()) {
                try {
                    currentSequence.terminate();
                    sequenceThread.join();

                } catch (InterruptedException e) {
                    // do nothing
                }
            }
            toggleAllLights(fairylights,false); //reset lights so sequences don't get mingled.
        }
    }

    /**
     * Create a new thread to run a sequence on.
     * @param currentSequence
     * @return new thread to run the sequence on.
     */
    protected Thread runSequenceInNewThread(LightSequence currentSequence) {
        currentSequence.setRunning();
        return new Thread(currentSequence);
    }

    /**
     * Sets the list of lighting sequences to be alternated between.
     * @param sequences the lighting sequences.
     */
    public void setSequences(List<LightSequence> sequences) {
        this.sequences = sequences;
    }

    /**
     * The constructor
     * @param newFairylights the string of coloured lights.
     */
    public AlternatePattern(List<Light> newFairylights) {
        this.fairylights = newFairylights;
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
