/**
 * Boat.java
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
 * Boat class uses the construction of geometric objects, colors, and <br/>
 * positioning of said geometric objects to create a sailboat <br/>
 * that will be viewed on an html page. <br />
 */

public class Boat
{
    Graphics g;
    int x = 0;
    int y = 0;
    Color color;
    
    /**
     * This is the constructor for boat class. <br />
     * @param canvas // canvas to be painted on <br />
     * @param x //x position <br />
     * @param y // y position <br />
     * @param colorin <br />
     */
    public Boat(Graphics canvas, int x, int y, Color colorin)
    {
        g = canvas;
        this.x = x;
        this.y = y;
        this.color = colorin;
    }

    /**
     * drawBoat method set all coordinates and colors for drawing boat <br />
     * @param g <br />
     * @param x <br />
     * @param y <br />
     * @param colorin <br />
     */
    public static void drawBoat(Graphics g, int x, int y, Color colorin)
    {
        //Draw mast
        g.setColor(Color.BLACK);
        g.drawRect(x + 270,y + 100,10,250);
        g.fillRect(x + 275,y + 100, 5, 250);
        
        //Draw boat
        Color myColor = new Color(0x7B3F00);
        g.setColor(myColor);
        g.fillArc(x + 75,y + 250, 400, 200, 0, -180);
        
        //Draw sail
        int xSail[] = {x + 285,x + 425,x + 285};
        int ySail[] = {y + 110,y + 210,y + 300};
        int pointsSail = 3;
        g.setColor(colorin);
        g.fillPolygon(xSail, ySail, pointsSail);
        
        //Draw text
        g.setColor(Color.BLACK);
        g.drawString("Hello Class!",x + 340,y + 210);
    }
/**
 * draw method is called in ProjectViewer to draw boat. <br />
 */
    public void draw()
    {
        drawBoat(g, x, y, color);
    }
}