package com.longbro.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageProduce
{
  public static BufferedImage markText(String imgPath, String text, Font font, Color color, int x, int y)
  {
    BufferedImage bi = null;
    try
    {
      File imgFile = null;
      Image img = null;
      if (imgPath != null) {
        imgFile = new File(imgPath);
      }
      if ((imgFile != null) && (imgFile.exists()) && (imgFile.isFile()) && (imgFile.canRead())) {
        img = ImageIO.read(imgFile);
      }
      int imgWidth = img.getWidth(null);
      int imgHeight = img.getHeight(null);
      
      bi = new BufferedImage(imgWidth, imgHeight, 1);
      
      mark(bi, img, text, font, color, imgWidth - 480, imgHeight - 20);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return bi;
  }
  
  public static void mark(BufferedImage bi, Image img, String text, Font font, Color color, int x, int y)
  {
    Graphics2D g = bi.createGraphics();
    g.drawImage(img, 0, 0, bi.getWidth(), bi.getHeight(), null);
    g.setColor(color);
    g.setFont(font);
    g.drawString(text, x, y);
    g.dispose();
  }
}
