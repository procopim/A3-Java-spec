/**
 * Author: Mark Procopio -procopim
 */

package src;

import java.util.HashSet;

public class ProgramT extends HashSet<CourseT> implements Measures {

    
    ProgramT()
    {
        super();
    } 


    public double[] measures(){
        throw new UnsupportedOperationException("Attribute required");
    }

    public double[] measures(IndicatorT ind){
        throw new UnsupportedOperationException("Attribute required");
    }

    public double[] measures(AttributeT att){
        
        double[] unit_op = {0, 0, 0, 0};

        double[] reduction = new double[4]; 
        int counter = 0;
        for (CourseT course : this){
            if (counter == 0){
                reduction = CourseT.sumMeas(unit_op, course.measures(att));
                counter++;
            }
            else{
                reduction = CourseT.sumMeas(reduction, course.measures(att));
            }
        }

        return Services.normal(reduction);
    }
}