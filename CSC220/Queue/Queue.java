public class Queue<QueueType>
{
	private List<QueueType> l;

	public Queue()
	{
		l = new List<QueueType>();
	}

	public Queue(Queue<QueueType> s)
	{
		l = new List<QueueType>(s.l);
		l.First();
	}

	public void Enqueue(QueueType data)
	{
		l.Last();
		l.InsertAfter(data);
	}

	public QueueType Dequeue()
	{
		l.First();
		QueueType t = Peek();

		l.Remove();
		
		return t;
	}

	public QueueType Peek()
	{
		l.First();
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
	
	public boolean Equals(Queue s)
	{
		return l.Equals(s.l);
	}

	public Queue<QueueType> Add(Queue<QueueType> s)
	{
		Queue<QueueType> s2 = new Queue<QueueType>(this);

		s2.l = s2.l.Add(s.l);

		s2.l.First();

		return s2;
	}

	public String toString()
	{
		return l.toString();
	}
}
