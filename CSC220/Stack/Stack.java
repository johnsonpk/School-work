public class Stack<StackType>
{
	private List<StackType> l;

	public Stack()
	{
		l = new List<StackType>();
	}

	public Stack(Stack<StackType> s)
	{
		l = new List<StackType>(s.l);
		l.First();
	}

	public void Push(StackType data)
	{
		l.InsertBefore(data);
	}

	public StackType Pop()
	{
		StackType t = Peek();

		l.Remove();
		
		return t;
	}

	public StackType Peek()
	{
		return l.GetValue();
	}
	
	public int Size()
	{
		return l.GetSize();
	}

	public boolean IsEmpty()
	{
		return l.IsEmpty();
	}

	public boolean IsFull()
	{
		return l.IsFull();
	}
	
	public boolean Equals(Stack s)
	{
		return l.Equals(s.l);
	}

	public Stack<StackType> Add(Stack<StackType> s)
	{
		Stack<StackType> s2 = new Stack<StackType>(this);

		s2.l = s2.l.Add(s.l);

		s2.l.First();

		return s2;
	}

	public String toString()
	{
		return l.toString();
	}
}
