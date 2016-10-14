/* ***************************************************
 * Pablo Johnson
 * 10-14-16
 * List Class
 *
 * Linked List class that holds integers.
 * Abstract data type, so you technically shouldn't need to look at the code
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
		// Initialize num_items
		this.num_items = 0;
	}

	// copy constructor
	// clones the list l and sets the last element as the current
	public List(List l)
	{
		// Temp node we can reset l.curr at the end
		Node temp_node = l.curr;
		l.curr = l.head;

		// For each item in List l
		for (int i = l.num_items; i > 0; i --)
		{
			// Insert into List this the data at our l.curr
			this.InsertAfter(l.curr.getData());

			// Increment curr
			l.curr = l.curr.getLink();
		}

		// Reset l.curr
		l.curr = temp_node;
	}

	// navigates to the beginning of the list
	public void First()
	{
		// move curr to head
		this.curr = this.head;
	}

	// navigates to the end of the list
	// the end of the list is at the last valid item in the list
	public void Last()
	{
		// I guess I didn't actually need this....
		// Like I didn't have anything here until I started commenting and saw it was empty.

		this.curr = this.tail;
	}

	// navigates to the specified element (0-index)
	// this should not be possible for an empty list
	// this should not be possible for invalid positions
	public void SetPos(int pos)
	{
		// if list is empty
		if (this.num_items == 0)
			return;
		// If invalid pos
		if (pos >= this.num_items)
			return;

		// Move curr to head
		this.curr = this.head;

		// Move curr to next element pos times
		for (; pos > 0; pos --)
			this.curr = this.curr.getLink();
	}

	// navigates to the previous element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Prev()
	{
		// If list is empty
		if (this.num_items == 0)
			return;
		// If already at head
		if (this.head == this.curr)
			return;

		// Move curr to previous position
		this.SetPos(this.GetPos() - 1);
	}

	// navigates to the next element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Next()
	{
		// If list is empty
		if (this.num_items == 0)
			return;
		// If already at tail
		if (this.curr == this.tail)
			return;

		// Move curr to next element
		this.curr = this.curr.getLink();
	}

	// returns the location of the current element (or -1)
	public int GetPos()
	{
		// If list is empty
		if (this.num_items == 0)
			return -1;

		// Make counter node we can count how far we are from head
		Node counter = new Node();
		// Move counter to head
		counter = this.head;

		// Do a max of num_items times
		for (int i = 0; i < num_items; i++)
		{
			// If we are at our curr position, return times we've moved
			if (counter == this.curr)
				return i;
			// else, move counter to next element
			counter = counter.getLink();
		}

		// Debug statement, should never reach here.
		return -99999;
	}

	// returns the value of the current element (or -1)
	public int GetValue()
	{
		// If list is empty
		if (this.num_items == 0)
			return -1;

		// Return data at current element
		return this.curr.getData();
	}

	// returns the size of the list
	// size does not imply capacity
	public int GetSize()
	{
		// Return number of items in list
		return this.num_items;
	}

	// inserts an item before the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertBefore(int data)
	{
		// If list is full
		if (this.num_items == this.MAX_SIZE)
			return;

		// Make new node that we will insert into list
		Node new_node = new Node();
		// Set data to new_node
		new_node.setData(data);

		// If first item in list
		if (this.num_items == 0)
		{
			// Make head, curr, tail all equal new_node
			this.head = new_node;
			this.curr = new_node;
			this.tail = new_node;

			// Increment num_items
			this.num_items ++;
			return;
		}

		// If at head of list
		if (this.head == this.curr)
		{
			// Make head equal new node
			this.head = new_node;

			// Make new node reference current element
			new_node.setLink(this.curr);

			// Move curr to new node
			this.curr = new_node;

			// Increment num_items
			this.num_items ++;
			return;
		}

		// Else

		// make new node reference curr
		new_node.setLink(this.curr);

		// Move our curr to element before where new node will be
		this.SetPos(this.GetPos() - 1);

		// Make element before new node reference new node
		this.curr.setLink(new_node);

		// Move curr to new node
		this.curr = new_node;

		// Increment num_items
		this.num_items ++;
	}

	// inserts an item after the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertAfter(int data)
	{
		// If list is full
		if (this.num_items == this.MAX_SIZE)
			return;

		// Make new node that will be inserted after curr
		Node new_node = new Node();

		// Set data to new node
		new_node.setData(data);

		// If first element in list
		if (this.num_items == 0)
		{
			// Make head, curr, and tail all equal new node
			this.head = new_node;
			this.curr = new_node;
			this.tail = new_node;

			// Increment num_items
			this.num_items ++;
			return;
		}

		// if at end of list
		if (this.curr == this.tail)
			// Make tail equal new node
			this.tail = new_node;
		else
			// Make new node reference element after new node
			new_node.setLink(this.curr.getLink());

		// make element at curr reference new node
		this.curr.setLink(new_node);

		// move curr to new node
		this.curr = new_node;

		// increment num_items
		this.num_items ++;
	}

	// removes the current element (collapsing the list)
	// this should not be possible for an empty list
	public void Remove()
	{
		// If already empty, return
		if (this.num_items == 0)
			return;

		// If only one item in list
		if (this.num_items == 1)
		{
			// set head, tail, curr to null.
			this.head = null;
			this.tail = null;
			this.curr = null;

			// Decrement num_items
			this.num_items --;
			return;
		}

		// If removing element at head
		if (this.head == this.curr)
		{
			// Just make head equal node after element
			this.head = this.curr.getLink();

			// Curr to new head
			this.curr = this.head;

			// Decrement num_items
			this.num_items --;
			return;
		}

		// If removing element at tail
		if (this.tail == this.curr)
		{
			// Move curr to element in front of curr
			this.SetPos(this.GetPos() - 1);

			// Remove reference to deleted element
			this.curr.setLink(null);

			// Set tail to curr
			this.tail = this.curr;

			// Decrement num_items
			this.num_items --;
			return;
		}

		// BigO(1) efficiency
		// Essentially deep copy the data and reference at the next element to current element.
		this.curr.setData(this.curr.getLink().getData());
		this.curr.setLink(this.curr.getLink().getLink());

		// Decrement num_items
		this.num_items--;
		return;

	}

	// replaces the value of the current element with the specified value
	// this should not be possible for an empty list
	public void Replace(int data)
	{
		// If list is empty, return
		if (this.num_items == 0)
			return;

		// Replace data at curr with new data
		this.curr.setData(data);
	}

	// returns if the list is empty
	public boolean IsEmpty()
	{
		// Check if empty
		return (this.num_items == 0);

		// Turns out List Test never actually checks if an empty list is empty. The more you know
		//return false;
	}

	// returns if the list is full
	public boolean IsFull()
	{
		// Check if full
		return (this.num_items == this.MAX_SIZE);
	}

	// returns if two lists are equal (by value)
	public boolean Equals(List l)
	{
		// If List this is not equal in size to List l, return false
		if (this.num_items != l.num_items)
			return false;

		// Nodes to hold curr position so we can reset it at the end
		Node this_node = this.curr;
		Node l_node = l.curr;

		// Move curr to head
		this.curr = this.head;
		l.curr = l.head;

		// Do num_items number of times
		for (int i = this.num_items; i > 0; i --)
		{
			// If data at l.curr and this.curr are not equal ...
			if (l.curr.getData() != this.curr.getData())
			{
				// .. reset curr for both lists
				this.curr = this_node;
				l.curr = l_node;

				return false;
			}

			//Increment curr for both lists
			l.curr = l.curr.getLink();
			this.curr = this.curr.getLink();
		}

		// Reset curr for both lists
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
		// if List this is already full, return
		if (this.num_items == this.MAX_SIZE)
			return this;

		// If List this is empty, return List l
		if (this.num_items == 0)
			return l;

		// Deep copy Lists this and l to new_list/2
		List new_list = new List(this);
		List new_list2 = new List(l);

		// Make new_list num_items = size of both lists
		new_list.num_items += new_list2.num_items;

		// Make new_list tail reference new_list2 head
		new_list.tail.setLink(new_list2.head);

		// move new_list tail to new_list2 tail
		new_list.tail = new_list2.tail;

		// Move curr to tail
		new_list.curr = new_list.tail;

		// Remove excess items
		// while our list is too large, remove the last element
		for (; new_list.num_items > new_list.MAX_SIZE; new_list.Remove());

		return new_list;

	}

	// returns a string representation of the entire list (e.g., 1 2 3 4 5)
	// the string "NULL" should be returned for an empty list
	public String toString()
	{
		// make node so we can reset curr at end
		Node temp_node = this.curr;

		// If  list is empty, return "NULL"
		if (this.num_items == 0)
			return "NULL";

		// String to be returned
		String string = "";

		// Move curr to head
		this.curr = this.head;

		// for each item in list
		for (int i = 0; i < this.num_items; i ++)
		{
			// Add data to string
			string += this.curr.getData() + " ";

			// Move curr to next element
			this.curr = this.curr.getLink();
		}

		// Reset curr
		this.curr = temp_node;

		return string;
	}
}
