/**
 * Author: Mark Procopio - procopim
 * Revised: March 29, 2021
 * 
 * Description: Test module applicable to ProgramT module.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestProgramT
{
	
	@Test
    public void testBlank()
    {
        assertTrue(true);
    }

    @Test 
    public void testProgramT()
    {
        //test overall functionality 
        AttributeT Design = new AttributeT("Design", new IndicatorT[] {IndicatorT.openEnded, IndicatorT.ideaGeneration,
                                                                        IndicatorT.healthSafety, IndicatorT.standards});

        IndicatorT[] PHYS101_indicators = new IndicatorT[] {IndicatorT.standards, IndicatorT.healthSafety};
        CourseT PHYS101 = new CourseT("PHYS101", PHYS101_indicators);
        PHYS101.addLO(IndicatorT.standards, new LOsT("You know, the thing", 10, 10, 10, 10));
        PHYS101.addLO(IndicatorT.healthSafety, new LOsT("Wear a mask while sleeping", 10, 10, 10, 10));
        
        ProgramT Scientist = new ProgramT();
        Scientist.add(PHYS101); 
        double[] meas = Scientist.measures(Design);

        boolean flag = true; 
        for (double num : meas){
            if (!(num == 0.25)){
                flag = false; 
            }
        }
        assertTrue(flag);

        //test that zero measures are returned for an Attribute that doesnt apply to the program 
        AttributeT Specs = new AttributeT("Design", new IndicatorT[] {IndicatorT.openEnded, IndicatorT.ideaGeneration});
        double[] meas2 = Scientist.measures(Specs);

        boolean flag2 = true; 
        for (double num : meas2){
            if (!(num == 0.0)){
                flag2 = false; 
            }
        }
        assertTrue(flag2);
    }

}
