/* 
 * GradeAnalyzer.java
 */
package gradegrinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * @version 1.1 <br>
 */
public class GradeAnalyzer {
    List<Integer> grades = new ArrayList<>();
    private final int SQUARE = 2;
    private final int MINIMUM_GRADE = 0;
    private final int MAXIMUM_GRADE = 100;
    private final int GRADE_DIVISOR = 10;
    private final int NULL_GRADE_DATA = 0;
    private int gradeA = 0;
    private int gradeB = 0;
    private int gradeC = 0;
    private int gradeD = 0;
    private int gradeF = 0;
    
    /**
     * adds an Integer grade to the array list
     * if the grade is > 100, the grade is set to 100
     * if the grade is < 0 then the grade is set to 0
     * increments corisponding letter grade
     * @param gradeInput 
     */
    public void addGrade(int gradeInput) {
        if (gradeInput < MINIMUM_GRADE){}
        else if (gradeInput > MAXIMUM_GRADE){}
        else
        {
            grades.add(gradeInput);
            switch (gradeInput/GRADE_DIVISOR)
            {
                case 10: 
                    ++gradeA;
                    break;
                case 9:
                    ++gradeA;
                    break;
                case 8:
                    ++gradeB;
                    break;
                case 7:
                    ++gradeC;
                    break;
                case 6:
                    ++gradeD;
                    break;
                default:
                    ++gradeF;   
            }       
        }
    }
    
    /**
     * Gets the highest Integer in the array list.
     * @return largestyet
     */
    public int GetHighGrade()
    {
        int largestyet = 0;
        if (grades.isEmpty())
                {
                    largestyet = NULL_GRADE_DATA;
                }
        else
        {
            largestyet = grades.get(0);
            for (int i = 1; i < grades.size(); i++)
            {
                int j = grades.get(i);
                if (j > largestyet)
                {
                    largestyet = j;
                } 
            }
        }
        
        return largestyet;
    }
    
    /**
     * Gets the lowest Integer in the array list
     * @return lowestyet
     */
    public int GetLowGrade()
    {
        int lowestyet = 0;
        if(grades.isEmpty())
        {
            lowestyet = NULL_GRADE_DATA;
        }
        else
        {
            lowestyet = grades.get(0);
            for (int i = 1; i < grades.size(); i++)
            {
                int j = grades.get(i);
                if (j < lowestyet)
                {
                    lowestyet = j;
                }
            }
        }
        return lowestyet;
    }
    
    /**
     * Gets the Sum of the grades.
     * @return gradeSum
     */
    public int GetSumGrade()
    {
        int gradeSum = 0;
        if(grades.isEmpty())
        {
            gradeSum = NULL_GRADE_DATA;
        }
        else
        {
            for (int i = 0; i < grades.size(); i++)
            {
                gradeSum += grades.get(i);
            }
        }
        return gradeSum;
    }
    
    /**
     * Gets the Mean of the grades.
     * if the array list is empty, then mean = 0
     * @return gradeMean
     */
    public double GetMeanGrade()
    {
        double gradeMean = 0;
	if (grades.isEmpty())
	{
		gradeMean = NULL_GRADE_DATA;
	}
        else
	{
		gradeMean = GetSumGrade() / (grades.size());
	}
        return gradeMean;
    }
    
    /**
     * Gets the variance.
     * if the array list is empty, then variance = 0.
     * @return variance
     */
    public double GetVariance()
    {
        double sumOfTheSquares = 0;
        double sumOfTheDataSqrd = 0;
        double variance = 0;
        double numberOfGrades = grades.size();
        
	if (grades.isEmpty())
	{
		variance = NULL_GRADE_DATA;
	}
        else
	{
		for (int i = 0; i < grades.size(); i++)
        	{
        	    sumOfTheSquares += Math.pow(grades.get(i), SQUARE);
        	    sumOfTheDataSqrd += grades.get(i);
        	}
        	sumOfTheDataSqrd = Math.pow(sumOfTheDataSqrd,2);
        	variance = (sumOfTheSquares - 
                           (sumOfTheDataSqrd / numberOfGrades)) 
        	           / (numberOfGrades - 1);
	}
        return variance;
    }
    
    /**
     * Gets standard deviation
     * @return standardDeviation
     */
    public double GetStandardDeviation()
    {
        double standardDeviation = 0;
        if(grades.isEmpty())
        {
            standardDeviation = NULL_GRADE_DATA;
        }
        else
        {
            standardDeviation = Math.sqrt(GetVariance());
        }
        return standardDeviation;
    }
    
    /**
     * Formats the output string of all grade calculations.
     * @return resultString
     */
    public String GetResults()
    {
	String gradeCountA = String.format(" A:%5d|", gradeA);
	String gradeCountB = String.format(" B:%5d|", gradeB);
	String gradeCountC = String.format(" C:%5d|", gradeC);
	String gradeCountD = String.format(" D:%5d|", gradeD);
	String gradeCountF = String.format(" F:%5d|", gradeF);
	String numOfData   = String.format("|%9d", grades.size());
	String high 	   = String.format("%3d", GetHighGrade());
	String low  	   = String.format("%3d", GetLowGrade());
	String range       = "|" + low + " to " + high;
	String sum  	   = String.format("|%5d", GetSumGrade());
	String mean 	   = String.format("|%6.2f", GetMeanGrade());
	String variance    = String.format("|%10.3f", GetVariance());
	String stdDeviation= String.format("|%19.3f|", GetStandardDeviation());
	String beautyBand  =
	"__________________________________________________________________";

	String resultString = beautyBand + "\n" +
	"|# Of Data| Sum |  Range   | Mean | Variance | Standard Deviation|\n" +
	numOfData + sum + range + mean + variance + stdDeviation + "\n" +
	beautyBand + "\n" + "|Number Of Each:    |" + 
	gradeCountA + gradeCountB + gradeCountC + gradeCountD + gradeCountF + 
        "\n" + beautyBand + "\n";
        return resultString;
    }
}
