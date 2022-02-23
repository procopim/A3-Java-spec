/**
 * Author:Mark Procopio - procopim
 */

package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AttributeT {

    String name;    // name of Attribute 
    HashSet<IndicatorT> s;  //set of Indicators applicable to Attribute 
 
    // AttributeT constructor 
    AttributeT(String attribName, IndicatorT[] indicators){
        name = attribName;
        s = convertArraytoSet(indicators);
    }

    private static HashSet<IndicatorT> convertArraytoSet(IndicatorT array[]){

        HashSet<IndicatorT> set = new HashSet<>();
        for (IndicatorT indicator : array){
            set.add(indicator);
        }
        return set;
    }
    
    public String getName(){
        return name;
    }

    public IndicatorT[] getIndicators(){
        IndicatorT[] out = new IndicatorT[s.size()];
        out = s.toArray(out);
        return out;
    }
}

