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

    private StreamUtils() {
    }

    /**
     * Close one or more {@link AutoCloseable} objects silently.
     *
     * @param closeables
     *            var args of {@link AutoCloseable} objects.
     */
    public static void closeSilently(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Close one or more {@link Closeable} objects silently.
     *
     * @param closeables
     *            var args of {@link Closeable} objects.
     */
    public static void closeSilently(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Close one or more {@link Context} objects silently.
     *
     * @param closeables
     *            var args of {@link Context} objects.
     */
    public static void closeSilently(Context... closeables) {
        for (Context closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Return the Content from input stream to String
     *
     * @param inputStream
     *            input stream
     * @return String of content
     * @throws IOException
     */
    public static String readContentFromInputStream(InputStream inputStream) throws IOException {
        String result = "";
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                closeSilently(inputStream);
            }
            result = writer.toString();
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Copy the Stream and log the progress.
     *
     * @param input
     *            Stream to be readed.
     * @param outputStream
     *            Stream to be writed.
     * @throws IOException
     *             if an error occurs when saving the file.
     */
    public static void copyStream(InputStream input, OutputStream outputStream, int totalBytes) throws IOException {

        byte[] bytes = new byte[BUFFER_BYTES];
        int count;
        // double countSaved = 0;
        while ((count = input.read(bytes)) > 0) {
            // double percent = countSaved * 100;
            // double value = percent / totalBytes;
            // countSaved += count;
            outputStream.write(bytes, 0, count);
        }

        closeSilently(outputStream, input);
    }

    /**
     * Copy the Stream.
     *
     * @param input
     *            Stream to be readed.
     * @param outputStream
     *            Stream to be writed.
     * @throws IOException
     *             if an error occurs when saving the file.
     */
    public static void copyStream(InputStream input, OutputStream outputStream, boolean closeInput, boolean closeOutput) throws IOException {
        byte[] bytes = new byte[BUFFER_BYTES];
        int count;
        while ((count = input.read(bytes)) > 0) {
            outputStream.write(bytes, 0, count);
            outputStream.flush();
        }

        if (closeInput) {
            closeSilently(input);
        }
        if (closeOutput) {
            closeSilently(outputStream);
        }
    }

    public static void copyStream(InputStream input, OutputStream outputStream) throws IOException {
        copyStream(input, outputStream, true, true);
    }

    /**
     * return the byte count for a given inputstream.
     *
     * @param input
     *            {@link InputStream}.
     * @return int number 0 if is empty.
     * @throws IOException
     *             if an error occurs when saving the file.
     */
    public static int countBytes(InputStream input) throws IOException {
        int totalCount = 0;
        int count = 0;
        byte[] bytes = new byte[BUFFER_BYTES];
        while ((count = input.read(bytes)) > 0) {
            totalCount += count;
        }
        return totalCount;
    }

}
