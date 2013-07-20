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
 * @deprecated Use the {@link com.eidoscode.util.jaxrs.VariableStrings} instead
 *             of this one.
 * @author eantonini
 * @since 1.0.1
 * @version 1.0
 */
@Deprecated
public class VariableStrings extends com.eidoscode.util.jaxrs.VariableStrings {

	public VariableStrings(String unparsedPath) {
		super(unparsedPath);
	}

}
