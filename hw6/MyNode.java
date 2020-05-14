import java.util.Iterator;

public final class MyNode<E> implements Iterable<E>{
	private E data;
	private MyNode<E> next;

	// constructors
	public MyNode(E val)
	{
		data = val;
		next = null;
	}

	public MyNode(E val, MyNode<E> node)
	{
		data = val;
		next = node;
	}
	// getters
	public E getData()
	{
		return data;
	}

	public MyNode<E> getNext()
	{
		return next;
	}

	// setters
	public void setData(E val)
	{
		data = val;
	}

	public void setNext(MyNode<E> node)
	{
		next = node;
	}

	public MyNodeIterator<E> iterator()
	{
		return new MyNodeIterator<E> (this);
	}

	// the iterator inner class
	public class MyNodeIterator<E> implements Iterator<E> {
		private MyNode<E> p;

		// constructor
		public MyNodeIterator(MyNode<E> n)
		{
			p = n;
		}

		public boolean hasNext()
		{
			return p != null;
		}

		public E next()
		{
			E data = p.getData();
			p = p.getNext();
			return data;
		}
	}

}