/**
 * SmallFish.java
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
 * LargeFish class uses the construction of geometric objects, colors, and <br/>
 * positioning of said geometric objects to create random large fish <br/>
 * that will be viewed on an html page. <br/>
 */
    public class SmallFish
    {        
        Graphics g;
        int x = 0;
        int y = 0;
        
        /**
         * SmallFish method creates canvas for fish. <br />
         * @param canvas // canvas to be painted on <br />
         * @param x //x position <br />
         * @param y // y position <br />
         */
        public SmallFish(Graphics canvas, int x, int y)
	{
            g = canvas;
            this.x = x;
            this.y = y;
        }
		
        /**
         * DrawSmallFish method creates geometric shapes that will look <br />
         * like small fish on Project 5. <br />
         * @param g <br />
         * @param x <br />
         * @param y <br />
         */
	private static void DrawSmallFish(Graphics g, int x, int y)
        {
		
		
		//Prepare Colors
		Color randColor = 	new Color(0,0,0);
		
		//Set random color to draw body
		g.setColor(randColor);
		
		//Draw body
		g.drawArc(x,y ,50,20,0,180);
		g.drawArc(x,y,50,20,180,360);
		
		//Draw tail
		g.drawLine(x + 50,y + 10,x + 60,y + 0);
		g.drawLine(x + 60,y + 20,x + 50,y + 10);
	}
        
        /**
         * draw method is called by ProjectViewer to paint small fish on <br />
         * canvas. <br />
         */
        public void draw()
        {
            DrawSmallFish(g, x, y);
        }
    }
    
