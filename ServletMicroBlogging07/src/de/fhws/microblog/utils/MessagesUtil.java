package de.fhws.microblog.utils;

import java.io.BufferedWriter;
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
  private static MessagesUtil mu = null;

  public static MessagesUtil getInstance(String rootPath)
  {
    if (mu == null) mu = new MessagesUtil(rootPath);
    return mu;
  }

  UsersUtil uu;
  BufferedWriter writer;
  String pathToMessages;
  List<String> messages = new LinkedList<String>();

  private MessagesUtil(String rootPath)
  {
    uu = UsersUtil.getInstance(rootPath);
    pathToMessages = rootPath + "/messages.txt";
    try
    {
      writer = new BufferedWriter(new FileWriter(pathToMessages, true));
      JavaApiEnhancements.readLineByLineFromFile(pathToMessages, messages);
    }
    catch (IOException e)
    {
      throw new RuntimeException("ServletMicroblogging", e);
    }
  }

  /**
   * post a message to a file
   **/
  public synchronized void postMessage(String message) throws IOException
  {
    if (!message.endsWith("\n")) message += "\n";
    writer.append(message);
    writer.flush();
    List<String> usersFromMessage = retrieveUsersFromMessage(message);
    for (String userName : usersFromMessage)
    {
      uu.postUser(userName);
      uu.addToUserMessages(userName, message);
    }
    messages.add(message); // add to ALL messages
  }

  /**
   * parses message for @ signs which identify users
   * 
   * @param message
   * @return
   */
  private List<String> retrieveUsersFromMessage(String message)
  {
    List<String> usersInMessage = new LinkedList<String>();
    int startIndex = -1;
    while (message.indexOf("@", startIndex + 1) != -1)
    {
      startIndex = message.indexOf("@", startIndex + 1);
      int endIndex = message.indexOf(" ", startIndex);
      String userName;
      if (endIndex == -1)
      {
        endIndex = message.length();
      }
      else
      {
        userName = message.substring(startIndex, endIndex);
        for (int i = 1; i < userName.length(); i++)
        {
          if (!(
              (userName.charAt(i) >= 'a' && userName.charAt(i) <= 'z')
              || (userName.charAt(i) >= 'A' && userName.charAt(i) <= 'Z')
              || (userName.charAt(i) >= '0' && userName.charAt(i) <= '9'))
             )
          {
            endIndex = i + startIndex;
            break;
          }
        }
      }
      userName = message.substring(startIndex, endIndex);      
      if (userName.length() > 3) // check if its a real user
      {
        usersInMessage.add(userName);
      }
    }
    return usersInMessage;
  }

  /**
   * Returns all messages sent from and to this user!
   * @param user
   * @return
   */
  public List<String> readUserMessages(String user)
  {
    try
    {
      return uu.readUserMessages(user);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return new LinkedList<String>();
  }

  /**
   * read all messages from the file
   **/
  public List<String> readMessages()
  {
    return messages;
  }
}
