package br.com.endrigo.util.resource;

import java.util.ResourceBundle;

/**
 * The purpose of this class is to make possible to use the
 * {@link ResourceBundle} static methods to read properties files that are
 * written on any encoding. It's necessary to send it through parameter what
 * encoding you want to use.
 * 
 * @deprecated Use the {@link com.eidoscode.util.resource.EncodingControl}
 *             instead of this one.
 * @author eantonini
 * @since 1.0.3
 * @version 1.0
 */
@Deprecated
public class EncodingControl extends
		com.eidoscode.util.resource.EncodingControl {

	/**
	 * Main constructor.
	 * 
	 * @param encoding
	 */
	public EncodingControl(String encoding) {
		super(encoding);
	}

}
