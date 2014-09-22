package com.eidoscode.util.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.PrivilegedExceptionAction;

/**
 * 
 * The purpose of this class is to be a helper class that is going to be used
 * with privileges. This is going to be used with the {@link EncodingControl}.
 * 
 * @author antonini
 * @since 1.0.3
 * @version 1.0
 * 
 */
class BufferedReaderPrivilegedExceptionAction implements
		PrivilegedExceptionAction<BufferedReader> {

	protected final boolean reloadFlag;
	protected final ClassLoader classLoader;
	protected final String resourceName;
	protected final String encoding;

	/**
	 * Main constructor.
	 * 
	 * @param reloadFlag
	 *            If the Bundle had been reloaded.
	 * @param classLoader
	 *            Class loader.
	 * @param resourceName
	 *            Resource name.
	 * @param encoding
	 *            Encoding.
	 */
	public BufferedReaderPrivilegedExceptionAction(boolean reloadFlag,
			ClassLoader classLoader, String resourceName, String encoding) {
		super();
		this.reloadFlag = reloadFlag;
		this.classLoader = classLoader;
		this.resourceName = resourceName;
		this.encoding = encoding;
	}

	/**
	 * Run the class and returns the {@link BufferedReader} or null.
	 */
	@Override
	public BufferedReader run() throws IOException {
		BufferedReader br = null;
		InputStream is = null;
		if (reloadFlag) {
			URL url = classLoader.getResource(resourceName);
			if (url != null) {
				URLConnection connection = url.openConnection();
				if (connection != null) {
					connection.setUseCaches(false);
					is = connection.getInputStream();
				}
			}
		} else {
			is = classLoader.getResourceAsStream(resourceName);
		}

		if (is != null) {
			br = new BufferedReader(new InputStreamReader(is, encoding));
		}
		return br;
	}
}
