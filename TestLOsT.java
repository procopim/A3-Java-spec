/**
 * Author: Mark Procopio  - procopim
 * Revised: March 29, 2021
 * 
 * Description: Test module applicable to LOsT module.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestLOsT
{

    @Test
    public void testBlank()
    {
        assertTrue(true);
    }

    @Test (expected=IllegalArgumentException.class)
    public void ConstructorEx1(){
        LOsT ex1 = new LOsT("ex1", -1,1,1,1);
    }    
    @Test (expected=IllegalArgumentException.class)
    public void ConstructorEx2(){
        LOsT ex2 = new LOsT("ex2", 0,0,0,0);
    }
    @Test 
    public void testMeasures(){
        Norm.setNorms(false, false, false);
        LOsT testMeas = new LOsT("ex2", 10,10,10,10);
        double[] res = testMeas.measures();
        
        boolean flag = true; 
        for (double num : res){
            if (!(num == 10)){
                flag = false; 
            }
        }
        assertTrue(flag);
    }   
}
