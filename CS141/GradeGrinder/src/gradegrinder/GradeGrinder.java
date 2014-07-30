/* 
 * GradeGrinder.java
 */

package gradegrinder;

/**
 * GradeGrinder <br>
 * <br>
 * Sample that inputs grades and calculates: <br>
 * sum, high, low, mean... <br>
 * <br>
 * Test Enviroment: JDK 1.7.2 on Windows 7, i7 CPU <br>
 * <br>
 * October 24th 2012 <br>
 * 	Completed v 1.0 <br>
 * November 14th 2012 <br>
 *      Completed v 1.1 <br>
 * @author Brandan Haertel <br>
 * @version 1.0 <br>
 */
import java.util.Scanner;

public class GradeGrinder 
{

    /**
     * Simple program that inputs and calculates grades
     * Inputs: grades
     * Outputs: high
     *          low
     *          mean
     *          sum
     *          variance
     *          standard deviation
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        GradeAnalyzer book = new GradeAnalyzer();
        boolean repeat = true;
        String gradeInput;
        Scanner keyboard = new Scanner(System.in);
        
        
        
        while (repeat)
        {
            System.out.println("Enter grades (enter a non-number to stop)");
            while (keyboard.hasNextInt())
            {
                book.addGrade(keyboard.nextInt());
            }
            
            System.out.println(book.GetResults());
            
            keyboard.nextLine();
            
            System.out.println("Would you like to continue? (y/n)");
            gradeInput = keyboard.nextLine();
            
            if (keyboard.hasNextLine())
            {
                gradeInput = keyboard.nextLine().trim();
                if (gradeInput.substring(0,1).equals("n"))
                {
                    repeat = false;
                }
            }
        } 
    }
}

