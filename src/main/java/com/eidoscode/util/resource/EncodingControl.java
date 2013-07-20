package com.eidoscode.util.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * The purpose of this class is to make possible to use the
 * {@link ResourceBundle} static methods to read properties files that are
 * written on any encoding. It's necessary to send it through parameter what
 * encoding you want to use.
 * 
 * @author eantonini
 * @since 1.0.3
 * @version 1.0
 */
public class EncodingControl extends java.util.ResourceBundle.Control {

	public static final String FORMAT_FILE = "java.properties";
	private static final String FILE_SUFFIX = "properties";

	final String encoding;

	/**
	 * Main constructor.
	 * 
	 * @param encoding
	 */
	public EncodingControl(String encoding) {
		super();
		this.encoding = encoding;
	}

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale,
			String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {

		if ((baseName == null) || (locale == null) || (format == null)
				|| (loader == null)) {
			throw new NullPointerException();
		}

		String bundleName = toBundleName(baseName, locale);
		ResourceBundle bundle = null;

		if (FORMAT_FILE.equals(format)) {
			final String resourceName = toResourceName(bundleName, FILE_SUFFIX);
			BufferedReader stream = null;
			try {
				PrivilegedExceptionAction<BufferedReader> privilegedExceptionAction = new BufferedReaderPrivilegedExceptionAction(
						reload, loader, resourceName, encoding);

				stream = AccessController
						.doPrivileged(privilegedExceptionAction);
			} catch (PrivilegedActionException e) {
				throw (IOException) e.getException();
			}
			if (stream != null) {
				try {
					bundle = new PropertyResourceBundle(stream);
				} finally {
					stream.close();
				}
			}
		} else {
			bundle = super.newBundle(baseName, locale, format, loader, reload);
		}
		return bundle;
	}
}
