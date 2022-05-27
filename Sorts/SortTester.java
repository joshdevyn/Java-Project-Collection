/**Class: SortTester
* @author Joshua Sims
* @version 1.0
*
* This class the driver class for the classroom demonstration of sort algorithms
*
* Purpose: Tests different sort algorithms
*/

package Sorts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SortTester
{
	public static void main(String[] args)
	{
//		 int[] numArray = {5,3,2,6,4,1,3,7};
		int[] numArray = new int[100000];
		for (int index = 0; index < numArray.length; index++)
		{
			numArray[index] = (int) (Math.random() * 100000);
		}
		int[] unsorted = numArray.clone();
		
		int sortType = 0;
		while (sortType >= 0) 
		{
			System.out.println("What type of sort do you want to perform?");
			System.out.println("1  Selection Sort");
			System.out.println("2  Insertion Sort");
			System.out.println("3  Mergesort Sort");
			System.out.println("4  Quicksort Sort");
			System.out.println("\nEnter a negative number to quit");
		
			Scanner keyboard = new Scanner(System.in);
			try
			{
				sortType = keyboard.nextInt();
			}
			catch (InputMismatchException ime)
			{
				System.out.println("Invalid entry.");
				sortType = 10;
			}
			if (sortType != 10)
			{
				MyTimer timer = new MyTimer();
				
				switch(sortType)
				{
					case 1:
					{
						SelectionSort selSort = new SelectionSort(numArray);
	
						timer.reset();					
						timer.startTimer();
						int [] newSort = selSort.sort();
						timer.stop();
						
						System.out.println("There were " + newSort.length + " items in the list." );
						
						System.out.println("Original     Sorted");
						break;
					}
					case 2:
					{
						InsertionSort insSort = new InsertionSort(numArray);
						
						
						timer.reset();					
						timer.startTimer();
						int [] newSort = insSort.sort();
						timer.stop();
						
						System.out.println("There were " + newSort.length + " items in the list." );
						
						System.out.println("Original     Sorted");
						break;
					}
					case 3:
					{
						MergeSort mergeSort = new MergeSort(numArray);
											
						timer.reset();					
						timer.startTimer();
						mergeSort.sort();
						timer.stop();
						
						System.out.println("There were " + mergeSort.sortArray.length + " items in the list." );
						
						System.out.println("Original     Sorted");
						break;
					}
					case 4:
					{
						QuickSort qSort = new QuickSort(numArray);
						timer.reset();					
						timer.startTimer();
						qSort.sort(0,numArray.length-1);
						timer.stop();
						
						System.out.println("There were " + qSort.sortArray.length + " items in the list." );
						
						System.out.println("Original     Sorted");
						break;
					}
					default:
					{
						
					}
				}
				if (sortType > 0)
				{
					for (int index = 0; index < numArray.length; index++)
					{
						System.out.println(   unsorted[index]+ "\t\t" + numArray[index]);			
					}
					System.out.println("This algorithm ran for " + timer.getElapsedTime() + " milliseconds.");
					numArray = unsorted.clone();
				}
				System.arraycopy(unsorted, 0, numArray, 0, unsorted.length);
			}
		} //end while
	}

}
