package com.eidoscode.util.io.zip;

import java.io.File;

/**
 * Internal Zip entry. It refers to a physical file and a logical location that
 * this file must be placed on the generated zip file.
 * 
 * @author antonini
 * @since 1.1.0
 * @version 1.0
 */
public class ZipEntry {

  private String relativePath;
  private File file;

  public ZipEntry() {
    super();
  }

  public ZipEntry(File file, String relativePath) {
    this();
    this.relativePath = relativePath;
    this.file = file;
  }

  public String getRelativePath() {
    return relativePath;
  }

  public void setRelativePath(String relativePath) {
    this.relativePath = relativePath;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

}
