/*
 * pin 'em up
 * 
 * Copyright (C) 2007 by Mario Koedding
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package net.sourceforge.pinemup.logic;

import java.io.*;
import java.net.URL;
import java.awt.*;

public class ResourceLoader {
   
   private static Image closeIcon1 = loadImage("net.sourceforge.pinemup.resources","closeicon.png");
   private static Image closeIcon2 = loadImage("net.sourceforge.pinemup.resources","closeicon2.png");
   private static Image trayIcon = loadImage("net.sourceforge.pinemup.resources","icon" + getTrayIconSize() + ".png");
   private static Image scrollImage = loadImage("net.sourceforge.pinemup.resources","scroll.png");
   
   private static long getTrayIconSize() {
      long size = Math.round(SystemTray.getSystemTray().getTrayIconSize().getHeight());
      if ((size < 16)) {
         size = 16;
      } else if (size > 48) {
         size = 48;
      } else if (size % 8 != 0) {
         size = Math.round(size/8) * 8;
      }
      return size;
   }
   
   private static InputStream getResourceStream(String pkg, String filename) {
      String name = "/" + pkg.replace('.', '/') + "/" + filename;
      InputStream is = PinEmUp.getMainApp().getClass().getResourceAsStream(name);
      return is;
   }
   
   private static Image loadImage(String pkg, String filename) {
      Image img = null;
      try {
         InputStream is = getResourceStream(pkg, filename);
         
         if (is != null) {
            byte[] buffer = new byte[0];
            byte[] temp = new byte[1024];
            while(true) {
               int len = is.read(temp);
               if (len <= 0) {
                  break;
               }
               byte[] newBuffer = new byte[buffer.length + len];
               System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
               System.arraycopy(temp, 0, newBuffer, buffer.length, len);
               buffer = newBuffer;
            }
            img = Toolkit.getDefaultToolkit().createImage(buffer);
            is.close();
         }         
      } catch (IOException e) {
         // do nothing
      }

      return img;
   }
   
   public static Image getCloseIcon(int nr) {
      switch(nr) {
      case 1: return closeIcon1;
      case 2: return closeIcon2;
      default: return closeIcon1;
      }
   }
   
   public static Image getTrayIcon() {
      return trayIcon;
   }
   
   public static Image getScrollImage() {
      return scrollImage;
   }
   
   public static String getLicense() {
      String s = "";
      try {
         String pkg = "net.sourceforge.pinemup.resources";
         String filename = "COPYING";
         String name = "/" + pkg.replace('.', '/') + "/" + filename;
         InputStream is = PinEmUp.getMainApp().getClass().getResourceAsStream(name);
         BufferedReader br = new BufferedReader(new InputStreamReader(is));         
         String nextLine = br.readLine();
         while (nextLine != null) {
            s += nextLine + "\r\n";
            nextLine = br.readLine();
         }
         br.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return s;
   }
   
   public static URL getSchemaFile() {
      String pkg = "net.sourceforge.pinemup.resources";
      String filename = "notesfile-0.1.xsd";
      String name = "/" + pkg.replace('.', '/') + "/" + filename;
      URL u = PinEmUp.getMainApp().getClass().getResource(name);
      return u;
   }
}
