/* ***************************************************
 * <your name>
 * <the date>
 * <the file name>
 *
 * <a simple, short program/class description>
 *************************************************** */

// the Node class
class Node
{
	private int data;
	private Node link;

	// constructor
	public Node()
	{
		this.data = 0;
		this.link = null;
	}

	// accessor and mutator for the data component
	public int getData()
	{
		return this.data;
	}

	public void setData(int data)
	{
		this.data = data;
	}

	// accessor and mutator for the link component
	public Node getLink()
	{
		return this.link;
	}

	public void setLink(Node link)
	{
		this.link = link;
	}
}

// the List class
public class List
{
	public static final int MAX_SIZE = 50;

	private Node head;
	private Node tail;
	private Node curr;
	private int num_items;
	
	// Debug for basic print statements and control flow
	public static final boolean DEBUG = true;
	// Debug for reference checking 
	public static final boolean DEBUG_REF = true;

	// constructor
	// remember that an empty list has a "size" of 0 and its "position" is at -1
	public List()
	{
		if(DEBUG)
			System.err.println("-----------------------------List Constructor");
		// Initialize head, curr, and num_items
		this.head = new Node();
		this.curr = new Node();
		this.num_items = 0;

		if(DEBUG_REF)
			System.err.printf("Head:%h\nCurr:%h\nNum Items:%d\n",this.head.getLink(),
				this.curr.getLink(), num_items);
	}

	// copy constructor
	// clones the list l and sets the last element as the current
	public List(List l)
	{
	}

	// navigates to the beginning of the list
	public void First()
	{
		if(DEBUG)
			System.err.println("-----------------------------First");
		if(DEBUG)
			System.err.printf("Navigating to head\n");
		
		if(DEBUG_REF)
			System.err.printf("Head:%h\nCurr:%h\n", this.head.getLink(), this.curr.getLink());

		// Make curr reference the same node as head
		this.curr.setLink(this.head.getLink());
	}

	// navigates to the end of the list
	// the end of the list is at the last valid item in the list
	public void Last()
	{
	}

	// navigates to the specified element (0-index)
	// this should not be possible for an empty list
	// this should not be possible for invalid positions
	public void SetPos(int pos)
	{
		if(DEBUG)
			System.err.println("-----------------------------SetPos");

		if(this.IsEmpty() || pos >= this.GetSize())
			return;
		
		// Move curr to head
		this.First();
		
		// Move curr to pos
		for(int i = 0; i < pos; i ++)
			this.Next();

		if(DEBUG) System.err.printf("Curr pos:%d\nPos Desired:%d\n",this.GetPos(), pos);


	}

	// navigates to the previous element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Prev()
	{
	}

	// navigates to the next element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Next()
	{
		if(DEBUG)
			System.err.println("-----------------------------Next");
		// If list is empty, do nothing	
		if(this.IsEmpty())
			return;
		
		if(DEBUG)
			System.err.println("Incrementing curr");

		// Make curr reference the next node
		this.curr.setLink(this.curr.getLink().getLink());
	}

	// returns the location of the current element (or -1)
	public int GetPos()
	{
		if(DEBUG)
			System.err.println("-----------------------------GetPos");

		// If list is empty, return -1
		if(this.IsEmpty())
			return -1;

		if(DEBUG)
			System.err.println("Finding position");

		// Set position counter
		int position = 0;

		// Instantiate and initialize temp_node to hold current position
		Node temp_node = new Node();
		temp_node.setLink(this.curr.getLink());

		// Move curr to head
		this.First();
		
		// While our curr is not where it was, increment position and curr
		while(this.curr.getLink() != temp_node.getLink())
		{
			if(DEBUG_REF)
				System.err.printf("Curr:%h\nTemp Node:%h\n",curr.getLink(),temp_node.getLink());

			this.Next();
			position ++;
		}

		if(DEBUG_REF)
			System.err.printf("Pos:%d\n", position);

		// Return integer value of position
		return position;
	}

	// returns the value of the current element (or -1)
	public int GetValue()
	{
		if(DEBUG)
			System.err.println("-----------------------------GetValue");
		// If list is empty, return -1
		if(this.IsEmpty())
			return -1;
		
		if(DEBUG)
			System.err.println("Getting data");

		// Else, return data curr is at
		return this.curr.getLink().getData();
	}

	// returns the size of the list
	// size does not imply capacity
	public int GetSize()
	{
		if(DEBUG)
		{
			System.err.println("-----------------------------GetSize");
			System.err.printf("Num_items = %d\n", this.num_items);
		}

		// Returns number of items in list
		return this.num_items;
	}

