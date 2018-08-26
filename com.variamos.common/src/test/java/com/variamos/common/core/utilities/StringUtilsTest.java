package com.variamos.common.core.utilities;

import org.junit.Assert;
import org.junit.Test;

import com.variamos.common.core.utilities.StringUtils;
/**
 * Verifies functionality of StringUtils class
 * @author Esteban Echavarria - Monitor Investigativo - Eafit 2018. 
 */
public class StringUtilsTest{
    @Test
/**
 * Verifies formatEnumValue method
 */
    public void formatEnumValue(){
    String text = "  aBc_tEst ";
    String expected = "aBc test"; 

    String result = StringUtils.formatEnumValue(text);
    Assert.assertFalse(!result.equals(expected));

    text = "   METHOD ___tEst    ";
    expected = "METHOD    test";

    result = StringUtils.formatEnumValue(text);
    Assert.assertTrue(result.equals(expected));
}

@Test
/**
 * Verifies multiLine method
 */
    public void multiLine(){
    String text = "Probando el metodo";
    String expected ="Probando\nel metodo";
    int length = 6;

    String result = StringUtils.multiLine(text,length);
    
    Assert.assertTrue(result.equals(expected));
}
}