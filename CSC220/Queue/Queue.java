/***************************************************
** Pablo Johnson
** CSC 220
** Queue
****************************************************/

public class Queue<QueueType>
{
	// use queue as backend
	private List<QueueType> l;

	// Constructor
	public Queue()
	{
		l = new List<QueueType>();
	}

	// Clone queue that is passed in
	public Queue(Queue<QueueType> s)
	{
		l = new List<QueueType>(s.l);
		l.First();
	}

	// Add to the tail of the queue
	public void Enqueue(QueueType data)
	{
		l.Last();
		l.InsertAfter(data);
	}

	// Remove from the front of the queue
	public QueueType Dequeue()
	{
		l.First();
		QueueType t = Peek();

		l.Remove();

		return t;
	}

	// See what is at the front / head of queue
	public QueueType Peek()
	{
		l.First();
		return l.GetValue();
	}

	// Return size of queue
	public int Size()
	{
		return l.GetSize();
	}

	// Return if queue is empty
	public boolean IsEmpty()
	{
		return l.IsEmpty();
	}

	// Return if queue is full
	public boolean IsFull()
	{
		return l.IsFull();
	}

	// Return if one queue equals another
	public boolean Equals(Queue s)
	{
		return l.Equals(s.l);
	}

	// Append a queue to another
	public Queue<QueueType> Add(Queue<QueueType> s)
	{
		Queue<QueueType> s2 = new Queue<QueueType>(this);

		s2.l = s2.l.Add(s.l);

		s2.l.First();

		return s2;
	}

	// Returns string representation of queue
	public String toString()
	{
		return l.toString();
	}
}
