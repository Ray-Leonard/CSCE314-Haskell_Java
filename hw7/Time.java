// Jiarui Li
// 827003805
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Time
{
	public Lock l;  // lock to prevent race condition
    public Condition c;  // condition variable to prevent dead lock
    public int t;		// the time that will be on track

    Time()
    {
    	l = new ReentrantLock();
    	c = l.newCondition();
    	t = 0;
    }

    public void timePrinting()
    {
    	l.lock();
    	try
    	{
    		// increment the counter
    		t++;
    		// print the time to screen
    		System.out.print(t + " ");
    		// wake up other threads
    		c.signalAll();
    	}
    	finally
    	{
    		l.unlock();
    	}
    }

    public void messagePrinting(int interval)
    {
    	l.lock();
    	try
    	{
    		while(t % interval != 0)
    			c.await();

    		System.out.println();
			if(interval == 5)
			{
				System.out.println("-- 5 second message");
			}
			else if(interval == 11)
			{
				System.out.println("***** 11 second message");
			}
			// need the thread to wait again because we want this thread to execute only once per time
			c.await();

    	}
    	catch(InterruptedException e){}
    	finally
    	{
    		l.unlock();
    	}
    }

    public static void main(String[] args)
    {
    	Time counter = new Time();

    	TimePrinting tp = new TimePrinting(counter);
    	MessagePrinting mp5 = new MessagePrinting(counter, 5);
    	MessagePrinting mp11 = new MessagePrinting(counter, 11);
    }

}

class TimePrinting implements Runnable{
	private Time counter;

	TimePrinting(Time time)
	{
		counter = time;
		new Thread(this).start();
	}

	public void run()
	{
		for(;;)
		{
			try
			{
				counter.timePrinting();
				Thread.sleep(500);

			}
			catch(InterruptedException e){}
		}

	}
}

class MessagePrinting implements Runnable{
	private Time counter;
	private int interval;

	MessagePrinting(Time time, int interval)
	{
		counter = time;
		this.interval = interval;
		new Thread(this).start();
	}

	public void run()
	{
		for(;;)
		{
			counter.messagePrinting(interval);
		}
	}
}