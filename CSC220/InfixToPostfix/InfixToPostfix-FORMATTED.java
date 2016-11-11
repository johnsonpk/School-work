/* ***************************************************
 * Pablo Johnson
 * 11-11-16
 * Infix To Postfix
 *
 * Program to take infix math notation and convert it
 * to postfix notation. Only accepts single digit numbers
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
		}
		catch (Exception e) {}
	}

	// given an infix queue, this method converts it to a postfix queue
	public static Queue<Character> Convert(Queue<Character> infix)
	{

		// Initialize our stack for our operands and queue for characters
		Stack<Character> oper = new Stack<Character>();
		Queue<Character> postfix = new Queue<Character>();

		// Initialize token and op
		char token, op;

		while (!infix.IsEmpty())
		{
			// Get next token from infix queue
			token = infix.Dequeue();

			// If our token is an operand, enqueue it to our postfix queue
			if (IsOperand(token))
			{
				postfix.Enqueue(token);
			}

			// If our token is a right paren, enqueue operators from oper onto postfix until a left paren is found
			else if (token == ')')
			{
				op = oper.Pop();

				while (op != '(')
				{
					postfix.Enqueue(op);
					op = oper.Pop();
				}
			}
			else
			{
				// Had to put these in so that a null value isn't assigned to a char
				op = (oper.Peek() == null) ? (0) : (oper.Peek());

				// While stack(op) >= infix(token) and oper isn't empty
				while (StackPriority(op) >= InfixPriority(token) && (oper.Peek() != null))
				{
					// Remover op from oper
					op = oper.Pop();

					// Enqueue op to postfix
					postfix.Enqueue(op);

					// Set op to the next operator
					op = (oper.Peek() == null) ? (0) : (oper.Peek());
				}

				// Push operator onto oper
				oper.Push(token);
			}
		}
		// While we still have operators in oper, pop them and enqueue them onto postfix
		while (!oper.IsEmpty())
		{
			op = oper.Pop();
			postfix.Enqueue(op);
		}
		return postfix;

	}

	// given a postfix queue, this method calculates the numeric result using a stack
	public static double Calculate(Queue<Character> postfix)
	{
		// Create stack that we will use to evaluate postfix
		Stack<Double> stackEval = new Stack<Double>();

		// Instantiate result and ch
		double result;
		char ch;

		// While we still have characters in postfix
		while (!postfix.IsEmpty())
		{
			// Get ch from postfix
			ch = postfix.Dequeue();

			// If ch is an operator
			if (!IsOperand(ch))
			{
				// Perform correct operation
				switch (ch)
				{
					case '*':
						result = stackEval.Pop() * stackEval.Pop();
						stackEval.Push(result);
						break;
					case '/':
						result =  1 / (stackEval.Pop() / stackEval.Pop());
						stackEval.Push(result);
						break;
					case '^':
						result = Math.pow(stackEval.Pop(), stackEval.Pop());
						stackEval.Push(result);
						break;
					case '-':
						result = 0 - (stackEval.Pop() - stackEval.Pop());
						stackEval.Push(result);
						break;
					case '+':
						result = stackEval.Pop() + stackEval.Pop();
						stackEval.Push(result);
						break;
					default:
						System.err.println(ch);
						System.err.println("IsOperand is broken :(");
						System.exit(1);
				}
			}

			// If not an operator, typecast to double and push onto stackEval
			else
			{
				stackEval.Push((double)(ch) - 48);
			}

		}
		// Since result will be the only thing in stackEval, pop it and return it
		return stackEval.Pop();
	}

	// given a character from an expression, this method determines whether or not it is an operand
	// it's ok to use the simple char primitive type here
	public static boolean IsOperand(char c)
	{
		// If c is in the range for numeric characters, return true
		if ('0' <= c && c <= '9')
			return true;
		return false;
	}

	// given a character that represents an operator from an expression, this method returns its infix priority
	// it's ok to use the simple char primitive type here
	public static int InfixPriority(char c)
	{
		// Return priority of c
		switch (c)
		{
			case '(':
				return 4;
			case '^':
				return 3;
			case '*':
			case '/':
				return 2;
			case '+':
			case '-':
				return 1;
			default:
				return 0;
		}
	}

	// given a character that represents an operator from an expression, this method returns its stack priority
	// since the character comes form the stack, we use the Character object here (since the stack could be empty which would return null)
	public static int StackPriority(Character c)
	{

		// Return priority of c
		switch (c)
		{
			case '^':
			case '*':
			case '/':
				return 2;
			case '+':
			case '-':
				return 1;
			default:
				return 0;
		}
	}
}
