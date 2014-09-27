package com.eidoscode.util.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.naming.Context;

/**
 * Utility class that it purpose is to help the user to work with Streams.
 *
 * @author antonini
 * @since 1.1.0
 * @version 1.0
 */
public final class StreamUtils {

    /**
     * Default buffer size.
     */
    public final static int BUFFER_BYTES = 1024;
    private static final String DEFAULT_ENCODING = "UTF-8";

    private StreamUtils() {
    }

    /**
     * Close one or more {@link AutoCloseable} objects silently.
     *
     * @param closeables
     *            varargs of {@link AutoCloseable} objects.
     */
    public static void closeSilently(AutoCloseable... closeables) {
        if (closeables != null) {
            for (AutoCloseable closeable : closeables) {
                try {
                    if (closeable != null) {
                        closeable.close();
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Close one or more {@link Closeable} objects silently.
     *
     * @param closeables
     *            varargs of {@link Closeable} objects.
     */
    public static void closeSilently(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                try {
                    if (closeable != null) {
                        closeable.close();
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Close one or more {@link Context} objects silently.
     *
     * @param closeables
     *            varargs of {@link Context} objects.
     */
    public static void closeSilently(Context... closeables) {
        if (closeables != null) {
            for (Context closeable : closeables) {
                try {
                    if (closeable != null) {
                        closeable.close();
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Return the string content from an input stream.
     *
     * @param input
     *            Input stream.
     * @param encoding
     *            Encoding used to read the file.
     * @return Result string of the input.
     * @throws IOException
     *             Throws it if occur an error while reading the input stream.
     * @throws NullPointerException
     *             Throws if the input stream or encoding parameters are null.
     */
    public static String readContentFromInputStream(InputStream input, String encoding) throws IOException {
        if (input == null) {
            throw new NullPointerException("The input stream is mandatory to exists.");
        }
        if (encoding == null) {
            throw new NullPointerException("The encoding is mandatory to exists.");
        }
        String result = "";
        if (input != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(input, encoding));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                closeSilently(input);
            }
            result = writer.toString();
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Return the string content from an input stream. It uses the default
     * encoding to read the content of the Stream. That value can be check on
     * the variable {@link #DEFAULT_ENCODING} (Default value:
     * {@value #DEFAULT_ENCODING}).
     *
     * @param input
     *            Input stream.
     * @return Result string of the input.
     * @throws IOException
     *             Throws it if occur an error while reading the input stream.
     * @throws NullPointerException
     *             Throws if the input stream parameter is null.
     */
    public static String readContentFromInputStream(InputStream input) throws IOException {
        return readContentFromInputStream(input, DEFAULT_ENCODING);
    }

    /**
     * Copy the input Stream to the output stream. After the process is done it
     * closes the input and output stream.
     *
     * @param input
     *            Input stream.
     * @param output
     *            Output stream.
     * @param closeInput
     *            <code>true</code> if after the process is done it will close
     *            the input stream.
     * @param closeOutput
     *            <code>true</code> if after the process is done it will close
     *            the output stream.
     * @throws IOException
     *             Throws if an error occurs when reading or saving the file.
     * @throws NullPointerException
     *             Throws if the input or output stream parameters are null.
     */
    public static void copyStream(InputStream input, OutputStream output, boolean closeInput, boolean closeOutput) throws IOException {
        if (input == null) {
            throw new NullPointerException("The input stream is mandatory to exists.");
        }
        if (output == null) {
            throw new NullPointerException("The output stream is mandatory to exists.");
        }
        byte[] bytes = new byte[BUFFER_BYTES];
        int count;
        while ((count = input.read(bytes)) > 0) {
            output.write(bytes, 0, count);
            output.flush();
        }

        if (closeInput) {
            closeSilently(input);
        }
        if (closeOutput) {
            closeSilently(output);
        }
    }

    /**
     * Copy the input Stream to the output stream. After the process is done it
     * closes the input and output stream.
     *
     * @param input
     *            Input stream.
     * @param outputStream
     *            Output stream.
     * @throws IOException
     *             Throws if an error occurs when reading or saving the file.
     * @throws NullPointerException
     *             Throws if the input or output stream parameters are null.
     */
    public static void copyStream(InputStream input, OutputStream outputStream) throws IOException {
        copyStream(input, outputStream, true, true);
    }

    /**
     * Count the amount of bytes a given input stream contains.
     *
     * @param input
     *            Input stream.
     * @return The amount of bytes of the given input stream. If 0 means that is
     *         empty.
     * @throws IOException
     *             Throws it if occur an error while reading the input stream.
     * @throws NullPointerException
     *             Throws if the input stream parameter is null.
     */
    public static int countBytes(InputStream input) throws IOException {
        if (input == null) {
            throw new NullPointerException("The input stream is mandatory to exists.");
        }
        int amountBytes = 0;
        int count = 0;
        byte[] bytes = new byte[BUFFER_BYTES];
        while ((count = input.read(bytes)) > 0) {
            amountBytes += count;
        }
        return amountBytes;
    }
}
