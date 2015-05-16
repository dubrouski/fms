package net.dubrouski.fams.util;

import java.util.Comparator;
import java.util.List;

import net.dubrouski.fams.model.AccommodationUnit;

public class AccommodationTypeComparator implements Comparator<String>{
	
	List<String> definedOrder = AccommodationUnit.getTypesList();
			
	@Override
	public int compare(String o1, String o2) {
		return Integer.valueOf(definedOrder.indexOf(o1))
				.compareTo(Integer.valueOf(definedOrder.indexOf(o2)));
	}
	
	
}
