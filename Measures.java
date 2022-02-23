/**
 * Author:Mark Procopio - procopim
 */

package src;

public interface Measures {

        // all should output array of Reals 

    public abstract double[] measures()throws UnsupportedOperationException;

    public abstract double[] measures(IndicatorT ind)throws UnsupportedOperationException;

    public abstract double[] measures(AttributeT att)throws UnsupportedOperationException;

} 

