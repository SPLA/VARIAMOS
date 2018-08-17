package com.variamos.common.core.utilities;

import java.util.Comparator;
import java.util.List;

/**
 * Overrides CollectionsSizeComparator
 */
public class CollectionsSizeComparator  implements Comparator  { 
    
    //FLAG what is it supposed to do?
    /**
     * Verifies the size of two objects, returns -1 if obj1 is smaller than obj2. 
     * returns 1 (always?) if obj1 is bigger than obj1. 
     * @param o1 
     * @param o2
     * @return 1 if (obj1 > obj1), -1 if(obj1 < obj2) otherwise 0
     */
    @Override
	public int compare(Object o1, Object o2) {
		
		if (((List) o1).size() > ((List) o1).size()) {
            return 1;
        } else if (((List) o1).size() < (((List) o2)).size()) {
            return -1;
        } else {
            return 0;
        }
	}

}
