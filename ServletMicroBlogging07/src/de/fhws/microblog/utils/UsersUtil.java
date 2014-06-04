package de.fhws.microblog.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UsersUtil
{
  private static UsersUtil uu = null;
  
  static UsersUtil getInstance(String rootPath)
  {
    if (uu == null) uu = new UsersUtil(rootPath); 
    return uu;
  }
  
  BufferedReader reader;
  BufferedWriter writer;
  String pathToUsers;
  Set<String> users = new TreeSet<String>();
  String rootPath;

  private String prepareUser(String user)
  {
    if (user.startsWith("@")) user = user.substring(1);
    user = user.trim();
    user = user.toLowerCase();
    return user;
  }

  private UsersUtil(String rootPath)
  {
    this.rootPath = rootPath;
    File folder = new File(rootPath + "/users/");
    if (!folder.exists()) folder.mkdir();
    pathToUsers = rootPath + "/users.txt";
    try
    {
      writer = new BufferedWriter(new FileWriter(pathToUsers, true));
      JavaApiEnhancements.readLineByLineFromFile(pathToUsers, users);
    }
    catch (IOException e)
    {
      throw new RuntimeException("ServletMicroblogging", e);
    }
  }

  /**
   * posts a user to a file , if the user does not already exist
   **/
  void postUser(String user) throws IOException
  {
    user = prepareUser(user);
    if (!users.contains(user))
    {
      writer.append(user + "\n");
      writer.flush();
      File f = new File(rootPath + "/users/" + user + "-messages.txt");
      f.createNewFile();
      users.add(user);
    }
  }

  void addToUserMessages(String user, String message) throws IOException
  {
    user = prepareUser(user);
    BufferedWriter userWriter = new BufferedWriter(new FileWriter(rootPath + "/users/" + user + "-messages.txt", true));
    userWriter.append(message);
    userWriter.close();
  }

  List<String> readUserMessages(String user) throws IOException
  {
    user = prepareUser(user);
    List<String> userMessages = new LinkedList<String>();
    try
    {
      JavaApiEnhancements.readLineByLineFromFile(rootPath + "/users/" + user + "-messages.txt", userMessages);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("User not found");
    }
    return userMessages;
  }

  /**
   * read all messages from the file
   **/
  public Set<String> readUsers()
  {
    return users;
  }
}
