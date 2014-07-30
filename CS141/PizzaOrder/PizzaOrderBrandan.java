/* 
 * PizzaOrderBrandan.java
 */

import java.io.*;
import java.util.Scanner;

/**
 * Pizza Order <br>
 * <br>
 * Sample that inputs a pizza size and toppings <br>
 * and calculates price and price per square inch <br>
 * <br>
 * Test Enviroment: JDK 1.7.*** on Windows 7, i7 CPU <br>
 * <br>
 * September 28th 2012 <br>
 * 	Completed v 1.0 <br>
 * October 22 2012 <br>
 * 	Completed v 1.1 <br>
 * @author Brandan Haertel <br>
 * @version 1.1 <br>
 */

public class PizzaOrderBrandan 
{

	// Initiate constant variabels

	public static final int SMALL_DIAMETER_INCHES = 9;
	public static final int MEDIUM_DIAMETER_INCHES = 13;
	public static final int LARGE_DIAMETER_INCHES = 17;
	public static final int COLOSSAL_DIAMETER_INCHES = 26;

	public static final int SMALL_PRICE_DOLLARS = 10;
	public static final int MEDIUM_PRICE_DOLLARS = 13;
	public static final int LARGE_PRICE_DOLLARS = 16;
	public static final int COLOSSAL_PRICE_DOLLARS = 25;
		
	public static final int MINIMUM_TOPPINGS = 0;
	public static final int MAXIMUM_TOPPINGS = 8;
		
	public static final float TOPPINGS_PRICE = 0.99F;
	public static final float TAX_RATE = 0.095F;
	public static final float DIAMETER_TO_RADIUS = 2;

	public static final String SMALL_TOKEN = "S";
	public static final String MEDIUM_TOKEN = "M";
	public static final String LARGE_TOKEN = "L";
	public static final String COLOSSAL_TOKEN = "C";
		
       /**
	* Inputs a Pizza size and quantity of toppings
	* 
	* input:	Pizza size
	*		Number of toppings
	* output:	Pizza size
	*		Pizza diameter
	* 		toppings quantities
	*		price of pizza
	*		tax Total
	*		price + tax
	*		price per square inch
	*
	* @param args command-line arguments
	*/

	public static void main(String[] args)
	{
		// Initiate variabels		

		String sizeInput = SMALL_TOKEN;
		double pizzaCost = 0;
		double pizzaSize = 0;
		double toppings = 0;
		double price = 0;
		double totalPrice = 0;
		double priceSqIn = 0;
		double radius = 0;
		double sqIn = 0;
		Scanner sc = new Scanner(System.in);

		// Get user size input
				
		System.out.println("Please choose a pizza size: (S/M/L/C)");
		sizeInput = sc.next().substring(0,1);							
				
		// Check user input and set cost of pizza and size of pizza
		
		if (sizeInput.equalsIgnoreCase	(SMALL_TOKEN))
		{
			pizzaCost = SMALL_PRICE_DOLLARS;
			pizzaSize = SMALL_DIAMETER_INCHES;
			System.out.println("");
		}
		else if (sizeInput.equalsIgnoreCase(MEDIUM_TOKEN))
		{
			pizzaCost = MEDIUM_PRICE_DOLLARS;
			pizzaSize = MEDIUM_DIAMETER_INCHES;
			System.out.println("");
		}
		else if (sizeInput.equalsIgnoreCase(LARGE_TOKEN))
		{
			pizzaCost = LARGE_PRICE_DOLLARS;
			pizzaSize = LARGE_DIAMETER_INCHES;
			System.out.println("");
		}
		else if (sizeInput.equalsIgnoreCase(COLOSSAL_TOKEN))
		{
			pizzaCost = COLOSSAL_PRICE_DOLLARS;
			pizzaSize = COLOSSAL_DIAMETER_INCHES;
			System.out.println("");
		}
		else
		{
			sizeInput = SMALL_TOKEN;
			pizzaCost = SMALL_PRICE_DOLLARS;
			pizzaSize = SMALL_DIAMETER_INCHES;
			System.out.println("");
			System.out.println("An error occured in you input.");
			System.out.println("Size has been set to: " + sizeInput);
			System.out.println("");
		}
			
		System.out.println("Choose your number of toppings (0-8)");
		
		// Get user toppings input
							
		if (sc.hasNextInt())
		{
			toppings = sc.nextInt();
		}
		else
		{
			toppings = MINIMUM_TOPPINGS;
			System.out.println("");
			System.out.println("An error occured in you input.");
			System.out.println("Toppings has been set to: " + toppings);
		}
		if (toppings > MAXIMUM_TOPPINGS)
		{
			toppings = MAXIMUM_TOPPINGS;
			System.out.println("");
			System.out.println("Maximum number of toppings exceded");
			System.out.println("Toppings has been set to: " + toppings);
		}
		else if (toppings < MINIMUM_TOPPINGS)
		{
			toppings = MINIMUM_TOPPINGS;
			System.out.println("");
			System.out.println("Minimum number of toppings exceded");
			System.out.println("Toppings has been set to: " + toppings);
		}
		
		//Calculate pizza equations

		price = pizzaCost + (toppings * TOPPINGS_PRICE);
		totalPrice = price + (price * TAX_RATE);
		radius = pizzaSize / DIAMETER_TO_RADIUS;
		sqIn = Math.pow(radius,2) * Math.PI;
		priceSqIn = price / sqIn;

		// Display results
		
		System.out.println("");		
		System.out.println("Size:\t\t\t" + sizeInput.toUpperCase());
		System.out.print("Diameter:\t\t" + pizzaSize + "\"");
		System.out.printf(" -- %.3f", sqIn);
		System.out.println(" Square Inches");
		System.out.println("toppings:\t\t" + toppings);
		System.out.printf("Price:\t\t\t$%.2f%n", price);
		System.out.printf("Tax:\t\t\t$%.2f%n", (TAX_RATE * price));
		System.out.printf("Total Price:\t\t$%.2f%n", totalPrice);
		System.out.printf("Price per SqIn:\t\t$%.3f%n", priceSqIn);
	}
}
