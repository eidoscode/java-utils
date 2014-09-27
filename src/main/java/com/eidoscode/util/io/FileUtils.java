package com.eidoscode.util.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import org.apache.log4j.Logger;

/**
 * Utility class with the purpose to group a lot of functionalities to use with
 * files. Such as copy, move or save a content on a given file.
 * 
 * @author antonini
 * @since 1.1.0
 * @version 1.0
 *
 */
public final class FileUtils {

    private static final Logger LOG = Logger.getLogger(FileUtils.class);

    private FileUtils() {
    }

    /**
     * It moves a file from a origin to a destination. It will not try to
     * overwrite the destination file if it already exists.
     *
     * @param origin
     *            Origin file.
     * @param destination
     *            Destination file.
     * @return <code>true</code> if it was moved.
     * @throws IOException
     *             Throws if the destination file already exists and it was not
     *             selected to overwrite it.
     * @throws NullPointerException
     *             Throws if the origin of the destination parameter is null.
     */
    public static boolean moveFile(File origin, File destination) throws IOException {
        return moveFile(origin, destination, false);
    }

    /**
     * It moves a file from a origin to a destination. It's possible to
     * overwrite a current file.
     *
     * @param origin
     *            Origin file.
     * @param destination
     *            Destination file.
     * @param overwrite
     *            <code>true</code> if you want to overwrite the destination
     *            file.
     * @return <code>true</code> if it was moved. Otherwise <code>false</code>.
     * @throws IOException
     *             Throws if the destination file already exists and it was not
     *             selected to overwrite it.
     * @throws NullPointerException
     *             Throws if the origin of the destination parameter is null.
     */
    public static boolean moveFile(File origin, File destination, boolean overwrite) throws IOException {
        if (origin == null || !origin.exists()) {
            throw new NullPointerException("The origin file is mandatory to exists.");
        }
        if (destination == null) {
            throw new NullPointerException("The destination file is mandatory.");
        }

        if (destination.exists() && !overwrite) {
            throw new IOException("The destination file already exists and it was chosed to not overwrite it.");
        }

        if (destination.exists()) {
            boolean deleted = destination.delete();
            LOG.debug("Deleted the destination file? " + deleted);
        }

        boolean moved = origin.renameTo(destination);
        LOG.debug("Moved the file \"" + origin + "\" to \"" + destination + "\" using Java API? " + moved);

        if (!moved) {
            LOG.debug("As it was not possible to move it by the JAVA native API it will try to copy the file and delete the original file.");
            copyFile(origin, destination);
            boolean deleted = origin.delete();
            LOG.debug("File copied. Origin filed was deleted? " + deleted);
        }

        moved = destination.exists();
        LOG.debug("Moved the file \"" + origin + "\" to \"" + destination + "\"? " + moved + " (method return)");
        return moved;
    }

    /**
     * This function read a File and returns its byte[];
     *
     * @param fileName
     *            the name of the file
     * @return byte array of file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] readFileToByteArray(File fileName) throws FileNotFoundException, IOException {
        byte[] byteArray = null;

        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        byteArray = new byte[(int) raf.length()];
        raf.read(byteArray);
        StreamUtils.closeSilently(raf);

        return byteArray;
    }

    /**
     * this method removes a directory with or without files, recursively.
     *
     * @param file
     *            the directory to delete.
     */
    public static void removeDirectory(File file) throws IOException {
        if (file.isDirectory()) {
            // validate if directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
            } else {
                // list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    // create file to delete
                    File fileDelete = new File(file, temp);

                    // recursive delete
                    removeDirectory(fileDelete);
                }

                // if directory is empty, delete
                if (file.list().length == 0) {
                    file.delete();
                }
            }

        } else {
            // if it's a file, delete
            file.delete();
        }
    }

    /**
     * Copy a file to other file.
     *
     * @param input
     *            File that is going to be readed.
     * @param output
     *            File that is going to be writed.
     * @throws IOException
     *             if an error occurs when saving the file.
     */
    public static void copyFile(File input, File output) throws IOException {
        FileInputStream fis = new FileInputStream(input);
        FileOutputStream fos = new FileOutputStream(output);
        StreamUtils.copyStream(fis, fos);

        StreamUtils.closeSilently(fis, fos);
    }

    /**
     * It saves the file in the given directory and file It closes both
     * connections (InputStream and Outputstream)
     *
     * @param input
     *            stream to be saved.
     * @param file
     *            file to be persisted.
     * @throws IOException
     *             if an error occurs when saving the file.
     */
    public static File saveFile(InputStream input, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        StreamUtils.copyStream(input, outputStream);
        return file;

    }

    /**
     * Save a given content into a given file name
     *
     * @param content
     *            String of content to save
     * @param fileName
     *            name of file
     * @throws IOException
     *             if an error occur
     */
    public static void saveContentIntoFile(String content, File fileName) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
        } finally {
            if (writer != null) {
                StreamUtils.closeSilently(writer);
            }
        }
    }

    public static void saveContentIntoFile(byte[] content, File fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(content);
        StreamUtils.closeSilently(fos);
    }

}
