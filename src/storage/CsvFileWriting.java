// declare the number of attempts as retries with a max of 5
// Conditional loop as long as retries is ok
// try block to start the process of writing
// try block to lock and catch if not possible
// unlock and close writer


package storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class CsvFileWriting {
  public void writeToCsv(List<String[]> data, String fileName) throws IOException {
    int retriesCounter = 0;
    int maxRetries = 5;
    long waitTime = 1000;
    boolean success = false;

    while (retriesCounter <= maxRetries && !success) {
      try {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
        FileChannel channel = fileOutputStream.getChannel();
        OutputStreamWriter osWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter writer = new BufferedWriter(osWriter);

        FileLock lock = null;
        try {
          lock = channel.tryLock();
          if (lock != null) {
            for (String[] line : data) {
              writer.write(String.join(",", line));
              writer.newLine();
            }
            success = true;
            System.out.println("Successfully wrote to the file.");
          } else {
            throw new IOException("Unable to aquire File Lock");
          }
        } finally {
          if (lock != null) {
            lock.release();
            writer.close();
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
