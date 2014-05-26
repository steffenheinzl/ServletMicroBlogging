package de.fhws.microblog.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaApiEnhancements
{
  //not used
  public static int indexOf(String pattern, String s)
  {
    Pattern compiledPattern = Pattern.compile(pattern);
    Matcher matcher = compiledPattern.matcher(s);
    return matcher.find() ? matcher.start() : -1;
  }
  
  public static void readLineByLineFromFile(String filename, Collection<String> listToReadTo) throws IOException
  {
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    while (reader.ready())
    {
      String line = reader.readLine();
      listToReadTo.add(line);
    }
    reader.close();
  }
}
