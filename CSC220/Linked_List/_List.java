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

	// Head will reference first element, tail will reference last element
	// curr will reference current element, num_items is how many items are in the list
	private Node head;
	private Node tail;
	private Node curr;
	private int num_items;

	// constructor
	// remember that an empty list has a "size" of 0 and its "position" is at -1
	public List()
	{
		// Initialize num_items to 0 and position to -1
		this.num_items = 0;
		this.head = new Node();
		this.tail = new Node();
		this.curr = new Node();
	}

	// copy constructor
	// clones the list l and sets the last element as the current
	public List(List l)
	{
	}

	// navigates to the beginning of the list
	public void First()
	{
		this.curr.setLink(this.head.getLink());
	}

	// navigates to the end of the list
	// the end of the list is at the last valid item in the list
	public void Last()
	{
		this.curr.setLink(this.tail.getLink());
	}

	// navigates to the specified element (0-index)
	// this should not be possible for an empty list
	// this should not be possible for invalid positions
	public void SetPos(int pos)
	{
		// If invalid position  or  empty list
		if(pos > this.num_items || this.num_items == 0)
			return;
		
		// Move curr to beginning of list, increase curr pos number of times
		curr.setLink(head.getLink());
		for(int i = 0; i < pos; i++)
			this.curr.setLink(this.curr.getLink().getLink());
	}

	// navigates to the previous element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Prev()
	{
		if(this.num_items == 0)
			return;

		// Make node to hold last position
		Node last_pos = new Node();
		last_pos.setLink(this.curr.getLink());
		
		// Move curr to beginning of list, while our last position is 
		// not equal to one element in front of curr
		this.curr.setLink(head.getLink());
		while(last_pos.getLink() != this.curr.getLink().getLink())
			this.curr.setLink(this.curr.getLink().getLink());


	}

	// navigates to the next element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Next()
	{
		// if list is empty    or   curr is at the tail
		if(this.GetSize() == 0 || this.curr.getLink() == this.tail.getLink())
			return;

		// Set curr to the next element
		this.curr.setLink(this.curr.getLink().getLink());

	}

	// returns the location of the current element (or -1)
	public int GetPos()
	{
		int pos_counter = 0;
		// If our list is empty
		if(this.GetSize() == 0)
			return -1;

		// Node pos_node to set endpoint for while loop
		// Lets us know when we are at the position we want to know
		Node pos_node = new Node();
		pos_node.setLink(this.curr.getLink());

		// Move curr to head
		this.curr.setLink(this.head.getLink());

		// while our curr isn't at our pos, move curr to next element
		while(this.curr.getLink() != pos_node.getLink())
		{
			this.curr.setLink(this.curr.getLink().getLink());
			pos_counter ++;
		}
		
		// return position
		return pos_counter;
	}

	// returns the value of the current element (or -1)
	public int GetValue()
	{
		if(this.GetSize() == 0)
			return -1;
		// Return the data of the Node that curr references
		return this.curr.getLink().getData();
	}

	// returns the size of the list
	// size does not imply capacity
	public int GetSize()
	{
		return this.num_items;
	}

	// inserts an item before the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertBefore(int data)
	{
		// If list is full, return
		if(this.num_items == this.MAX_SIZE)
			return;

		// Create new node that we will insert
		Node new_node = new Node();
		new_node.setData(data);

		// Make temp_node to hold old curr position
		Node temp_node = new Node();
		temp_node.setLink(this.curr.getLink());

		// Make the new node point to the node our curr is at
		if(this.GetSize() != 0)
			new_node.setLink(this.curr.getLink());
		else
		{
			this.head.setLink(new_node);
			this.tail.setLink(new_node);
		}

		// Move curr to beggining
		this.First();

		if(this.GetSize() < 2)	
		{

			// Move curr to element in front of new_node
			// while element after curr is not temp_node, curr ++
			while(this.curr.getLink().getLink() != temp_node.getLink())
				this.curr.setLink(this.curr.getLink().getLink());

			// Make element at curr reference new_node
			this.curr.getLink().setLink(new_node);

		}


		this.Next();
		// Increment num_items
		this.num_items ++;
	}

	// inserts an item after the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertAfter(int data)
	{
		// If list is full, return
		if(this.GetSize() == this.MAX_SIZE)
			return;

		// Made new_node, set data
		Node new_node = new Node();
		new_node.setData(data);

		// First time setup
		if(this.GetSize() == 0)
		{
			// Set this.head to reference new_node, this.curr to reference new_node
			this.head.setLink(new_node);
			this.curr.setLink(this.head.getLink());
			this.tail.setLink(new_node);
		}
		else
			new_node.setLink(this.curr.getLink().getLink());
		
		if(this.curr.getLink() == this.tail.getLink())
			tail.setLink(new_node);
		// Increment num_items
		this.num_items ++;
	}

	// removes the current element (collapsing the list)
	// this should not be possible for an empty list
	public void Remove()
	{
		// if list is empty, return
		if(this.num_items <= 0)
			return;
		// Make node curr references have the same data as node after curr
		this.curr.getLink().setData(this.curr.getLink().getLink().getData());

		// Make node curr references point to node two spots after curr
		this.curr.getLink().setLink(this.curr.getLink().getLink().getLink());

		// Decrement num_items
		this.num_items --;

	}

	// replaces the value of the current element with the specified value
	// this should not be possible for an empty list
	public void Replace(int data)
	{
		// if list is empty, return
		if(this.num_items <= 0)
			return;
		this.curr.getLink().setData(data);
	}

	// returns if the list is empty
	public boolean IsEmpty()
	{
		if(this.num_items <= 0)
			return true;
		return false;
	}

	// returns if the list is full
	public boolean IsFull()
	{
		if(this.GetSize() == this.MAX_SIZE)
			return true;
		return false;
	}

	// returns if two lists are equal (by value)
	public boolean Equals(List l)
	{
		// Set l.curr and this.curr to reference respective heads
		this.curr.setLink(this.head.getLink());
		l.curr.setLink(l.head.getLink());

		// If they aren't the same length, return false
		if(this.num_items != l.num_items)
			return false;

		// While curr is not at the at the tail
		// Move through lists, comparing values 
		while(this.curr.getLink() != this.tail.getLink())
		{
			// If values don't match, return false
			if(this.curr.getLink().getData() != l.curr.getLink().getData())
				return false;

			// Increment this.curr and l.curr
			this.curr.setLink(this.curr.getLink().getLink());
			l.curr.setLink(l.curr.getLink().getLink());
		}
		return true;

	}

	// returns the concatenation of two lists
	// l should not be modified
	// l should be concatenated to the end of *this
	// the returned list should not exceed MAX_SIZE elements
	// the last element of the new list is the current
	public List Add(List l)
	{
		// Create new list that will be the concatenation of this. and l.
		List new_list = new List();

		// Set curr for both lists to beginning
		this.First();
		l.First();

		// Loop through this. , adding elements to new_list as we go
		for(int i = 0; i < this.GetSize(); i++)
		{
			new_list.InsertAfter(this.GetValue());
			this.Next();
		}

		// Loop through l. , adding elements to new_list until we run out of items to add or new_list.GetSize() is maxed out 
		for(int i = 0; i < l.GetSize() && new_list.GetSize() <= new_list.MAX_SIZE; i++)
		{
			new_list.InsertAfter(l.GetValue());
			l.Next();
		}

		return new_list;
	}

	// returns a string representation of the entire list (e.g., 1 2 3 4 5)
	// the string "NULL" should be returned for an empty list
	public String toString()
	{
		// Node to hold curr position
		Node temp_node = new Node();
		temp_node.setLink(this.curr.getLink());

		// Instantiate and initialize string to return
		String string = "";
		
		// If this is empty, return "NULL"
		if(this.GetSize() == 0)
			return "NULL";
		
		// Move curr to beginning of this
		this.First();
		
		// Append data to string
		for(int i = 0; i < this.GetSize(); i++, this.Next())
			string += Integer.toString(this.GetValue());	

		// Reset curr
		this.curr.setLink(temp_node.getLink());

		return string;
	}
}








