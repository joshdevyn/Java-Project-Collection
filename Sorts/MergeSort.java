/**Class: MergeSort
* @author Joshua Sims
* @version 1.0
*
* This class Implementation of the Merge Sort algorithm
*
* Purpose: Demonstrate merge sort
*/

package Sorts;


public class MergeSort
{
	protected int[] sortArray;

	/** Method: MergeSort
	 * Constructor for MergeSort demonstration
	 * @param inArray int[] the array to be sorted
	 */
	public MergeSort(int[] inArray)
	{
		sortArray = inArray;
	}
	
	/** Method: sort
	 * Public interface for MergeSort
	 * @return void
	 */
	public void sort()
	{
		if (sortArray.length <= 1)
		{
			return;
		}
		int[] firstArray = new int[sortArray.length/2];
		int[] secondArray = new int[sortArray.length - firstArray.length];
		
		//copy to divided arrays
		for (int index = 0; index < firstArray.length; index++)
		{
			firstArray[index] = sortArray[index];
		}
		for (int index = 0; index < secondArray.length; index++)
		{
			secondArray[index] = sortArray[firstArray.length + index];
		}
		
		//recursive calls
		MergeSort firstSorter = new MergeSort(firstArray);
		MergeSort secondSorter = new MergeSort(secondArray);
		
		firstSorter.sort();
		secondSorter.sort();
		
		merge(firstArray, secondArray);

	}
	
	/** Method: merge 
	 * private method to merge the two lists in the recursive MergeSort
	 * @param first the first array to merge
	 * @param second the second array to merge
	 * @return int[] - the merged array in sorted order
	 */
	private int[] merge(int[] first, int[]second)
	{
		int firstIndex = 0;
		int secondIndex = 0;
		
		int nextPos = 0;
		
		while (firstIndex < first.length && secondIndex < second.length)
		{
			if (first[firstIndex] < second[secondIndex])
			{
				sortArray[nextPos] = first[firstIndex];
				firstIndex++;
			}
			else
			{
				sortArray[nextPos] = second[secondIndex];
				secondIndex++;
			}
			nextPos++;
		}
		
		while (firstIndex < first.length)
		{
			sortArray[nextPos++] = first[firstIndex++];
		}
		
		while (secondIndex < second.length)
		{
			sortArray[nextPos++] = second[secondIndex++];
		}
		return sortArray;
	}
}
