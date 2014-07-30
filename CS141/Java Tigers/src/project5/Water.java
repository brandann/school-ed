/*
 * Water.java
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
 * Water class uses the construction of geometric objects, colors, and <br/>
 * positioning of said geometric objects to create water that will <br/>
 * be viewed on an html page. <br/>
 */
    public class Water
    {        
        Graphics g;
        int x = 0;
        int y = 0;
        /**
         * Water method creates the canvas that the water is painted on. <br />
         * @param canvas // canvas to be painted on <br />
         * @param x //x position <br />
         * @param y // y position <br />
         */
        public Water(Graphics canvas, int x, int y)
	{
            g = canvas;
            this.x = x;
            this.y = y;
        }
	/**
         * DrawWater Method creates geometric shapes that will look like <br />
         * water on Project5.<br />
         * @param g <br />
         * @param x <br />
         * @param y <br />
         */
	private static void DrawWater(Graphics g, int x, int y)
        {
            int alpha = 175;
            g.setColor(Color.YELLOW);
            g.fillOval(520,0,75,75);
                
            g.setColor(new Color(000,204,255,alpha));
            for(int j = 400; j < 800; j += 50)
            {
                for(int i = -100; i < 900; i+=50)
                {
                    g.fillOval(i, j, 110, 75);
                }
            }
            
	}
        
        /**
         * draw method is called by ProjectViewer to paint water on final <br />
         * canvas. <br />
         */
        public void draw()
        {
            DrawWater(g, x, y);
        }
    }