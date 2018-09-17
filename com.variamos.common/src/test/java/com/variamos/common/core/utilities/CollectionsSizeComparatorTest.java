package com.variamos.common.core.utilities;

import java.util.ArrayList;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;


import com.variamos.common.core.utilities.CollectionsSizeComparator;
/**
 * Verifies functionality of CollectionsSizeComparatorTest class
 * @author Esteban Echavarría - Monitor Investigativo - Eafit 2018. 
 */
public class CollectionsSizeComparatorTest{
    @Test
/**
 * Verifies compare method
 */
    public void compare(){
    List<String> set1 = new ArrayList<String>();
    set1.add("A");
    set1.add("A");
    set1.add("A");
    set1.add("A");

    List<String> set2 = new ArrayList<String>();
    set2.add("B");
    set2.add("B");
    set2.add("B");
    set2.add("B");

    CollectionsSizeComparator obj = new CollectionsSizeComparator();
    int comparingResult = obj.compare(set1, set2);
    //0 cause set1.size() == set2.size()
    Assert.assertFalse(comparingResult != 0);

    set1.add("A");

    comparingResult = obj.compare(set1, set2);
    //1 Cause set1.size() > set2.size()
    Assert.assertFalse(comparingResult != 1);
    
    set2.add("A");
    set2.add("A");

    Assert.assertTrue(comparingResult != -1);
}
}