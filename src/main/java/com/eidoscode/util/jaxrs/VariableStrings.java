package com.eidoscode.util.jaxrs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class designed to be used with the JAX-RS API. The purpose of it is
 * to make it possible to expose an JAX-RS Path that receives an varargs
 * argument.<br/>
 * What you need to do is to overload the varargs method an use this class as it
 * would be the varargs parameter. Then use the
 * {@link VariableStrings#getSplitPath()} to send the value to the original
 * method.
 * 
 * @author eantonini
 * @since 1.0.1
 * @version 1.0
 */
public class VariableStrings {

	private final List<String> splitPath;

	public VariableStrings(String unparsedPath) {
		if (unparsedPath == null) {
			throw new NullPointerException("The parameter is mandatory.");
		}
		String[] paths = unparsedPath.split("/");
		splitPath = Collections.unmodifiableList(Arrays.asList(paths));
	}

	/**
	 * Returns an String array split by the slash ("/").
	 * 
	 * @return
	 * @deprecated Use instead of this the method {@link #getSplitPaths()}
	 */
	@Deprecated
	public String[] getSplitPath() {
		return splitPath.toArray(new String[splitPath.size()]);
	}

	/**
	 * Returns the List of string split by the slash ("/").
	 * 
	 * @return
	 */
	public List<String> getSplitPaths() {
		return splitPath;
	}

}
