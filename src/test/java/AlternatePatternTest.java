import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SequencePattern.class)
public class AlternatePatternTest {

    private List<Light> lights;

    @Mock
    private AlternatePattern classUnderTest;

    @Before
    public void setUp(){

        lights = Arrays.asList(new Light(0,Colour.RED));
        classUnderTest = new AlternatePattern(lights);
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void run() {
        Thread mockThread = mock(Thread.class);
        SequencePattern mockSequence = mock(SequencePattern.class);
        ColourPattern mockColourSequence = mock(ColourPattern.class);
        Iterator<LightSequence> mockIterator = mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(true,true, false);
        when(mockIterator.next()).thenReturn(mockSequence,mockColourSequence);


        doReturn(mockThread).when(classUnderTest).runSequenceInNewThread(any(LightSequence.class));
        doNothing().when(mockThread).start();
        doNothing().when(mockThread).interrupt();
        doCallRealMethod().when(classUnderTest).run();
        doCallRealMethod().when(classUnderTest).setFairylights(any());
        doCallRealMethod().when(classUnderTest).displayAllLights();
        doReturn(mockIterator).when(classUnderTest).makeSequenceListCyclic(any());

        classUnderTest.setFairylights(lights);
        classUnderTest.run();

        verify(classUnderTest, times(2)).runSequenceInNewThread(any(LightSequence.class));
        verify(classUnderTest, times(1)).runSequenceInNewThread(mockColourSequence);
        verify(classUnderTest, times(1)).runSequenceInNewThread(mockSequence);

    }


}