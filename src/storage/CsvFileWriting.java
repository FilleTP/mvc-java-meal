/*declare the number of attempts as retries with a max of 5
  Conditional loop as long as retries is ok
  try block to start the process of writing with automatic closing of resources afterwards
  try block to lock and catch if not possible
  release lock
*/
package storage;

import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class CsvFileWriting {
  public void writeToCsv(String[] data, String fileName, String[] headers) throws IOException {
    int retriesCounter = 0;
    int maxRetries = 5;
    long waitTime = 1000;
    boolean success = false;

    File csvFile = new File(fileName);
    boolean fileExists = csvFile.exists() && csvFile.length() > 1;

    while (retriesCounter <= maxRetries && !success) {
      try(FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
        FileChannel channel = fileOutputStream.getChannel();
        OutputStreamWriter osWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter writer = new BufferedWriter(osWriter)) {

        FileLock lock = null;
        try {
          lock = channel.tryLock();
          if (lock != null) {
            if (!fileExists) {
              writer.write(String.join(",", headers));
              writer.newLine();
            }
            writer.write(String.join(",", data));
            writer.newLine();
            success = true;
            System.out.println("Successfully wrote to the file.");
          } else {
            throw new IOException("Unable to aquire File Lock");
          }
        } finally {
          if (lock != null) {
            lock.release();
          }
        }

      } catch (IOException ioe) {
        retriesCounter++;
        if (retriesCounter >= maxRetries) {
          System.out.println("Failed to write to file after " + maxRetries + "attempts");
        } else {
          System.out.println("File was locked or an error ocurred, trying again");
          try {
            Thread.sleep(waitTime);
          } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted", ie);
          }
        }
      }
    }
  }
}
