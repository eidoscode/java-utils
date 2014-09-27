package com.eidoscode.util.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * Utility class with functions to work with compacted files.
 * 
 * @author antonini
 * @since 1.1.0
 * @version 1.0
 * 
 */
public final class CompressUtils {

  private static final Logger LOG = Logger.getLogger(CompressUtils.class);

  /**
   * Hide the constructor.
   */
  private CompressUtils() {
  }

  /**
   * It extract all files from a ZIP File to destiny path. <br/>
   * Prefer to use the method
   * 
   * @param zippedFile
   *          zipped file.
   * @param fileDestiny
   *          path where the files are saved.
   * @throws IOException
   *           when an error occurs.
   */
  public static void extractZipFile(File zippedFile, File fileDestiny) throws IOException {
    InputStream input = null;
    input = new FileInputStream(zippedFile);
    extractZipFile(input, fileDestiny);
  }

  /**
   * It extract all files from a ZIP File to destiny path.
   * 
   * @param input
   *          InputStream of the Zip file.
   * @param destiny
   *          path where the files are saved.
   * @throws IOException
   *           when an error occurs.
   */
  public static void extractZipFile(InputStream input, File destiny) throws IOException {
    ZipEntry entry = null;
    ZipInputStream zinputStream = null;

    zinputStream = new ZipInputStream(new BufferedInputStream(input));

    while ((entry = zinputStream.getNextEntry()) != null) {

      if (entry.isDirectory()) {
        String currentEntry = entry.getName();
        File destFile = new File(destiny, currentEntry);
        destFile.mkdirs();

      } else {
        String currentEntry = entry.getName();

        File destFile = new File(destiny, currentEntry);
        File destinationParent = destFile.getParentFile();

        // create the parent directory structure if needed
        destinationParent.mkdirs();

        LOG.debug("Extracting: " + entry);

        int count;
        byte data[] = new byte[StreamUtils.BUFFER_BYTES];
        FileOutputStream fos = new FileOutputStream(destFile);
        BufferedOutputStream dest = new BufferedOutputStream(fos, StreamUtils.BUFFER_BYTES);
        while ((count = zinputStream.read(data, 0, StreamUtils.BUFFER_BYTES)) != -1) {
          dest.write(data, 0, count);
        }
        dest.flush();
        StreamUtils.closeSilently(dest);
      }
    }
    StreamUtils.closeSilently(zinputStream);
  }

  public static File toZip(final File zipFile, com.eidoscode.util.io.zip.ZipEntry... files) throws IOException {
    int cont;
    byte[] data = new byte[StreamUtils.BUFFER_BYTES];

    if (zipFile.exists()) {
      zipFile.delete();
    }

    final File tempReadingFile = File.createTempFile("tmp-", "-" + zipFile.getName());

    if (tempReadingFile.delete()) {
      BufferedInputStream source = null;
      FileInputStream streamSource = null;
      ZipEntry entry = null;

      final File tempWritingFile = File.createTempFile("tmp-", ".w.dat");
      tempWritingFile.delete();

      ZipOutputStream append = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tempWritingFile)));

      if (zipFile.exists()) {

        FileUtils.moveFile(zipFile, tempReadingFile, true);

        ZipFile zip = new ZipFile(tempReadingFile);

        Enumeration<? extends ZipEntry> entries = zip.entries();
        while (entries.hasMoreElements()) {
          ZipEntry e = entries.nextElement();
          LOG.debug("Copy: " + e.getName());
          append.putNextEntry(e);
          if (!e.isDirectory()) {
            StreamUtils.copyStream(zip.getInputStream(e), append, false, false);
          }
        }

        StreamUtils.closeSilently(zip);
      }

      for (com.eidoscode.util.io.zip.ZipEntry file : files) {
        LOG.debug(file.getFile());
        streamSource = new FileInputStream(file.getFile());
        source = new BufferedInputStream(streamSource, StreamUtils.BUFFER_BYTES);
        entry = new ZipEntry(file.getRelativePath());
        LOG.debug("Adding: " + file.getRelativePath());

        append.putNextEntry(entry);

        while ((cont = source.read(data, 0, StreamUtils.BUFFER_BYTES)) != -1) {
          append.write(data, 0, cont);
        }
        StreamUtils.closeSilently(source);
      }

      StreamUtils.closeSilently(append);

      tempReadingFile.delete();
      FileUtils.moveFile(tempWritingFile, zipFile, true);
    }
    return zipFile;
  }

}
