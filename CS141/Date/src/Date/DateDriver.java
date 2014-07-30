/*
 * DateDriver.java
 */
package Date;

import java.util.ArrayList;
import java.util.List;

/**
 * Date Driver <br>
 * <br>
 * Program the compares inputed dates the current dates <br>
 * <br>
 * Test Enviroment: JDK 1.7.*** on Windows 7, i7 CPU <br>
 * Test Enviroment: JDK 1.7.*** on Windows 8, AMD CPU <br>
 * <br>
 * November 14th 2012 <br>
 * 	Completed v 1.0 <br>
 * @author Brandan Haertel <br>
 * @version 1.0 <br>
 */
public class DateDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DateAnalyzer userDate;  
         List<Short> inputDate = new ArrayList<>();         
        if(args.length > 0)
        {
            setlist: for(int i = 0 ; i < 3; i++)
            {
               try
               {
                  inputDate.add(Short.parseShort(args[i]));
               }
               catch(Exception e)
                    {
                        break setlist;
                    }
            }            
        }
        
        if(inputDate.size() == 3)
        {
            userDate = new DateAnalyzer(inputDate.get(0),
                    inputDate.get(1),inputDate.get(2));
        }
        else if(inputDate.size() == 2)
        {
            userDate = new DateAnalyzer(inputDate.get(0),inputDate.get(1));
        }
        else if(inputDate.size() == 1)
        {
            userDate = new DateAnalyzer(inputDate.get(0));
        }
        else
        {
            userDate = new DateAnalyzer();
        }
        System.out.println("");
        System.out.println(userDate.GetCurrentDate());
        System.out.println(userDate.GetInputedDate());
        System.out.println(userDate.GetDateBefore());
        System.out.println(userDate.GetDateAfter());
        System.out.println(userDate.GetPastOrFuture());
    }
}
