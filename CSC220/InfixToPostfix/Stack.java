/*********************************
** Pablo Johnson
** CSC220
** Stack
*********************************/


public class Stack<StackType>
{
	// Use Generic list as backend
	private List<StackType> l;

	// Constructor
	public Stack()
	{
		l = new List<StackType>();
	}

	// Clones stack to another stack
	public Stack(Stack<StackType> s)
	{
		l = new List<StackType>(s.l);
		l.First();
	}

	// Pushes data onto stack
	public void Push(StackType data)
	{
		l.InsertBefore(data);
	}

	// Removes data from top of stack 
	public StackType Pop()
	{
		StackType t = Peek();

		l.Remove();

		return t;
	}

	// See what is on the top of the stack
	public StackType Peek()
	{
		return l.GetValue();
	}

	// Return height of stack
	public int Size()
	{
		return l.GetSize();
	}

	// Return true if stack is empty
	public boolean IsEmpty()
	{
		return l.IsEmpty();
	}

	// Return true if stack is full
	public boolean IsFull()
	{
		return l.IsFull();
	}

	// Return true if stacks are equal
	public boolean Equals(Stack s)
	{
		return l.Equals(s.l);
	}

	// Creates new stack from two stacks
	public Stack<StackType> Add(Stack<StackType> s)
	{
		Stack<StackType> s2 = new Stack<StackType>(this);

		s2.l = s2.l.Add(s.l);

		s2.l.First();

		return s2;
	}

	// Returns string representation of stack
	public String toString()
	{
		return l.toString();
	}
}
