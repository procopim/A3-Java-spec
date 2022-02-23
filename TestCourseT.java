/**
 * Author: Mark Procopio - procopim
 * Revised: March 29, 2021
 * 
 * Description: Test module applicable to CourseT module.
 */

package src;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.stream.*;
import java.util.*; 
import java.util.Arrays;

public class TestCourseT
{
    @Test
    public void testBlankCourseIndicators(){
        CourseT blank_course = new CourseT("blank_course_name", new IndicatorT[]{});
        assertEquals(new IndicatorT[]{}, blank_course.getIndicators());
    }

    @Test 
    public void testIndicators(){

        IndicatorT[] SE2AA4_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt};
        CourseT SE2AA4 = new CourseT("SE2AA4", SE2AA4_indicators);

        //Test membership within Indicators[]
        boolean contains = Arrays.stream(SE2AA4_indicators).anyMatch(IndicatorT.healthSafety::equals);
        assertFalse(contains);
        boolean contains2 = Arrays.stream(SE2AA4_indicators).anyMatch(IndicatorT.math::equals);
        assertTrue(contains2);

        //Test addLO for exisiting indicator 
        LOsT L01 = new LOsT("Be a good boy", 10,10,10,10);
        LOsT L02 = new LOsT("The greatest love were murderer's first", 5,5,5,5);
        SE2AA4.addLO(IndicatorT.math, L01);
        SE2AA4.addLO(IndicatorT.math, L02);

        LOsT[] LOs = SE2AA4.getLOs(IndicatorT.math); 
        LOsT[] expected = new LOsT[]{L01,L02};
        int counter = 0;
        for (LOsT LO : LOs){
            boolean contains3 = Arrays.stream(LOs).anyMatch(expected[counter]::equals);
            if (contains3 == true){
                counter++;
             }       
        }     
        assertEquals(expected.length, counter);

        //Test delLO for existing Indicators[] - length should now be 1
        SE2AA4.delLO(IndicatorT.math, L01);
        assertEquals(1, SE2AA4.getLOs(IndicatorT.math).length);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testForIllegalArgExc(){
        IndicatorT[] SE2AA4_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt};
        CourseT SE2AA4 = new CourseT("SE2AA4", SE2AA4_indicators);
        
        //test error thrown from addLo -- des.Princples is not an indicator in SE2AA4 
        LOsT outcome = new LOsT("Be a good boy", 10,10,10,10);
        SE2AA4.addLO(IndicatorT.desPrinciples, outcome);

    }

    @Test (expected=IllegalArgumentException.class)
    public void memberExc()
    {
        IndicatorT[] SE123_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt};
        CourseT SE123 = new CourseT("SE123", SE123_indicators);
        SE123.member(IndicatorT.desProcess, new LOsT[]{});
    }
}
