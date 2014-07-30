/*
 * project5.java
 */
package project5;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.applet.Applet;

/**
 * Project 5 <br />
 * Project 5 is a program that combines six classes to display applet <br />
 * in html.  The applet uses basic geometric shapes to create  a <br />
 * an ocean landscape with a sailboat. <br />
 *
 * 
 * @author Chris Steigerwald, Brandan Haertel, Nasir Abdullahi, <br />
 * Mohammad Herzalla. <br />
 * @version 1.0 <br />
 *
 *  Test Environment: JDK 1.7.0_03 on Windows 7, i7 CPU <br>
 *
 * December 6, 2012<br>
 * CS completed v 1.0 <br />
 */

public class ProjectViewer extends Applet
{
    /**
     * init method sets background color. <br />
     */
    public void init()
    {
        setBackground(new Color(0xCEF6F5));
    }
    /**
     * paint method creates the canvas that the program paints to. <br />
     * @param canvas a graphics canvas <br />
     */
    public void paint(Graphics canvas)
    {
        
        
        int R = (int) (Math.random( )*256);
        int G = (int)(Math.random( )*256);
        int B= (int)(Math.random( )*256);
        Color randomColor = new Color(R, G, B);
        Random randInt = new Random();
        
        
        Boat componentBoat = new Boat(canvas,0,0,randomColor);
        Water componentWater = new Water(canvas,0,0);
        LargeFish componentLargeFish;
        SmallFish componentSmallFish;
        Cloud componentCloud;
        
        componentBoat.draw();
        componentWater.draw();
        
        int randx;
        int randy;
        for(int i = 0; i < 3; i++)
        {
            randy = randInt.nextInt(100) + 400;
            randx = randInt.nextInt(900) - 50;
            componentLargeFish = new LargeFish(canvas,randx,randy);
            componentLargeFish.draw();
        }
        
        for(int i = 0; i < 6; i++)
        {
            randy = randInt.nextInt(100) + 400;
            randx = randInt.nextInt(900) - 50;
            componentSmallFish = new SmallFish(canvas,randx,randy);
            componentSmallFish.draw();
        }
        
        
        componentCloud = new Cloud(canvas,0,0);
        componentCloud.draw();
        componentCloud = new Cloud(canvas,400,100);
        componentCloud.draw();
        componentCloud = new Cloud(canvas,600,150);
        componentCloud.draw(); 
        
        
    }
}
