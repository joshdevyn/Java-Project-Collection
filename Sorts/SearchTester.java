/**Class: SearchTester
 * @author Joshua Sims
 * @version 1.0
 *
 * This class provides a test harness for searches
 */

package Sorts;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class SearchTester
{
	private int[] numArray;
	private Scanner keyboard;
	static final int LIST_SIZE = 1000;
	
	public SearchTester()
	{
		numArray = new int[LIST_SIZE];
	}
	
	/** Method: main
	 * Entry point for search demonstration
	 * @param args String[] command line arguments
	 */
	public static void main(String[] args)
	{
		SearchTester st = new SearchTester();
		int input = 1;
		int selection;
		st.keyboard = new Scanner(System.in);
		
		
		int find = st.enterFind();
				
		for (int index = 0; index < LIST_SIZE; index++)
		{
			st.numArray[index] = index + 1;
		}
		
		while(input > 0)
		{
			System.out.println("Select the search to perform");
			System.out.println("1 Sorted Search");
			System.out.println("2 Unsorted Search");
			System.out.println("3 Enter a new number to search for");
			System.out.println("\n4 Quit.");
			selection = st.keyboard.nextInt();
			
			switch(selection)
			{
				case 1:
				{
					QuickSort qs = new QuickSort(st.numArray);
					st.printArray(st.numArray);
					
					st.binary(find);
					break;
				}
				case 2:
				{
					Random rnd = new Random();
					for (int index = 0; index < st.numArray.length; index++)
					{
						int rndIndex = rnd.nextInt(st.numArray.length);
						int temp = st.numArray[rndIndex];
						st.numArray[rndIndex] = st.numArray[index];
						st.numArray[index] = temp;
					}
					st.printArray(st.numArray);
					st.linear(find);
					break;
				}
				case 3: 
				{
					find = st.enterFind(); 
					break;
				}
				default:
				{
					System.exit(0);
				}
			}
		}
		
	}
	
	/** Method: linear
	 * Calling code for the linear search algorithm
	 * @param find the value to find
	 */
	private void linear(int find)
	{
		LinearSearch ls = new LinearSearch(numArray);
		int count = ls.search(find);
		
		if (count >= 0)
		{
			System.out.println("Your number was found after " + count + " searches.");
		}
		else
		{
			System.out.println("Your number was not found after " + -count + " searches.");
		}
		ls = null;
	}

	/** Method: binary
	 * Calling code for the binary search algorithm
	 * @param find the value to find
	 */
	private void binary(int find)
	{
		
		BinarySearch bs = new BinarySearch(numArray);
		int count = bs.search(find);
		
		if (count >= 0)
		{
			System.out.println("Your number was found after " + count + " searches.");
		}
		else
		{
			System.out.println("Your number was not found after " + -count + " searches.");
		}
		bs = null;
	}
	
	/** Method: enterFind
	 * Allows the user to enter a value to search for
	 * @return int, the value entered by the user
	 */
	private int enterFind()
	{
		int find = 0;
		boolean valid = false;
		while (!valid)
		{
			try
			{
				System.out.println("Enter a number between 1 and " + LIST_SIZE);
				find = keyboard.nextInt();
				valid = true;
			}
			catch(InputMismatchException ime)
			{
				System.out.println("You must enter an integer");
			}
			keyboard.nextLine();
		}
		return find;
	}
	
	/** Method: printArray
	  * 
	  * @param nums
	  */
	private void printArray(int[] nums)
	{
		for (int index = 0; index < nums.length; index++)
		{
			System.out.print(nums[index] + "\t");
			if (index % 9 == 0)
			{
				System.out.println("");
			}
		}
	}
}
