
public class fibonacciSequence
{

	// iterative Fibonacci method implementation
	public static int fibonacci(int number)
	{
		int num1 = 0;
		int num2 = 1;
		int sum = 0;

		for (int i = 0; i < number; i++)
		{
			// Fibonacci number is sum of previous two Fibonacci numbers in the sequence
			sum = num1 + num2;
			num1 = num2;
			num2 = sum;

		}
		return num1;
	}

	// recursive Fibonacci method implementation
	public static int recursiveFibonacci(int number)
	{
		// switch statement
		switch (number)
		{
			// base case: when number is equal 0
			case 0:
				return 0;
			// base case: when number is equal 1
			case 1:
				return 1;
			// recursive case: when number is greater than 1
			default:
				return recursiveFibonacci(number - 1) + recursiveFibonacci(number - 2);
		}

	}

	public static void main(String[] args)
	{

		int number = 10;
		System.out.print("This is recursive Fibonacci: ");
		// for (int i = 0; i <= number; i++)
		// {
		// // calling recursiveFibonnaci for each index of the Fibonacci
		// sequence
		// System.out.print(recursiveFibonacci(i) + " ");
		// }

		System.out.print(recursiveFibonacci(number) + " ");
		System.out.println();
		System.out.print("This is Fibonacci using iteration: ");
		System.out.println(fibonacci(10));

	}

}
