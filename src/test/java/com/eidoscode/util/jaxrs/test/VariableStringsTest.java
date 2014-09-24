package com.eidoscode.util.jaxrs.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.eidoscode.util.jaxrs.VariableStrings;

public class VariableStringsTest {

    @Test
    public void testSplitPaths() {
        String sPaths = "param1/param2";
        VariableStrings vars = new VariableStrings(sPaths);
        List<String> paths = vars.getSplitPaths();

        assertNotNull(paths);
        assertEquals(2, paths.size());
    }

    @Test(expected = NullPointerException.class)
    public void testSplitNull() {
        String sPaths = null;
        new VariableStrings(sPaths);
    }

}
