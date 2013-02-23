package br.com.endrigo.util.jaxrs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class VariableStringsTest {
	
	@Test
	public void testSplitPath() {
		String sPaths = "param1/param2";
		VariableStrings vars = new VariableStrings(sPaths);
		String[] paths = vars.getSplitPath();
		
		assertNotNull(paths);
		assertEquals(2, paths.length);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSplitNull() {
		String sPaths = null;
		VariableStrings vars = new VariableStrings(sPaths);
	}
	
}
