package br.com.endrigo.util.jaxrs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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
	@Test
	public void testSplitPaths() {
		String sPaths = "param1/param2";
		VariableStrings vars = new VariableStrings(sPaths);
		List<String> paths = vars.getSplitPaths();
		
		assertNotNull(paths);
		assertEquals(2, paths.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testSplitNull() {
		String sPaths = null;
		new VariableStrings(sPaths);
	}
	
	@Test
	public void testSplitBlank() {
		String sPaths = "";
		VariableStrings vars = new VariableStrings(sPaths);
		String[] retVars = vars.getSplitPath();
		List<String> values = vars.getSplitPaths();
		
		assertEquals(1, retVars.length);
		assertEquals(1, values.size());
	}
	
}