	// inserts an item before the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertBefore(int data)
	{
		if(DEBUG)
			System.err.println("-----------------------------InsertBefore");

		if(DEBUG_REF)
			System.err.printf("Head:%h\nCurr:%h\nData:%d\n",this.head.getLink(),
				this.curr.getLink(),data);

		// If list is full, do nothing
		if(this.IsFull())
			return;

		
		// If list is empty or only has one element
		if(this.IsEmpty() || (this.GetSize() == 1) || (this.GetPos() == 0))
		{
			// Initialize and instantiate new node	
			Node new_node = new Node();
			new_node.setData(data);

			// Set head to reference new node
			this.head.setLink(new_node);

			// Set curr to reference new node
			this.curr.setLink(new_node);
			
			// If only one element
			if(!this.IsEmpty())
				// Set new_node to reference curr
				new_node.setLink(this.curr.getLink());


			// Increment num_items
			this.num_items ++;

			if(DEBUG)
				System.err.println("____________Inserting before, list was empty | had one element | insert at beginning");
			if(DEBUG_REF)
				System.err.printf("Head:%h\nCurr:%h\nNew_Node:%h\n",this.head.getLink(),
					this.curr.getLink(),new_node);
			return;
		}

		System.err.printf("List: %s",this.toString());
		// Basically navigate to node in front of curr, then insert after
		this.First();
		this.SetPos(this.GetPos() - 1);
		this.InsertAfter(data);

	}

	// inserts an item after the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertAfter(int data)
	{
		if(DEBUG)
			System.err.println("-----------------------------InsertAfter");
		
		// If list is full, do nothing
		if(this.IsFull())
			return;

		if(DEBUG)
		{
			System.err.printf("Inserting %d\n",data);
		}
			

		// Instantiate and initialize new node
		Node new_node = new Node();
		
		// Give new data to new node
		new_node.setData(data);

		// If list is empty, we need to do some stuff
		if(this.IsEmpty())
		{
			// Head, curr will all now refence new_node, since it is the only item in list
			this.head.setLink(new_node);
			this.curr.setLink(new_node);

			// Increment num_items
			this.num_items++;

			return;
		}

		// Set new_node to reference node after curr
		new_node.setLink(this.curr.getLink().getLink());

		// Make node at curr reference new node
		this.curr.getLink().setLink(new_node);

		// Move curr to new node
		this.Next();

		// Increment num_items
		this.num_items ++;
	}

	// removes the current element (collapsing the list)
	// this should not be possible for an empty list
	public void Remove()
	{
		if(DEBUG)
			System.err.println("-----------------------------Remove");
	}

	// replaces the value of the current element with the specified value
	// this should not be possible for an empty list
	public void Replace(int data)
	{
		if(DEBUG)
			System.err.println("-----------------------------Replace");
	}

	// returns if the list is empty
	public boolean IsEmpty()
	{
		if(DEBUG)
			System.err.println("-----------------------------IsEmpty");
		if(DEBUG_REF)
			System.err.printf("IsEmpty:%s\n",(this.num_items == 0) ? "true":"false");
		return (this.GetSize() == 0); 
	}

	// returns if the list is full
	public boolean IsFull()
	{
		if(DEBUG)
			System.err.println("-----------------------------IsFull");
		if(DEBUG_REF)
			System.err.printf("IsFull:%s\n",(this.num_items == this.MAX_SIZE) ? "true" : "false");

		return (this.GetSize() == this.MAX_SIZE);
	}

	// returns if two lists are equal (by value)
	public boolean Equals(List l)
	{
		if(DEBUG)
			System.err.println("-----------------------------Equals");
		return false;
	}

	// returns the concatenation of two lists
	// l should not be modified
	// l should be concatenated to the end of *this
	// the returned list should not exceed MAX_SIZE elements
	// the last element of the new list is the current
	public List Add(List l)
	{
		if(DEBUG)
			System.err.println("-----------------------------Add");
		return null;
	}

	// returns a string representation of the entire list (e.g., 1 2 3 4 5)
	// the string "NULL" should be returned for an empty list
	public String toString()
	{
		if(DEBUG)
			System.err.println("-----------------------------toString");
		String string = "";

		if(this.GetSize() <= 0)
			return "NULL";

		this.First();
		for(int i = 0; i < this.GetSize(); i ++)
		{
			string += Integer.toString(this.GetValue()) + " ";
			this.Next();
		}
		

		return string;


	}
}
