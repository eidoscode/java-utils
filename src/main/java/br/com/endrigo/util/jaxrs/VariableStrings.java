package br.com.endrigo.util.jaxrs;

/**
 * Utility class designed to be used with the JAX-RS API. The purpose of it is
 * to make it possible to expose an JAX-RS Path that receives an varargs
 * argument.<br/>
 * What you need to do is to overload the varargs method an use this class as it
 * would be the varargs parameter. Then use the
 * {@link VariableStrings#getSplitPath()} to send the value to the original
 * method.
 * 
 * @author antonini
 * @since 1.0.1
 * @version 1.0
 */
public class VariableStrings {

	private final String[] splitPath;

	public VariableStrings(String unparsedPath) {
		if (unparsedPath == null) {
			throw new NullPointerException("The parameter is mandatory.");
		}
		splitPath = unparsedPath.split("/");
	}

	/**
	 * Returns the String split by the slash ("/").
	 * @return
	 */
	public String[] getSplitPath() {
		return splitPath;
	}

}
