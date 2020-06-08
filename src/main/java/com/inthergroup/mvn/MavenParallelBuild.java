package com.inthergroup.mvn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author nsirbu
 */
public class MavenParallelBuild
{

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws FileNotFoundException, IOException
  {
    String pathToFile = System.getenv("appdata") + "\\NetBeans\\8.2\\config\\Preferences\\org\\netbeans\\modules\\maven.properties";
    String pathToBackupFile = System.getenv("appdata") + "\\NetBeans\\8.2\\config\\Preferences\\org\\netbeans\\modules\\maven.properties.backup";

    new File(pathToFile).renameTo(new File(pathToBackupFile));

    BufferedReader sourceFile = new BufferedReader(new FileReader(pathToBackupFile));
    PrintWriter writer = new PrintWriter(new File(pathToFile), "UTF-8");
    String line;

    while ((line = sourceFile.readLine()) != null)
    {
      if (line.contains("defaultOptions=") && !line.contains("-T 1C"))
      {
        if (line.length() > "defaultOptions=".length())
        {
          line += " -T 1C";
        }
        else
        {
          line += "-T 1C";
        }
      }
      writer.println(line);
    }
    sourceFile.close();
    if (writer.checkError())
    {
      throw new IOException("cannot write");
    }
    writer.close();
  }
}
