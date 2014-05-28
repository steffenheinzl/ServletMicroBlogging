package de.fhws.microblog.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Writes messages to a file (or possibly database) and read all messages again.
 *
 */
public class MessagesUtil
{
  BufferedReader reader;
  BufferedWriter writer;
  File pathToMessages;
  List<String> messages = new LinkedList<String>();

  public MessagesUtil(String rootPath)
  {
    pathToMessages = new File(rootPath + "/messages.txt");
    try
    {
      writer = new BufferedWriter(new FileWriter(pathToMessages, true));
      reader = new BufferedReader(new FileReader(pathToMessages));
      while(reader.ready())
      {
        String line = reader.readLine();
        messages.add(line);
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException("ServletMicroblogging", e);
    }
  }

  /**
   *  post a message to a file
   **/
  public void postMessage(String message) throws IOException
  {
    if (!message.endsWith("\n")) message += "\n";
    writer.append(message);
    writer.flush();
    messages.add(message);
  }

  /**
   *  read all messages from the file 
   **/
  public List<String> readMessages()
  {
    return messages;
  }
}
