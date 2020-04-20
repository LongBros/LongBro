package com.longbro.util;

import com.google.gson.Gson;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

public class FileProduce
{
  public static void main(String[] args)
  {
    getAllFiles("5211314", "D:/apache-tomcat-8.5.35/webapps/LongMusic/image/tx/");
  }
  
  public static ArrayList<String> getAllFiles(String userId, String path)
  {
    ArrayList<String> userFiles = new ArrayList();
    
    File file = new File(path);
    if (file.isDirectory())
    {
      File[] fs = file.listFiles();
      File[] arrayOfFile1;
      int j;
      int i;
      if (StringUtils.isEmpty(userId))
      {
        j = (arrayOfFile1 = fs).length;
        for (i = 0; i < j; i++)
        {
          File f = arrayOfFile1[i];
          userFiles.add(f.getName());
        }
      }
      else
      {
        j = (arrayOfFile1 = fs).length;
        for (i = 0; i < j; i++)
        {
          File f = arrayOfFile1[i];
          if ((f.getName().contains("_")) && (userId.equals(f.getName().substring(0, f.getName().indexOf("_"))))) {
            userFiles.add(f.getName());
          }
        }
      }
      System.out.println(new Gson().toJson(userFiles));
    }
    return userFiles;
  }
}
