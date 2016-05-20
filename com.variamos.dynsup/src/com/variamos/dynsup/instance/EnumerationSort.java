package com.variamos.dynsup.instance;

import java.util.Comparator;

public class EnumerationSort implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		if (o1 instanceof InstAttribute && o2 instanceof InstAttribute) {
			Object oo1 = ((InstAttribute) o1).getValue();
			Object oo2 = ((InstAttribute) o2).getValue();
			if (oo1 instanceof String && oo2 instanceof String) {
				String s1 = (String) oo1;
				String s2 = (String) oo2;
				String[] ss1 = s1.split("#");
				String[] ss2 = s2.split("#");
				try {
					int i1 = Integer.parseInt(ss1[0]);
					int i2 = Integer.parseInt(ss2[0]);
					return (i1 - i2);
				} catch (Exception e) {
				}
				;
			}
		}
		return -1;
	}
}
