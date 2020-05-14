// Jiarui Li
// 827003805
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.Queue;
import java.util.LinkedList;

public class PrintServerV2 implements Runnable 
{
    private static Lock l = new ReentrantLock();  // lock to prevent race condition
    private static Condition c = l.newCondition();  // condition variable to prevent dead lock
    private String side;  // string to indicate client or manager side
    private int id;     // id number for manager and clients
    public static Queue<String> requests = new LinkedList<String>();
    private int counter;  // counter to construct meaningful messages

    public PrintServerV2(String _side, int _id)
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
          try
          {
            managerRun();
          }
          catch(InterruptedException e)
          {
            System.out.println("Interrupted while awaits!");
            System.exit(1);
          }

      }
      else if("client" == side)
      {
          clientRun();
      }
    }

    public void managerRun() throws InterruptedException
    {
        for(;;){
          synchronized(l){
            while(requests.size() <= 0)
            {
              l.wait();
            }
            realPrint(requests.remove());
            Thread.sleep(100);
          }
      }
    }

    public void clientRun()
    {
        for(;;){
          synchronized(l){
            // make a string for request
            String s = "[" + this.getClass() + "] " + "client #" + id + " says: I am No." + counter + "!!";
            // update the request Q and counter
            printRequest(s);
            counter++;
            // notify all
            l.notifyAll();
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
      PrintServerV2 m = new PrintServerV2("manager", 1);
      PrintServerV2 c1 = new PrintServerV2("client", 2);
      PrintServerV2 c2 = new PrintServerV2("client", 3);
    }
}