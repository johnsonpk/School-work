public class MyTest
{
	public static void main(String[] args)
	{
		List a = new List();
		List b = new List();

		for(int i = 0; i < 25; i++)
		{
			a.InsertAfter(i);
			b.InsertBefore(i);
		}
		
		System.out.println("a: " + a);
		System.out.println("b: " + b);

		System.out.println("apos: " + a.GetPos());	
		System.out.println("bpos: " + b.GetPos());	

		a.Remove();
		b.Remove();

		System.out.println("a: " + a);
		System.out.println("b: " + b);

	}
}

