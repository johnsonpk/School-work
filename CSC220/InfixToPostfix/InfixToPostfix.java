/* ***************************************************
 * <your name>
 * <the date>
 * <the file name>
 *
 * <a simple, short program/class description>
 *************************************************** */

import java.io.*;

public class InfixToPostfix
{
	// the main function is just a driver!
	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line;

			// read input, one expression at a time
			while ((line = br.readLine()) != null)
			{
				// convert each infix expression to its postfix equivalent
				Queue<Character> infix = new Queue<Character>();
				Queue<Character> postfix = new Queue<Character>();

				// build the infix queue and display it
				for (int i=0; i<line.length(); i++)
					infix.Enqueue(line.charAt(i));
				System.out.println(infix);
				// convert it to its postfix equivalent and display it
				postfix = Convert(infix);
				System.out.println(postfix);
				// calculate the result and display it
				System.out.println(Calculate(postfix));
				System.out.println();
			}

			br.close();
		} catch (Exception e) {}
	}

	// given an infix queue, this method converts it to a postfix queue
	public static Queue<Character> Convert(Queue<Character> infix)
	{
	}

	// given a postfix queue, this method calculates the numeric result using a stack
	public static double Calculate(Queue<Character> postfix)
	{
	}

	// given a character from an expression, this method determines whether or not it is an operand
	// it's ok to use the simple char primitive type here
	public static boolean IsOperand(char c)
	{
	}

	// given a character that represents an operator from an expression, this method returns its infix priority
	// it's ok to use the simple char primitive type here
	public static int InfixPriority(char c)
	{
	}

	// given a character that represents an operator from an expression, this method returns its stack priority
	// since the character comes form the stack, we use the Character object here (since the stack could be empty which would return null)
	public static int StackPriority(Character c)
	{
	}
}
