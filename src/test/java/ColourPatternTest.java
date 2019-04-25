import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SequencePattern.class)
public class ColourPatternTest {

    private List<Light> lights;

    @Mock
    private ColourPattern classUnderTest;
    @Before
    public void setUp() throws InterruptedException {

        lights = Arrays.asList(new Light(0,Colour.RED));
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void run() throws InterruptedException {
        Iterator<Colour> mockIterator = Mockito.mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(true,false);
        when(mockIterator.next()).thenReturn(Colour.RED,Colour.GREEN,Colour.WHITE);

        Mockito.doNothing().when(classUnderTest).waitFor(any(Integer.class));
        Mockito.doCallRealMethod().when(classUnderTest).run();
        Mockito.doCallRealMethod().when(classUnderTest).setFairylights(any());
        Mockito.doCallRealMethod().when(classUnderTest).displayAllLights();
        Mockito.doReturn(mockIterator).when(classUnderTest).makeColourListCyclic(any());

        classUnderTest.setFairylights(lights);
        final List<Colour> colourSequence = Arrays.asList(Colour.RED, Colour.GREEN, Colour.WHITE);
        classUnderTest.setSequence(colourSequence);
        classUnderTest.run();

        verify(classUnderTest, times(colourSequence.size())).displayAllLights();
    }
}