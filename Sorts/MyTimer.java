/**Class: MyTimer
* @author Joshua Sims
* @version 1.0
*
* This class Implementation of a stop watch class to be used to time sorting algorithms
*
* Purpose: Timer for sorting
*/

package Sorts;

public class MyTimer
{
	private long start;
	private long end;

	/** Method: MyTimer
	 * Constructor for MyTimer class
	 */
	public MyTimer()
	{
		reset();
	}
	
	/** Method: reset
	 * resets the variables in the MyTimer class
	 * @return none
	 */
	public void reset()
	{
		start = 0;
		end = 0;
	}
	
	/** Method: startTimer
	 * starts the timer, records the start time
	 * @return none
	 */
	public void startTimer()
	{
		start = System.currentTimeMillis();
	}
	
	/** Method: stop
	 * stops the timer, records the stop time
	 * @return none
	 */
	public void stop()
	{
		end = System.currentTimeMillis();
	}
	
	/** Method: getElapsedTime
	 * Calculates how long the timer ran
	 * @return long - the number of milliseconds the timer ran
	 */
	public long getElapsedTime()
	{
		return end - start;
	}
}
