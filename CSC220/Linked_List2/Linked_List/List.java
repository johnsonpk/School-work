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

	// constructor
	// remember that an empty list has a "size" of -1 and its "position" is at -1
	public List()
	{
		this.num_items = 0;
	}

	// copy constructor
	// clones the list l and sets the last element as the current
	public List(List l)
	{
		Node temp_node = l.curr;
		l.curr = l.head;

		for(int i = l.num_items; i > 0; i --)
		{
			this.InsertAfter(l.curr.getData());
			l.curr = l.curr.getLink();
		}

		l.curr = temp_node;
	}

	// navigates to the beginning of the list
	public void First()
	{
		this.curr = this.head;
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
		if(this.num_items == 0)
			return;
		if(pos >= this.num_items)
			return;

		this.curr = this.head;
		for(;pos > 0; pos --)
			this.curr = this.curr.getLink();
	}

	// navigates to the previous element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Prev()
	{
		if(this.num_items == 0)
			return;
		if(this.head == this.curr)
			return;

		this.SetPos(this.GetPos() - 1);
	}

	// navigates to the next element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Next()
	{
		if(this.num_items == 0)
			return;
		if(this.curr == this.tail)
			return;

		this.curr = this.curr.getLink();
	}

	// returns the location of the current element (or -1)
	public int GetPos()
	{
		if(this.num_items == 0)
			return -1;

		Node counter = new Node();
		counter = this.head;

		for(int i = 0; i < num_items; i++)
		{
			if(counter == this.curr)
				return i;
			counter = counter.getLink();
		}
		return -99999;
	}

	// returns the value of the current element (or -1)
	public int GetValue()
	{
		if(this.num_items == 0)
			return -1;
		return this.curr.getData();
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
		if(this.num_items == this.MAX_SIZE)
			return;

		Node new_node = new Node();
		new_node.setData(data);

		if(this.num_items == 0)
		{
			this.head = new_node;
			this.curr = new_node;
			this.tail = new_node;
			this.num_items ++;
			return;
		}

		if(this.head == this.curr)
		{
			this.head = new_node;
			new_node.setLink(this.curr);
			this.curr = new_node;
			this.num_items ++;
			return;
		}

		new_node.setLink(this.curr);
		this.SetPos(this.GetPos() - 1);
		this.curr.setLink(new_node);
		this.curr = new_node;
		this.num_items ++;
	}

	// inserts an item after the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertAfter(int data)
	{
		if(this.num_items == this.MAX_SIZE)
			return;

		Node new_node = new Node();
		new_node.setData(data);

		if(this.num_items == 0)
		{
			this.head = new_node;
			this.curr = new_node;
			this.tail = new_node;
			this.num_items ++;
			return;
		}
		
		if(this.curr == this.tail)
			this.tail = new_node;
		else
			new_node.setLink(this.curr.getLink());
		
		this.curr.setLink(new_node);
		this.curr = new_node;

		this.num_items ++;
	}

	// removes the current element (collapsing the list)
	// this should not be possible for an empty list
	public void Remove()
	{
		if(this.num_items == 0)
			return;

		if(this.num_items == 1)
		{
			this.head = null;
			this.tail = null;
			this.curr = null;
			this.num_items --;
			return;
		}

		if(this.head == this.curr)
		{
			this.head = this.curr.getLink();
			this.curr = this.head;
			this.num_items --;
			return;
		}

		if(this.tail == this.curr)
		{
			this.SetPos(this.GetPos() - 1);
			this.curr.setLink(null);
			this.tail = this.curr;
			this.num_items --;
			return;
		}

		this.SetPos(this.GetPos() - 1);
		this.curr.setLink(this.curr.getLink().getLink());
		this.curr = this.curr.getLink();
		this.num_items --;

	}

	// replaces the value of the current element with the specified value
	// this should not be possible for an empty list
	public void Replace(int data)
	{
		if(this.num_items == 0)
			return;

		this.curr.setData(data);
	}

	// returns if the list is empty
	public boolean IsEmpty()
	{
		return false;
	}

	// returns if the list is full
	public boolean IsFull()
	{
		return (this.num_items == this.MAX_SIZE);
	}

	// returns if two lists are equal (by value)
	public boolean Equals(List l)
	{
		if(this.num_items != l.num_items)
			return false;

		Node this_node = this.curr;
		Node l_node = l.curr;
		this.curr = this.head;
		l.curr = l.head;

		for(int i = this.num_items; i > 0; i --)
		{
			if(l.curr.getData() != this.curr.getData())
			{
				this.curr = this_node;
				l.curr = l_node;
				return false;
			}

			l.curr = l.curr.getLink();
			this.curr = this.curr.getLink();
		}

		this.curr = this_node;
		l.curr = l_node;
		
		return true;
	}

	// returns the concatenation of two lists
	// l should not be modified
	// l should be concatenated to the end of *this
	// the returned list should not exceed MAX_SIZE elements
	// the last element of the new list is the current
	public List Add(List l)
	{
		if(this.num_items == this.MAX_SIZE)
			return this;

		List new_list = new List(this);

		Node this_node = this.curr;
		Node l_node = l.curr;

		this.curr = this.head;
		l.curr = l.head;

		int limit = ((l.num_items + new_list.num_items) >= 50) ? l.MAX_SIZE : (l.num_items + new_list.num_items);
		for(int i = new_list.num_items; i < limit; i ++)
		{
			new_list.InsertAfter(l.curr.getData());
			l.curr = l.curr.getLink();
		}

		new_list.curr = new_list.tail;

		this.curr = this_node;
		l.curr = l_node;

		return new_list;

	}

	// returns a string representation of the entire list (e.g., 1 2 3 4 5)
	// the string "NULL" should be returned for an empty list
	public String toString()
	{
		Node new_node = this.curr;
		if(this.num_items == 0)
			return "NULL";

		String string = "";

		this.curr = this.head;

		for(int i = 0; i < this.num_items; i ++)
		{
			string += this.curr.getData() + " ";
			this.curr = this.curr.getLink();
		}
		
		this.curr = new_node;
		return string;
	}
}
