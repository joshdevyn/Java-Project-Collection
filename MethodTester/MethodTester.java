/**Class: MethodTester.java
   * @author Joshua Sims
   * @version 1.0
   * This class provides the driver for testing the MethodContainer class.
   * 
   */

package MethodTester;

import java.util.Scanner;

public class MethodTester
{
	public static void main(String[] args)
	{
		MethodContainer methods = new MethodContainer();
		boolean again = true;
		Scanner input = new Scanner(System.in);
		do
		{
			int probNum = 0;
			System.out.println("1.  Run your addition method.");
			System.out.println("2.  Run your subtraction method.");
			System.out.println("3.  Run your first word method.");
			System.out.println("4.  Run your last word method.");
			System.out.println("5.  Run your string building method.");
			System.out.println("6.  Run you string repeat method.");
			
			System.out.println("Please select the problem you would like to run");
			probNum = input.nextInt();
			input.nextLine();
			switch(probNum)
			{
				case 1:
				{
					if (4 != methods.add(2,2))
					{
						System.out.println("Your answer was not correct. You returned " + methods.add(2,2) + " instead of 4.");
						break;
					}
					if (47 != methods.add(21,26))
					{
						System.out.println("Your answer was not correct. You returned " + methods.add(21,26) + " instead of 47.");
						break;
					}
					if (-100 != methods.add(10,-110))
					{
						System.out.println("Your answer was not correct. You returned " + methods.add(10,-100) + " instead of -100.");
						break;
					}
					System.out.println("You got them all right!");
					break;
				}
				case 2:
				{
					if (0 != methods.subtract(2,2))
					{
						System.out.println("Your answer was not correct. You returned " + methods.subtract(2,2) + " instead of 0.");
						break;
					}
					if (5 != methods.subtract(26,21))
					{
						System.out.println("Your answer was not correct. You returned " + methods.subtract(26,21) + " instead of 5.");
						break;
					}
					if (110 != methods.subtract(10,-100))
					{
						System.out.println("Your answer was not correct. You returned " + methods.subtract(10,-100) + " instead of 110.");
						break;
					}
					System.out.println("You got them all right!");
					break;
				}
				case 3:
				{
					if (!methods.firstWord("This is the first sentence.").equals("This"))
					{
						System.out.println("Your answer was not correct. You returned \"" + 
								methods.firstWord("This is the first sentence.") + "\" instead of \"This\".");
						break;
					}
					if (!methods.firstWord("What is your name?").equals("What"))
					{
						System.out.println("Your answer was not correct. You returned \"" + 
								methods.firstWord("What is your name?") + "\" instead of \"What\".");
						break;
					}
					if (!methods.firstWord("Sally got all of her problems correct.").equals("Sally"))
					{
						System.out.println("Your answer was not correct. You returned \"" + 
								methods.firstWord("Sally got all of her problems correct.") + "\" instead of \"Sally\".");
						break;
					}
					System.out.println("You got them all right!");
					break;
				}
				case 4:
				{
					if (!methods.lastWord("This is the first sentence.").equals("sentence"))
					{
						System.out.println("Your answer was not correct. You returned \"" + 
								methods.lastWord("This is the first sentence.") + "\" instead of \"sentence\".");
						break;
					}
					if (!methods.lastWord("What is your name?").equals("name"))
					{
						System.out.println("Your answer was not correct. You returned " + 
								methods.lastWord("What is your name?") + " instead of name.");
						break;
					}
					if (!methods.lastWord("Sally got all of her problems correct.").equals("correct"))
					{
						System.out.println("Your answer was not correct. You returned " + 
								methods.lastWord("Sally got all of her problems correct.") + " instead of correct.");
						break;
					}
					System.out.println("You got them all right!");
					break;
				}
				case 5:
				{
					if (!methods.buildString("Fred", "Flintstone").equals("Fred Flintstone"))
					{
						System.out.println("Your answer was not correct. You returned " + 
								methods.buildString("Fred", "Flintstone") + " instead of Fred Flintstone.");
						break;
					}
					if (!methods.buildString("Clark", "Kent").equals("Clark Kent"))
					{
						System.out.println("Your answer was not correct. You returned " + 
								methods.buildString("Clark", "Kent") + " instead of Clark Kent.");
						break;
					}
					if (!methods.buildString("Peter", "Parker").equals("Peter Parker"))
					{
						System.out.println("Your answer was not correct. You returned " + 
								methods.buildString("Peter", "Parker") + " instead of Peter Parker.");
						break;
					}
					System.out.println("You got them all right!");
					break;
				}
				case 6:
				{
					String rightAnswer = "This is the string to repeat\nThis is the string to repeat\nThis is the string to repeat\nThis is the string to repeat\nThis is the string to repeat\n";
					if (!methods.repeatString("This is the string to repeat", 5).equals(rightAnswer))
					{
						System.out.println("Your answer was not correct. You returned \n" + 
								methods.repeatString("This is the string to repeat", 5) + "\n instead of \n" + rightAnswer);
						break;
					}
					rightAnswer = "Got it right!!!\nGot it right!!!\nGot it right!!!\n";
					if (!methods.repeatString("Got it right!!!", 3).equals(rightAnswer))
					{
						System.out.println("Your answer was not correct. You returned " + 
								methods.repeatString("Got it right!!!", 3) + " instead of \n" + rightAnswer);
						break;
					}
					System.out.println("You got them all right!");
					break;
				}
				
			}
			
			System.out.println("Do you want to run this again? (Y/N)");
			String another = input.nextLine();
			if(another.charAt(0) != 'y' && another.charAt(0) != 'Y')
			{
				again = false;
			}
			
		}while (again);
		input.close();
	}

}
