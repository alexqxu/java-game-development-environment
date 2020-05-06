package ooga.utilities.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class WriteToCSV {

  private String newLine = System.getProperty("line.separator");
  private BufferedWriter fileWriter = null;
  //private OutputStreamWriter fileWriter = null;
  private int columnNumber = 0;
  private int rowNumber = 0;

  public WriteToCSV(String fileName) throws IOException {
    fileWriter = new BufferedWriter(new FileWriter(fileName));
  }

  public WriteToCSV(File csvFile, String encodingType)
      throws FileNotFoundException, UnsupportedEncodingException {

    if (encodingType == null){
      encodingType = System.getProperty("file.encoding");
    }
    FileOutputStream fileOutputStream = new FileOutputStream(csvFile);
    //fileWriter = new OutputStreamWriter(fileOutputStream, encodingType);
  }

  /**
   * writes the csv header (fieldnames). should be called after
   * construction one time.
   * @param header String[] with fieldnames
   */
  public void writeHeader(String[] header) throws IOException {

    this.columnNumber = header.length;

    doWriteData(header);
  }

  /**
   * writes a data-record to the file. note that data[] must have
   * same number of elements as the header had.
   *
   * @param data data to write to csv-file
   */
  public void writeData(String[] data) throws IOException {
    doWriteData(data);
  }

  /**
   * closes the csv file.
   */
  public void close() throws IOException {
    this.fileWriter.close();
  }

  private void doWriteData(String[] values) throws IOException {

    for (int i = 0; i < values.length; i++) {
      if (i > 0) {
        this.fileWriter.write(";");
      }
      if (values[i] != null) {
        this.fileWriter.write("\"");
        this.fileWriter.write(this.toCsvValue(values[i]));
        this.fileWriter.write("\"");
      }
    }
    this.fileWriter.write(newLine);
    this.rowNumber++;
  }

  private String toCsvValue(String str) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      sb.append(c);
      switch (c) {
        case '"' :
          sb.append('"');
          break;
      }
    }
    return sb.toString();
  }

}
