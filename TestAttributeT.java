/**
 * Author: Mark Procopio - procopim 
 * Revised: March 29, 2021
 * 
 * Description: Test module applicable to AttrbuteT module.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;

public class TestAttributeT
{

    @Test
    public void testAttributeT()
    {
        AttributeT test_att = new AttributeT("Test-attribute",
        new IndicatorT[] {IndicatorT.math, IndicatorT.standards, IndicatorT.tools});

        assertEquals("Test-attribute", test_att.getName());
        IndicatorT[] indicators = test_att.getIndicators();
        boolean contains = Arrays.stream(indicators).anyMatch(IndicatorT.healthSafety::equals);
        assertFalse(contains);
        boolean contains2 = Arrays.stream(indicators).anyMatch(IndicatorT.math::equals);
        assertTrue(contains2);
    }

    @Test
    public void testBlankAttributeT(){
        AttributeT blank_att = new AttributeT("blank-att-Name", new IndicatorT[]{});

        assertEquals(new IndicatorT[]{}, blank_att.getIndicators());
    }
}

