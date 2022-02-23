/**
 * Author:Mark Procopio - procopim
 */

package src;

public class LOsT implements Measures{

    String name;
    int n_blw;
    int n_mrg;
    int n_mts;
    int n_exc; 

    LOsT(String topic, int nblw, int nmrg, int nmts, int nexc){
        
        if (nblw < 0 || nmrg < 0 || nmts < 0 || nexc < 0){
            throw new IllegalArgumentException("arguments must be greater than 0");
        }
        if (nblw == 0 & nmrg == 0 & nmts == 0 & nexc == 0){
            throw new IllegalArgumentException("all arguments cannot simultaneously be equal to zero");
        }
        this.name = topic;
        this.n_blw = nblw;
        this.n_mrg = nmrg;
        this.n_mts = nmts;
        this.n_exc = nexc; 
    }

    public String getName(){
        return this.name;
    }

    public boolean equals(LOsT o){
        return this.name == o.getName();
    }

    public double[] measures(){
        double[] out = new double[4];
        out[0] = n_blw;
        out[1] = n_mrg;
        out[2] = n_mts;
        out[3] = n_exc;
        if (Norm.getNLOs() == false){
            return out;
        }
        else {
            return Services.normal(out);
        }
    }

    public double[] measures(IndicatorT ind){
        throw new UnsupportedOperationException("only applicable to arrays");
    }

    public double[] measures(AttributeT att){
        throw new UnsupportedOperationException("only applicable to arrays");
    }
}