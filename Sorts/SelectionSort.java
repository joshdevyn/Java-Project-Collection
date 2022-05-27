/**Class: SelectionSort
* @author Joshua Sims
* @version 1.0
*
* This class Implementation of the Selection Sort algorithm
*
* Purpose: Demonstrate selection sort
*/

package Sorts;

public class SelectionSort
{
	private int[] sortArray;

	/** Method: SelectionSort
	 * Constructor for SelectionSort demonstration
	 * @param inArray the array to be sorted
	 */
	public SelectionSort(int[] inArray)
	{
		sortArray = inArray;
	}
	
	/** Method: sort
	 * Public interface for SelectionSort
	 * @return void
	 */
	public int[] sort()
	{
		for (int outer = 0; outer < sortArray.length - 1; outer++)
		{
			int min = outer;
			for (int inner = outer + 1; inner < sortArray.length; inner++)
			{
				if (sortArray[inner] < sortArray[min])
				{
					min = inner;
				}

			}
			changeNum(min, outer);
		}
		return sortArray;
	}
	
	/** Method: changeNum
	 * private method for changing the order of the elements
	 * @param min - the index of the lower number
	 * @param index - the index of the higher number
	 * @return none
	 */
	private void changeNum(int min, int index)
	{
		int temp = sortArray[min];
		sortArray[min] = sortArray[index];
		sortArray[index] = temp;
	}

}
