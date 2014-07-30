/*
 * LinkedList.java
 */
package CircularDoublyLinkedList;
import java.util.Iterator;
import java.util.List;

/**
 * Test Driver for the CircularDoublyLinkedList Class
 * @author pbladek
 * @version 1.0
 * Test Environment: JDK 1.7.0_03 on Windows 7, i3 CPU<br />
 * @author pbladek
 */
public class CircularDoublyLinkedListDriver 
{
    /**
     * main program
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            int index = CircularDoublyLinkedList.ITEM_NOT_FOUND;
            List<String> aList =  new CircularDoublyLinkedList<>();
            aList.add(0, "this");
            System.out.println(aList);
            aList.add(1, "is");
            System.out.println(aList);
            aList.add(2, "a");
            System.out.println(aList);
            aList.add(3, "test");
            System.out.println(aList);
            aList.add(2, "not");
            aList.add(4, "not");
            System.out.println(aList);
            
            index = aList.indexOf("not");
            if(index > CircularDoublyLinkedList.ITEM_NOT_FOUND)
                System.out.println("first \"not\" is at " + index);
            else
                System.out.println("item not found");
            
            index = aList.lastIndexOf("not");
            if(index > CircularDoublyLinkedList.ITEM_NOT_FOUND)
                System.out.println("last \"not\" is at " + index);
            else
                System.out.println("item not found");  
            
            index = aList.indexOf("note");
            if(index > CircularDoublyLinkedList.ITEM_NOT_FOUND)
                System.out.println("first \"note\" is at " + index);
            else
                System.out.println("item not found"); 
            
            System.out.println("removing element 4: " + aList.remove(4)); 
            System.out.println(aList);
            
            for(String s: aList)
                System.out.print("(" + s + ") ");
            aList.clear();
            System.out.println(aList);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
