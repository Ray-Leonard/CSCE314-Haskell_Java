README.txt

********* How to compile and run my Fibonacci.java *********

There are two classes in Fibonacci.java and they both contain a main() method.
So when one types and run "javac Fibonacci.java" in shell, there will be two class files generated: "SubsetOutputFib.class" and "ImprovedFibonacci.class"
.
.
.
.
** How to test and run "SubsetOutputFib.class" **

To give a test run, type and run "java SubsetOutputFib a b" in shell.
Note that this program takes in two command line arguments 'a' and 'b', both in integer forms. Those are the begine and end of the desired output of the fibonacci sequence.

- For e.g. if one executes "java SubsetOutputFib 3 9", the output should look like the following:
3: 2 *
4: 3
5: 5
6: 8 *
7: 13
8: 21
9: 34 *

- One more example:
Jiaruis-MacBook-Pro:hw5 li$ java SubsetOutputFib 1 13
1: 1
2: 1
3: 2 *
4: 3
5: 5
6: 8 *
7: 13
8: 21
9: 34 *
10: 55
11: 89
12: 144 *
13: 233

What's more, the program has the input bound check so that if the second argument is smaller than the first one, or if either of the arguments are less than 1, the program will prompt with "Bound error!" and exit. 
But the program does not check the type of the command line arguments, so if someone inputs something other than integer, the program may crash. 

- Examples for bound error:
Jiaruis-MacBook-Pro:hw5 li$ java SubsetOutputFib 1 0
Bound error!
Jiaruis-MacBook-Pro:hw5 li$ java SubsetOutputFib 1 -1
Bound error!
Jiaruis-MacBook-Pro:hw5 li$ java SubsetOutputFib -1 1
Bound error!
.
.
.
.
.
** How to test and run "ImprovedFibonacci.class" **

Type "java ImprovedFibonacci" and run in terminal.

Since this program only rewrites the println() with printf() of the code provided in the textbook, there will be nothing different in terms of its output. 

- Sample output:
Jiaruis-MacBook-Pro:hw5 li$ java ImprovedFibonacci
1: 1
2: 1
3: 2 *
4: 3
5: 5
6: 8 *
7: 13
8: 21
9: 34 *
************ END of Fibonacci.java documentation ***********

.

************ How to compile and run my Vehicle.java************

There are 3 classes in Vehicle.java: Vehicle, VehicleTestP4 and VehicleTest
Vehicle is a class that contains some fields and methods to denote a vehicle.
VehicleTestP4 and VehicleTest are two classes that contain the main() method for testing the Vehicle class. 

If compiled by typing "javac Vehicle.java", two class files will be generated:
VehicleTestP4.class
VehicleTest.class
.
.
.
.
**** How to test run VehicleTestP4.class *****

Type and run "java VehicleTestP4.class" and the console will print some outputs like the following:
------------------------
Vehicle id: 0
Vehicle owner: George
Current speed: 10
Current direction: 0
------------------------
Vehicle id: 1
Vehicle owner: Ray
Current speed: 20
Current direction: 45
------------------------
Vehicle id: 2
Vehicle owner: Zoom
Current speed: 30
Current direction: 90
------------------------
Vehicle id: 3
Vehicle owner: Shinn
Current speed: 40
Current direction: 135
------------------------
Vehicle id: 4
Vehicle owner: Leonard
Current speed: 50
Current direction: 180
------------------------

NOTE 0: The above output is shown when no constructor was implemented by me.

NOTE 1: The fields for each vehicle is predefined, so no matter how many times one runs the code, it will generate the same output, unless modified in the actual code.

NOTE 2: Since this test method is designed when no constructor was implemented, and in order to increase the vehicleId, I explicitly used the following line of code to make sure that vehicleId is incremented by 1 each time a new object is constructed:
v.setId(Vehicle.nextId++);

Afterwards, I encapsulated the incrementation inside the default constructor so that I don't have to explicitly write the increment. Now if one runs this test again, the result of the VehicleId will probably be incremented by 2 each time since it was incremented twice, one inside the constructor and one when calling the setId() function. 
.
.
.
.
**** How to test run VehicleTest.class *****

Type and run "java VehicleTestP4.class" and the console will print some outputs like the following:

Jiaruis-MacBook-Pro:hw5 li$ java VehicleTest
------------------------
Vehicle id: 0
Vehicle owner: Jesus
Current speed: 75
Current direction: 168
------------------------
Vehicle id: 1
Vehicle owner: Jesus
Current speed: 89
Current direction: 0
------------------------
Vehicle id: 2
Vehicle owner: Fibonacci
Current speed: 3
Current direction: 107
------------------------
Vehicle id: 3
Vehicle owner: Trump
Current speed: 56
Current direction: 154
------------------------
Vehicle id: 4
Vehicle owner: Mickey Mouse
Current speed: 41
Current direction: 119
------------------------

(this commented part does not belong to the system output)
// NOTE: I created 2 objects using my default constructor and 3 objects using the paramtized constructor. So the default owner name is "Jesus" in this case. And I used Math.random() to generate random speed and direction within the correct range for the object in my default constructor. 

----test for toString()----
*ID* 0
*Owner* Jesus
*Speed* 75
*Direction* 168

*ID* 1
*Owner* Jesus
*Speed* 89
*Direction* 0

*ID* 2
*Owner* Fibonacci
*Speed* 3
*Direction* 107

*ID* 3
*Owner* Trump
*Speed* 56
*Direction* 154

*ID* 4
*Owner* Mickey Mouse
*Speed* 41
*Direction* 119

Highest ID number: 4

----test for changeSpeed() and stop()----
*ID* 3
*Owner* Trump
*Speed* 2333
*Direction* 154

*ID* 4
*Owner* Mickey Mouse
*Speed* 0
*Direction* 119

----test for turn()----
*ID* 3
*Owner* Trump
*Speed* 2333
*Direction* 24

*ID* 4
*Owner* Mickey Mouse
*Speed* 0
*Direction* 209

(the commented part does not belong to the system output)
// NOTE 0: In this testcase, I used
v4.turn(230); and
v5.turn(Vehicle.TURN_RIGHT); to make the turn.
My turn() function is able to get any integer number as input. When the input is smaller than -359 or greater than 359, it ran the algo to calculate the correct degree within the range of -359 ~ 359 since any degree out of the range is causing the car to turn around in circles. 

What's more, the result of the Direction will not exceed 359 or below 0 since there is also protection algo that makes the direction right after turning. 

NOTE 1: I could not figure out a way to overload methods like:
public void turn(int d);
and
public void turn(final int d);
since the compiler seems to believe that "int" and "final int" are of same type. 
So I managed to overload:
public void turn(int d);
and public void turn(final short d); and in the mean time I defined the constants Vehicle.TURN_LEFT and Vehicle.TURN_RIGHT of type "public static final short".
I'm not sure if this is the correct way to do this.

Thank you for reading my documentation!
**************END**************

