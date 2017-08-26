package com.variamos.common.core.utilities;

import java.util.Comparator;
import java.util.List;

public class CollectionsSizeComparator  implements Comparator  {

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
