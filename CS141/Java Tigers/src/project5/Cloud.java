/*
 * cloud.java
 */
package project5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.applet.*;


/**
 * Cloud class uses the construction of geometric objects, colors, and <br/>
 * positioning of said geometric objects to create clouds that <br/>
 * will be viewed on an html page. <br/>
 */

    public class Cloud
    {        
        Graphics g;
        int x = 0;
        int y = 0;
        /*
         * Constructor for cloud class <br />
         */
        public Cloud(Graphics canvas, int x, int y)
	{
            g = canvas;
            this.x = x;
            this.y = y;
        }
	/**
         * DrawCloud method is used to draw the clouds. <br />
         * @param g // graphics g <br />
         * @param x // x position on canvas <br />
         * @param y // y position on canvas <br />
         */	
	private static void DrawCloud(Graphics g, int x, int y)
        {
		int xCloud1 = x + 20;
     		int xCloud2 = x + 100;
     		int xCloud3 = x + 60;
     		int yCloud = y + 20;
     		int xRightWingBrid1 = x + 85;
     		int xleftWingBrid1 = x + 0;
     		int yRightWingBrid1 = y + 5;
     		int yleftWingBrid1 = y + 5;
     		int xRightWingBrid2 = x + 105;
     		int xleftWingBrid2 = x + 20;
     		int yRightWingBrid2 = y + 10;
     		int yleftWingBrid2 = y + 10;

     		g.setColor(Color.WHITE);
     
     		g.fillOval(xCloud1,  yCloud, 100, 60);   
     		g.fillOval(xCloud2,  yCloud, 100, 60);   
     		g.fillOval(xCloud3,  yCloud, 100, 60);   
     
      
    		 g.setColor(Color.black); 
    		 g.drawArc(xleftWingBrid1, yleftWingBrid1, 100, 100, 30, 30);
    		 g.drawArc(xRightWingBrid1, yRightWingBrid1, 100, 100, 120,30);
    		 g.drawArc(xleftWingBrid2, yleftWingBrid2, 100, 100, 30, 30);
    		 g.drawArc(xRightWingBrid2 , yRightWingBrid2, 100, 100, 120,30);
	}
        /**
         * draw method is called by ProjectViewer so cloud can be drawn. <br />
         */
        public void draw()
        {
            DrawCloud(g, x, y);
        }
    }