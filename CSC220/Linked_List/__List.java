/* ***************************************************
 * Pablo Johnson
 * 10-14-16
 * Linked List
 *
 * Backend for a linked list. 
 * Since it is an abstract data type, you don't really need to read this :)
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

	private Node head = new Node();
	private Node tail = new Node();
	private Node curr = new Node();
	private int num_items;

	// constructor
	// remember that an empty list has a "size" of -1 and its "position" is at -1
	public List()
	{
		num_items = -1;
		curr.setData(-1);
		
	}

	// copy constructor
	// clones the list l and sets the last element as the current
	public List(List l)
	{
		Node temp_node = new Node();

		List new_list = new List();
		l.SetPos(0);
		while(l.GetPos() < l.GetSize());	
			{
				new_list.InsertAfter(l.GetValue());
			}
	}

	// navigates to the beginning of the list
	public void First()
	{
		curr = head.getLink();
	}

	// navigates to the end of the list
	// the end of the list is at the last valid item in the list
	public void Last()
	{
		curr = tail.getLink();
	}

	// navigates to the specified element (0-index)
	// this should not be possible for an empty list
	// this should not be possible for invalid positions
	public void SetPos(int pos)
	{
		if(head.getLink() == null)
			return;
		curr = head.getLink();
		for(int i = 0; i < pos; i++)
		{
			curr = curr.getLink();
		}
		
	}

	// navigates to the previous element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Prev()
	{
		int pos_counter = 0;
		Node last_pos = curr.getLink();
		curr = head.getLink();
		while(last_pos.getLink() != curr.getLink().getLink())
		{
			pos_counter++;
			curr = curr.getLink();
		}

	}

	// navigates to the next element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Next()
	{
		curr = curr.getLink();
	}

	// returns the location of the current element (or -1)
	public int GetPos()
	{
		if(curr.getData() == -1)
			return curr.getData();

		int pos_counter = 0;
		Node last_pos = curr.getLink();
		curr = head.getLink();
		while(last_pos.getLink() != curr.getLink())
		{
			pos_counter ++;
			curr = curr.getLink();
		}
		return pos_counter;

	}

	// returns the value of the current element (or -1)
	public int GetValue()
	{
		return curr.getData();
	}

	// returns the size of the list
	// size does not imply capacity
	public int GetSize()
	{
		if(head.getLink() == null)
			return -1;

		Node temp_node = new Node();
		temp_node.setLink(curr);
		curr = head.getLink();
		int counter = 1;
		while(curr.getLink() != null)
		{
			curr = curr.getLink();
			counter ++;
		}
		curr = temp_node;
		return counter;
	}


	// inserts an item before the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertBefore(int data)
	{
		// Node to hold curr so we can reset it at the end.
		Node temp_node = new Node();
		temp_node.setData(curr);
		temp_node.setLink(curr);
		
		// Create new node that we will insert
		Node new_node = new Node();
		new_node.setData(data);
		new_node.setLink(curr.getLink());


		// Set node before new node to point to new node
		Node last_pos = new Node();
		last_pos.setData(curr.getData());
		last_pos.setLink(curr.getLink());
		

		// Set 
		curr = head.getLink();
		while(last_pos.getLink() != curr.getLink().getLink())
		{
			curr = curr.getLink();
		}
		curr.setLink(new_node);
		curr = temp_node;



	}

	// inserts an item after the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertAfter(int data)
	{
		if(IsFull())
			return;

		Node new_node = new Node();
		new_node.setData(data);
		new_node.setLink(curr.getLink());
		curr.setLink(new_node);
		
	}

	// removes the current element (collapsing the list)
	// this should not be possible for an empty list
	public void Remove()
	{
		if(head.getLink() == null)
			return;

		curr.setData(curr.getLink().getData());
	}

	// replaces the value of the current element with the specified value
	// this should not be possible for an empty list
	public void Replace(int data)
	{
	}

	// returns if the list is empty
	public boolean IsEmpty()
	{
		return true;
	}

	// returns if the list is full
	public boolean IsFull()
	{
		return true;
	}

	// returns if two lists are equal (by value)
	public boolean Equals(List l)
	{
		return true;
	}

	// returns the concatenation of two lists
	// l should not be modified
	// l should be concatenated to the end of *this
	// the returned list should not exceed MAX_SIZE elements
	// the last element of the new list is the current
	public List Add(List l)
	{
		return null;
	}

	// returns a string representation of the entire list (e.g., 1 2 3 4 5)
	// the string "NULL" should be returned for an empty list
	public String toString()
	{
		return null;
	}
}
