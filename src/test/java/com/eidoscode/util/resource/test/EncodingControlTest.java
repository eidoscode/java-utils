package com.eidoscode.util.resource.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import com.eidoscode.util.resource.EncodingControl;

/**
 * Unit test of the {@link EncodingControl}.
 * 
 * @author eantonini
 * @since 1.0.3
 * @version 1.0
 * 
 */
public class EncodingControlTest {

	private static final Locale US = Locale.US;
	private static final Locale BRAZIL = new Locale("pt", "BR");
	private static final EncodingControl ENCODING_UTF8 = genEncoding("UTF-8");
	private static final EncodingControl ENCODING_ISO88591 = genEncoding("ISO-8859-1");

	private static final String BUNDLE_FILE_UTF8 = "messages_utf8";
	private static final String BUNDLE_FILE_ISO88591 = "messages_iso88591";

	private static final String BUNDLE_KEY = "testMessage";

	private static final String SPECTED_STRING = "Message with special char like 'ç', 'ã', 'õ' and 'ï',";

	@Test
	public void testEncoding() {
		checkBundle(BUNDLE_FILE_UTF8, US, ENCODING_UTF8, BUNDLE_KEY);
		checkBundle(BUNDLE_FILE_ISO88591, BRAZIL, ENCODING_ISO88591, BUNDLE_KEY);
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer1() throws IllegalAccessException,
			InstantiationException, IOException {
		ENCODING_UTF8.newBundle(null, US, "format", Thread.currentThread()
				.getContextClassLoader(), true);
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer2() throws IllegalAccessException,
			InstantiationException, IOException {
		ENCODING_UTF8.newBundle("base", null, "format", Thread.currentThread()
				.getContextClassLoader(), true);
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer3() throws IllegalAccessException,
			InstantiationException, IOException {
		ENCODING_UTF8.newBundle("base", US, null, Thread.currentThread()
				.getContextClassLoader(), true);
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer4() throws IllegalAccessException,
			InstantiationException, IOException {
		ENCODING_UTF8.newBundle("base", US, "format", null, true);
	}

	private void checkBundle(String bundleFile, Locale locale,
			EncodingControl control, String bundleKey) {
		ResourceBundle labels = ResourceBundle.getBundle(bundleFile, locale,
				control);
		String result = labels.getString(bundleKey);

		assertEquals(SPECTED_STRING, result);
		System.out.println(result);
	}

	private static EncodingControl genEncoding(String encoding) {
		return new EncodingControl(encoding);
	}

}
