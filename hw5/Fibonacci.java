import java.util.*;

class SubsetOutputFib
{
	public static void main(String[] args)
	{
		// read from command line arguments
		int be = Integer.parseInt(args[0]);
		int en = Integer.parseInt(args[1]);
		// check that en >= be
		if(be > en || be < 1)
		{
			System.out.println("Bound error!");
			System.exit(0);
		}

		// for calculating and outputing fib sequence
		String mark;
		int lo = 1;
		int hi = 1;

		// before going to the calculation, check if be == 1
		// if true, then output the first fibo sequence
		if(be == 1)
		{
			System.out.println("1: 1");
		}

		for(int i = 2; i <= en; i++)
		{
			// set the mark for even numbers
			if(hi % 2 == 0)
			{
				mark = " *";
			}
			else
			{
				mark = "";
			}

			// only print if i is greater than be
			if(i >= be)
			{
				System.out.println(i + ": " + hi + mark);
			}

			// do the fibo calculation
			hi = lo + hi;
			lo = hi - lo;

		}


	}
}

class ImprovedFibonacci
{
	static final int MAX_INDEX = 9;
	public static void main(String[] args)
	{
		int lo = 1;
		int hi = 1;
		String mark;

		System.out.printf("1: %d\n", lo);
		for(int i = 2; i <= MAX_INDEX; i++)
		{
			if(hi % 2 == 0)
				mark = " *";
			else
				mark = "";

			System.out.printf("%d: %d%s\n", i, hi, mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}