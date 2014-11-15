package com.variamos.defectAnalyzer.util;

import java.util.Comparator;
import java.util.List;

public class CollectionsSizeComparator  implements Comparator<List>  {

	@Override
	public int compare(List o1, List o2) {
		
		if ( o1.size() > o1.size()) {
            return 1;
        } else if (o1.size() < o2.size()) {
            return -1;
        } else {
            return 0;
        }
	}

}
