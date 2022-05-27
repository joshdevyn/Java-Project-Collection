/**Class: QuickSort
* @author Joshua Sims
* @version 1.0
 *
* This class Implementation of the Quick Sort algorithm
*
* Purpose: Demonstrate quick sort
*/

package Sorts;

public class QuickSort
{
	int [] sortArray;
	
	/** Method: QuickSort
	 * Constructor for QuickSort demonstration
	 * @param inArray the array to be sorted
	 */
	public QuickSort(int[] inArray)
	{
		sortArray = inArray;
	}
	
	/** Method: sort
	 * Public interface for QuickSort
	 * @param from the int starting position
	 * @param to the int ending position
	 * @return void
	 */
	public void sort(int from, int to)
	{
		//base case.  This is the exit case for the recursion.  When the low index is >/ to high index, simply return
		if (from >= to)
		{
			return;
		}
		int p = partition(from, to);
		sort (from, p);
		sort(p+1, to);
	}
	
	/** Method: partition
	 * Divides array into sub-areas to be sorted
	 * @param from the int starting position
	 * @param to the int ending position
	 * @return void
	 */
	private int partition(int from, int to)
	{
		//assign the value contained in the position of the lowest reference to the pivot variable.  
		//this it the value we are comparing
		int pivot = sortArray[from];
		//subtract 1 from lowest index to allow following while to actually work on correct value and iterate through the array 
		int loIndex = from - 1;
		//add 1 to high index for the following while
		int hiIndex = to + 1;
		while (loIndex < hiIndex)
		{
			//change low index to traverse the array
			loIndex++;
			//traverse the sorted area and record where it goes if pivot is less than a number already arranged
			while (sortArray[loIndex] < pivot)
			{
				loIndex++;
			}
			//change high index to traverse the array
			hiIndex--;
			//traverse the sorted area and record where it goes if pivot is greater than a number already arranged
			while (sortArray[hiIndex] > pivot)
			{
				hiIndex--;
			}
			if (loIndex<hiIndex)
			{
				changeNum(loIndex, hiIndex);
			}
		}
		return hiIndex;
	}
	
	/** Method: changeNum
	 * swaps numbers when needed
	 * @param i the int low value position
	 * @param j the int high value position
	 * @return void
	 */
	private void changeNum(int i, int j)
	{
		int temp = sortArray[i];
		sortArray[i] = sortArray[j];
		sortArray[j] = temp;
	}

}
