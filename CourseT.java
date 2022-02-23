/**
 * Author: Mark Procopio 
 * Revised: March 29, 2021
 * Description: CourseT module that provides Abstract Data Type 'CourseT'. 
 * This module ties Indicators (IndicatorT) and their respective Learning Outcomes (LOsT)
 * to a CourseT object, which reflects a course in the Program of study. 
 */

package src;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @brief an ADT that represents a Course in the Program of study. 
 * @details CourseT implements the Measures interface.
 */
public class CourseT implements Measures{

    String name;
    HashMap<IndicatorT, HashSet<LOsT>> m;
    /**
     * @brief Initializes a Course object. 
     * @param courseName String representation of the Course name. 
     * @param indicators Array of Indicator objects partial to the Course. 
     */
    CourseT(String courseName, IndicatorT[] indicators){
        this.name = courseName;
        this.m = new HashMap<IndicatorT, HashSet<LOsT>>();

        HashSet<LOsT> initial = new HashSet<LOsT>();
        for (IndicatorT indicator : indicators){
            m.put(indicator, initial);
        }
    }
    /**
     * @brief Getter method for name of Course.
     * @return String representation of Course name.
     */
    public String getName(){
        return this.name;
    }
    /**
     * @brief Getter method for Course Indicators.
     * @return Array of Indicator objects partial to a given Course.
     */
    public IndicatorT[] getIndicators(){
        IndicatorT[] out = new IndicatorT[this.m.size()];

        for (IndicatorT indicator : m.keySet()){
            int counter = 0;
            out[counter] = indicator;
            counter++;
        }
        return out;
    }
    /**
     * @brief Getter method for Learning Objectives.
     * @details Learning Objectives are tied to an Indicator, which are tied to a Course.
     * @return Array of Learning Objectives. 
     */
    public LOsT[] getLOs(IndicatorT indicator){
        HashSet<LOsT> res = this.m.get(indicator);
        LOsT[] EMPTY = {};
        if (res == null){
            return EMPTY; 
        }
        else {
            LOsT[] LOs = new LOsT[res.size()];
            LOs = res.toArray(LOs);
            return LOs;
        }
    }
    /**
     * @brief Adds a Learning Objective to an Indicator.
     * @param indicator IndicatorT object for a Course.
     * @param outcome Learning Objective object for an Indicator. 
     */
    public void addLO(IndicatorT indicator, LOsT outcome){
        if (this.m.get(indicator) == null){
            throw new IllegalArgumentException("Invalid indicator");
        }
        HashSet<LOsT> vals = this.m.get(indicator);
        vals.add(outcome);
        this.m.put(indicator, vals);
    }
    /**
     * @brief Deletes a Learning Objective from an Indicator 
     * @param indicator IndicatorT object for a Course.
     * @param outcome Learning Objective object for an Indicator. 
     */
    public void delLO(IndicatorT indicator, LOsT outcome){
        if (this.m.get(indicator) == null){
            throw new IllegalArgumentException("Invalid indicator");
        }
        HashSet<LOsT> vals = this.m.get(indicator);
        vals.remove(outcome);
        this.m.remove(indicator);
        this.m.put(indicator, vals);
    }
    /**
     * @brief Membership method
     * @details Tests membership of an Indicator and its respective array of Learning Objectives.
     * @param indicator IndicatorT object for a Course.
     * @param outcomes Array of Learning Objectives. 
     * @return boolean value reflecting membership of parameters.
     */
    public boolean member(IndicatorT indicator, LOsT[] outcomes){
        if (this.m.get(indicator) == null){
            throw new IllegalArgumentException("Invalid indicator");
        }
        else{
            HashSet<LOsT> container = this.m.get(indicator);
            int counter = 0;
            for (LOsT LO : outcomes){
                if ((container.contains(LO)) == true){
                    counter++;
                }
            }
            return counter == outcomes.length;
        }
    }
    /**
     * @brief Measures Interface implementation. 
     */
    public double[] measures(){
        throw new UnsupportedOperationException("must pass in Indicator or Attribute");
    }
    /**
     * @brief Measures Interface implementation.
     * @details provides interface that handles IndicatorT objects. 
     * @return Array reflecting reduced Learning Objective measures.
     */
    public double[] measures(IndicatorT ind){
        double[] unit_op = {0, 0, 0, 0};
        if (this.m.get(ind) == null){
            return unit_op;
        }
        HashSet<LOsT> container = this.m.get(ind);
        double[] reduction = new double[4];
        int counter = 0;
        for (LOsT LO : container){
            if (counter == 0){
                reduction = sumMeas(unit_op, LO.measures());
                counter++;
            }
            else{
                reduction = sumMeas(reduction, LO.measures());
            }
        }

        if ((Norm.getNInd()) == true){            
            return Services.normal(reduction);
        }
        else{
            return reduction; 
        }
    }
    /**
     * @brief Measures Interface implementation.
     * @details provides interface that handles AttributeT objects. 
     * @return Array reflecting reduced Learning Objective measures.
     */
    public double[] measures(AttributeT att){
        double[] unit_op = {0, 0, 0, 0};
        if (att.getIndicators() == null){
            return unit_op;
        }
        IndicatorT[] container = att.getIndicators();
        double[] reduction = new double[4];
        double[] res;
        int counter = 0;
        for (IndicatorT ind : container){
            if (counter == 0){
                res = sumMeas(unit_op, measures(ind));
                for (int i = 0; i < 4; i++){
                    reduction[i] = res[i];
                }
                counter++;
            }
            else{
                res = sumMeas(reduction, measures(ind));
                for (int i = 0; i < 4; i++){
                    reduction[i] = res[i];
                }
            }
        }
        if ((Norm.getNAtt()) == true){            
            return Services.normal(reduction);
        }
        else{
            return reduction; 
        }
    }

    public static double[] sumMeas(double[] a, double[] b){
        double[] out = new double[4];
        for (int i = 0; i < 4; i++){
            out[i] = (a[i] + b[i]);
        }
        return out;
      }
}

