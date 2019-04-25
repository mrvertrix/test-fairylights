import com.google.common.collect.Iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Class to control the starting of light sequences.
 */
public class LightController {

    protected List<Light> fairylights;

    protected SequencePattern patternSequence;
    protected ColourPattern patternColour;
    protected AlternatePattern patternAlternate;
    /**
     * Constructor.
     * @param newColourSequence the sequence of colours to repeat.
     * @param listSize the total size of the string of lights.
     */
    public LightController(List<Colour> newColourSequence, int listSize) {
        generateLightSequence(newColourSequence,listSize);
        patternSequence =  new SequencePattern(fairylights);
        patternColour =  new ColourPattern(fairylights, newColourSequence);
        patternAlternate = new AlternatePattern(fairylights);
    }

    /**
     * Creates a list of colours of a given length, built from a repeating pattern
     * @param newColourSequence
     * @param listSize
     * @return List of colours
     */
    protected void generateLightSequence(List<Colour> newColourSequence, int listSize) {
        Iterator<Colour> cyclingIterator = Iterators.cycle(newColourSequence);
        fairylights =  new ArrayList();
        for (int i = 0; i < listSize; i++) {
            fairylights.add(new Light(i,cyclingIterator.next()));
        }
    }

    /**
     * Start a sequence using the corresponding sequence name string.
     * @param sequenceName
     */
    public void startSequence(String sequenceName){
        if (sequenceName.equalsIgnoreCase("Sequence")) {
            patternSequence.run();
        }
        if (sequenceName.equalsIgnoreCase("Colour")) {
            patternColour.run();
        }
        if (sequenceName.equalsIgnoreCase("Alternate")) {
            patternAlternate.setSequences(Arrays.asList(patternSequence,patternColour));
            patternAlternate.run();
        }
    }
}
