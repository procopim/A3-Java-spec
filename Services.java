/**
 * Author: Mark Procopio - procopim
 */

package src;

public class Services{

    private static double sum(double[] v){
        double total = 0;
        for (int i = 0; i < v.length; i++){
            total += v[i];
        }
        return total;
    }

    public static double[] normal(double[] v){

        double sumOfv = sum(v);
        double[] norm = new double[v.length]; 
        for (int i = 0; i < v.length; i++){
            if (v[i] == 0){
                norm[i] = 0;
            }
            else{
                norm[i] = v[i]/sumOfv; 
            }
        }
        return norm;
    }
}

