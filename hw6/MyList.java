import java.util.*;

public class MyList<E> implements Iterable<E>, Cloneable, Comparable<MyList<E>>
{

	private MyNode<E> n;
	private int length;

	// constructors
	MyList()
	{
		n = null;
		length = 0;
	}

	MyList(Iterable<E> iterable)
	{
		MyNode<E> ptr = null;
		length = 0;

		Vector<E> v = new Vector<E>();
		for(E i : iterable)
		{
			v.add(i);
		}

		for(int i = v.size()-1; i >= 0; i--)
		{
			ptr = new MyNode<E>(v.get(i), ptr);
			length ++;
		}

		n = ptr;
	}

	public int getLength()
	{
		return length;
	}

	// implementing Iterable<E>
	public Iterator<E> iterator()
	{
		return n.iterator();
	}

	// overriding Object's clone() method. Also implementing Clonable.
	public MyList<E> clone()
	{
		MyList<E> clone = new MyList<E>();

		// go over every item in the liked list with the head n.
		for (E i : n)
		{
			// push the value into the clone list
			clone.push(i);
		}

		return clone.reverse();
	}


	// implementing Comparable
	// compares the length
	public int compareTo(MyList<E> rhs)
	{
		return getLength() - rhs.getLength();
	}

	// check the length and contents(order doesn't matter)
	public boolean equals(MyList<E> rhs)
	{
		// check if rhs is null first
		if(rhs == null)
		{
			return false;
		}

		// compare their length first
		// if length not equal, return false.
		if(this.hashCode() != rhs.hashCode())
		{
			return false;
		}

		// then compare the elements in both lists
		// first put them into two vectors and sort them
		Vector<E> v1 = new Vector<E>();
		for(E i : this)
		{
			v1.add(i);
		}

		Vector<E> v2 = new Vector<E>();
		for(E i : rhs)
		{
			v2.add(i);
		}

		// nested for loop to check if v1 and v2 are equal
		for(int i = 0; i < v1.size(); i++)
		{

			for(int j = 0; j < v2.size(); j++)
			{
				// found a match element
				if( v1.get(i).equals(v2.get(j)) )
				{
					// remove the matched element in v2 and break the loop
					v2.remove(j);
					break;
				}

				// now v2 is exhausted ans still not found the element
				if(j == v2.size()-1)
				{
					return false;
				}
			}
		}

		// finally returnt true
		return true;

	}

	// simply returns the length of the list.
	public int hashCode()
	{
		return length;
	}	


	public MyList<E> reverse()
	{

		MyList<E> rev = new MyList<E>();

		if(length == 0)
		{
			return rev;
		}


		for(E i : this)
		{
			rev.push(i);
		}

		this.n = rev.n;
		return this;
	}


	public String toString()
	{
		if(n == null)
		{
			return "";
		}


		String ret = "";
		for(E i : this)
		{
			ret += " (" + i + ") ->";
		}

		// some special actions on string to make it look nice
		ret = ret.substring(2, ret.length() - 3);
		ret = "[(head: " + ret + "]";

		return ret;
	}


	public void push(E item)
	{
		// when the list was initially empty, make it a new node
		if(length == 0)
		{
			n = new MyNode<E>(item); 
		}
		// otherwise, prepend the element to the head
		else
		{
			n = new MyNode<E>(item, n);
		}
		// modify the length
		length++;
	}


	public E pop()
	{
		if(n == null)
		{
			throw new NoSuchElementException();
		}
		// get the data from the first node for return
		E elem = n.getData();
		// set the head to the next, thus deleting the current head
		n = n.getNext();
		// decrement the length by 1
		length--;

		return elem;
	}

	public E peek()
	{
		if(n == null)
		{
			throw new NoSuchElementException();
		}

		return n.getData();
	}

}