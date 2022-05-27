/**Class: MethodContainer.java
   * @author Joshua Sims
   * @version 1.0
   * This class will be called by Method Tester and its methods will solve the problems 
   * from MethodTester.  
   * 
   */
public class MethodContainer
{
	/**Method: add
	 * This method should add the two numbers and return the sum.
	 * @param first
	 * @param second
	 * @return int containing the sum of the two arguments
	 */
	public int add(int first, int second)
	{
		return first + second;
	}

	/**Method: subtract
	 * This method should return the difference of two numbers.
	 * @param first
	 * @param second
	 * @return int containing the difference of the two numbers
	 */
	public int subtract(int first, int second) { return first - second; }

	/**Method: firstWord
	 * This method should return the first word in a sentence.  For example:
	 * "This is a sentence." should return This
	 * @param sentence
	 * @return String containing the first word in the sentence
	 */
	public String firstWord(String sentence)
	{
		String arr[] = sentence.split(" ", 2);
		return arr[0];
	}

	/**Method lastWord
	 * This method should return the last word in a sentence.
	 * For this problem assume the last character is always a punctuation mark
	 * For example:
	 * "This is a sentence." should return sentence
	 * @param sentence
	 * @return
	 */
	public String lastWord(String sentence)
	{
		String lastWord = sentence.substring(sentence.lastIndexOf(" ")+1);
		return lastWord.substring(0, lastWord.length()-1);
	}

	/**Method: buildString
	 * This method should return the concatenation of the first and second word
	 * passed as arguments.  The words should be separated by a single space.
	 * For example:
	 * if first = "Fred" and second = "Flintstone" should return "Fred Flintstone"
	 * @param first
	 * @param second
	 * @return the concatenation of the two Strings separated by a space
	 */
	public String buildString(String first, String second)
	{
		return first + " " + second;
 	}

	/**Method: repeatString
	 * returns the str repeated number times.  Each str should be on its own line
	 * @param str
	 * @param number
	 * @return a String containing the String passed in repeated number times.
	 */
	public String repeatString(String str, int number)
	{
		String sentences = "";
		for(int i = 0; i < number; i++) {
			sentences += str + "\n";
		}
		return sentences;
	}
}
