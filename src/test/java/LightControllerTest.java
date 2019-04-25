import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LightController.class)
public class LightControllerTest {

    @Mock
    private LightController classUnderTest;

    @Test
    public void generateLightSequence() {

        LightController controller = new LightController(Arrays.asList(Colour.RED,Colour.GREEN,Colour.WHITE),6);
        List<Light> result = controller.fairylights;

        assertEquals(6, result.size());
        assertEquals(Colour.RED, result.get(0).getColour());
        assertEquals(Colour.GREEN, result.get(1).getColour());
        assertEquals(Colour.WHITE, result.get(2).getColour());
        assertEquals(Colour.RED, result.get(3).getColour());
        assertEquals(Colour.GREEN, result.get(4).getColour());
        assertEquals(Colour.WHITE, result.get(5).getColour());
    }

    @Test
    public void startSequence() {
        LightController controller = new LightController(Arrays.asList(Colour.RED,Colour.GREEN,Colour.WHITE),20);

        AlternatePattern mockPatternAlternate = mock(AlternatePattern.class);
        controller.patternAlternate = mockPatternAlternate;
        SequencePattern mockPatternSequence = mock(SequencePattern.class);
        controller.patternSequence = mockPatternSequence;
        ColourPattern mockPatternColour = mock(ColourPattern.class);
        controller.patternColour = mockPatternColour;

        doNothing().when(mockPatternAlternate).run();
        doNothing().when(mockPatternSequence).run();
        doNothing().when(mockPatternColour).run();


        controller.startSequence("Sequence");
        verify(controller.patternSequence, times(1)).run();

        controller.startSequence("Colour");
        verify(controller.patternColour, times(1)).run();

        controller.startSequence("Alternate");
        verify(controller.patternAlternate, times(1)).run();

        controller.startSequence("bogusCall");
        verify(controller.patternSequence, times(1)).run();
        verify(controller.patternColour, times(1)).run();
        verify(controller.patternAlternate, times(1)).run();
    }
}