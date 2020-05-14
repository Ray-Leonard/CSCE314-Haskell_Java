/* CSCE 314 [Sections 500, 501] Programming Languages Spring 2020
   Homework 6 Problem 1 
   Skeleton code for MyNodeTest class
   Written by Hyunyoung Lee 
*/
// 20 points for the MyNodeTest class

public class MyNodeTest {

  // 5 points
  private static int int_sum (MyNode<Integer> n) {
    // check for null param
    if(n == null)
    {
      return 0;
    }

    int sum = 0;
    for(Integer i : n)
    {
      sum += i.intValue();
    }

    return sum;
  }

  // 10 points
  private static double num_sum (MyNode<? extends Number> n) {
    // check for null input
    if(n == null)
    {
      return 0.0;
    }

    double sum = 0.0;
    for(Number i : n)
    {
      sum += i.doubleValue();
    }

    return sum;
  }

  // 5 points
  private static <T> void print (MyNode<T> n) {
    if(n == null)
    {
      return;
    }
    // to be implemented by you
    for(T t : n)
    {
      System.out.println(t);
    }
  }

    
  // Feel free to "expand" the main method, but keep whatever provided as it is
  public static void main (String args[]) {
    MyNode<Integer> intlist = 
        new MyNode<Integer>(1, 
          new MyNode<Integer>(22, 
            new MyNode<Integer>(21, 
              new MyNode<Integer>(12, 
                new MyNode<Integer>(24, 
                  new MyNode<Integer>(17, null))))));
        
    MyNode<Integer> nullintlist = null;

    System.out.println("===");
    print(intlist);
    System.out.println("sum of intlist is " + int_sum(intlist));
    System.out.println("sum of null list is " + int_sum(nullintlist));
    System.out.println("===");

    MyNode<Double> doublelist = 
        new MyNode<Double>(1., 
          new MyNode<Double>(16., 
            new MyNode<Double>(13.72, 
              new MyNode<Double>(5., 
                new MyNode<Double>(22., 
                  new MyNode<Double>(7.1, null))))));

    System.out.println("===");
    print(doublelist);
    System.out.println("sum ints = " + num_sum(intlist));
    System.out.println("sum doubles = " + num_sum(doublelist));
    System.out.println("===");
  }
}

