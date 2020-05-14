// Jiarui Li
// 827003805
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.Queue;
import java.util.LinkedList;

public class PrintServerV1 implements Runnable 
{
    private static Lock l = new ReentrantLock();  // lock to prevent race condition
    private static Condition c = l.newCondition();  // condition variable to prevent dead lock
    private String side;  // string to indicate client or manager side
    private int id;     // id number for manager and clients
    public static Queue<String> requests = new LinkedList<String>();
    private int counter;  // counter to construct meaningful messages

    public PrintServerV1(String _side, int _id)
    {
      side = _side;
      id = _id;
      counter = 0;
      new Thread(this).start();
    }

    // called by the client to add a print request to the Q
    public void printRequest(String s)
    {
      requests.add(s);
    }

  /*    
    depending on whether it is manager or client, this method will run differently
      
      manager: wait for the client to add requests to the Q, 
      then call realPrint() to print them out

      client: add requests to the Q and notify all. 
    */
    public void run()
    {
      if("manager" == side)
      {
        for(;;)
        {
          l.lock();
          try
          {
              while(requests.size() <= 0)
            {
              c.await();
            }
            realPrint(requests.remove());
            Thread.sleep(100);
          }
          catch (InterruptedException e)
          {
            System.out.println("Interrupt while awaits!");
            System.exit(1);
          }
          finally
          {
            l.unlock();
          }


        }
      }
      else if("client" == side)
      {
        for(;;)
        {
          // critical section needs lock
          l.lock();
          try
          {
            // make a string for request
             String s = "[" + this.getClass() + "] " + "client #" + id + " says: I am No." + counter + "!!";
            // update the request Q and counter
            printRequest(s);
            counter++;
            // notify all
            c.signalAll();
          }
          finally
          {
            // yield the lock
            l.unlock();
          }
        }
      }
    }

    // do the real work of outputting the string to the screen.
    public void realPrint(String s)
    {
      System.out.println(s);
    }

    public static void main(String[] args)
    {
      PrintServerV1 m = new PrintServerV1("manager", 1);
      PrintServerV1 c1 = new PrintServerV1("client", 2);
      PrintServerV1 c2 = new PrintServerV1("client", 3);
    }


}