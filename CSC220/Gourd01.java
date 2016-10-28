import java.util.Scanner;

public class Gourd01
{
	
	public static void main(String args[])
	{
		// Instantiate variables
		int[] leading_digit = new int[10];
		int sum = 0;
		int num;
		String nextLine;
		Scanner scanner = new Scanner(System.in);

		// While we have a line to be read ...
		while(scanner.hasNextLine())
		{
			// Grab the next line
			nextLine = scanner.nextLine();
			
			//  parse the first character to an integer 
			num = Integer.parseInt(nextLine.substring(0,1));

			// For whatever integer we have, add one to that place in our leading_digit array
			leading_digit[num] += 1;
		} 

		for(int i: leading_digit)
		{
			// calculate sum of leading_digits
			sum += i;
		}

		System.out.println("-------------------------------");
		System.out.println("Leading Digit   Count         %");
		System.out.println("-------------------------------");
		
		for(int i = 0; i < 10; i++)
		{
			
			num = 3 - (Integer.toString(100 * leading_digit[i]/sum).length());
			nextLine = "";
			

			for(int j = 0; j < num; j ++)
			{
				// make nextLine equal the num * " "
				nextLine += " ";
			}
			// Print output
			System.out.printf("%d\t\t%d\t%s%.2f%%\n",i, leading_digit[i],nextLine, (100 * leading_digit[i] / (double)sum));
		
		}
		
		// Do the same as above, but for our final sum line
		nextLine = "";
		num = 3 - Integer.toString(sum).length();

		for(int j = 0; j < num; j ++)
		{
			nextLine += " ";
		}
		
		System.out.println("-------------------------------");
		System.out.printf("TOTAL\t\t%d\t%.2f%%\n", sum, (double)sum / sum * 100);

		System.out.println("===============================");

	}
}
