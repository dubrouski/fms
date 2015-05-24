package net.dubrouski.fams.model.enums;

/**
 * Created by tmarton on 4/12/15.
 */
public enum MeterType {
    Gas("Gas")
    , Cold_Water("Cold_water")
    , Hot_Water("Hot_water")
    , Heating("Heating")
    , Electricity("Electricity");
    
    private String label;
    
    private MeterType(String label){
    	this.label = label;
    }
    
    public String getLabel(){
    	return label;
    }
}
