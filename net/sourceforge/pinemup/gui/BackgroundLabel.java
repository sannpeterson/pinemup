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

package net.sourceforge.pinemup.gui;

import java.awt.*;
import javax.swing.JLabel;

public class BackgroundLabel extends JLabel {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   private static final Color MYSTARTCOLOR = new Color(255, 255, 185);
   private static final Color MYENDCOLOR = new Color(255, 235, 70);
   private NoteWindow parentWindow;
   
   public void paintComponent(Graphics g) {
       setBounds(0,0,parentWindow.getWidth(),parentWindow.getHeight());
       int h = getHeight();
       int w = getWidth();
       Graphics2D g2 = (Graphics2D)g;
       
       for (int i=1; i<=w; i++) {
          int startX = i;
          int startY = 0;
          int endX = startX+1;
          int endY = startY+h;
          GradientPaint gradient = new GradientPaint(startX, startY, MYSTARTCOLOR, endX, endY, MYENDCOLOR);
          g2.setPaint(gradient);
       }
       g2.fillRect(0,0,w,h);
   }
   
   public BackgroundLabel(NoteWindow w) {
      super();
      parentWindow = w;
      setBounds(0,0,parentWindow.getWidth(),parentWindow.getHeight());
      setFocusable(false);
      setOpaque(false);
   }
}