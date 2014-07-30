/*
 * LargeFish.java
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
 * that will be viewed on an html page <br/>
 */

    public class LargeFish
    {        
        Graphics g;
        int x = 0;
        int y = 0;
        /**
         * Constructor for LargeFish class. <br />
         * @param canvas for fish to be painted on <br />
         * @param x x position <br />
         * @param y y position <br />
         */
        public LargeFish(Graphics canvas, int x, int y)
	{
            g = canvas;
            this.x = x;
            this.y = y;
        }

/**
 * DrawLargeFish method will create all shapes that are needed for the <br />
 * draw method and place the fish randomly. <br />
 * @param g <br />
 * @param x <br />
 * @param y <br />
 */		
	private static void DrawLargeFish(Graphics g, int x, int y)
        {
            int sf = 1;

            //Prepare Colors
            Color black = new Color(0,0,0);
            Color white = new Color(255,255,255);
            Color randColor = new Color(0,0,0);

            //Draw eyeball
            g.setColor(black);
            g.fillOval(x + sf * 13,y + sf * 31,sf * 3,sf * 4);

            //Draw Iris
            g.setColor(white);
            g.fillOval(x + sf * 14,y + sf * 33,sf * 1,sf * 1);

            //Draw body
            g.setColor(randColor);
            g.drawArc(x,y + sf * 18,sf * 87,sf * 38,  0,180);
            g.drawArc(x,y + sf * 18,sf * 87,sf * 38,180,360);

            //Draw Top Fin
            int[] x1Points = {	x + sf * 29,
                                                    x + sf * 59,
                                                    x + sf * 56,
                                                    x + sf * 62,
                                                    x + sf * 57,
                                                    x + sf * 62,
                                                    x + sf * 59	};
            int[] y1Points = {	y + sf * 21,
                                                    y + sf * 0,
                                                    y + sf * 9 ,
                                                    y + sf * 14,
                                                    y + sf * 16,
                                                    y + sf * 20,
                                                    y + sf * 23	};
            int topFinPoints = 7;
            g.drawPolyline(x1Points, y1Points, topFinPoints);

            //Draw side fin
            int[] x2Points = {	x + sf * 34,
                                                    x + sf * 44,
                                                    x + sf * 39,
                                                    x + sf * 42,
                                                    x + sf * 37,
                                                    x + sf * 36,
                                                    x + sf * 32	};
            int[] y2Points = {	y + sf * 37,
                                                    y + sf * 37,
                                                    y + sf * 42,
                                                    y + sf * 47,
                                                    y + sf * 45,
                                                    y + sf * 50,
                                                    y + sf * 42	};
            int sideFinPoints = 7;
            g.drawPolyline(x2Points, y2Points, sideFinPoints);

            //Draw Tail
            g.drawLine(x + sf * 87, y + sf * 37,x + sf * 100,y + sf * 12);
            g.drawLine(x + sf * 100,y + sf * 62,x + sf * 87, y + sf * 37);

            //Draw tail lines
            int x1Tail = x + sf * 92;
            int x2Tail = x + sf * 98;
            g.drawLine(x1Tail,y + sf * 34,x2Tail,y + sf * 23);
            g.drawLine(x1Tail,y + sf * 35,x2Tail,y + sf * 29);
            g.drawLine(x1Tail,y + sf * 36,x2Tail,y + sf * 33);
            g.drawLine(x1Tail,y + sf * 37,x2Tail,y + sf * 37);
            g.drawLine(x1Tail,y + sf * 38,x2Tail,y + sf * 41);
            g.drawLine(x1Tail,y + sf * 39,x2Tail,y + sf * 45);
            g.drawLine(x1Tail,y + sf * 40,x2Tail,y + sf * 51);
	}
        /*
         * draw method is called in ProjectViewer to draw fish. <br />
         */
        public void draw()
        {
            DrawLargeFish(g, x, y);
        }
    }
    
