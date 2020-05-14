
How to compile and run Homework 6 and what is the expected output?


There are 4 java files for this homework and are grouped and tested as follows:
MyNode.java & MyNodeTest.java
MyList.java & MyListTest.java
MyNodeTest.java and MyListTest.java contains main methods, MyNode.java and MyList.java are corresponding class definitions. 

To run the MyNode test, please do the following:
1. type and run "javac MyNode.java MyNodeTest.java" in terminal
2. type and run "java MyNodeTest" in terminal

The expected intpu & output will look like:
Jiaruis-MacBook-Pro:hw6 li$ javac MyNode.java MyNodeTest.java
Jiaruis-MacBook-Pro:hw6 li$ java MyNodeTest
===
1
22
21
12
24
17
sum of intlist is 97
sum of null list is 0
===
===
1.0
16.0
13.72
5.0
22.0
7.1
sum ints = 97.0
sum doubles = 64.82
===


*************************************************************


To run the MyNode test, please do the following:
1. type and run "javac MyList.java MyListTest.java" in terminal
2. type and run "java MyListTest" in terminal

Jiaruis-MacBook-Pro:hw6 li$ javac MyList.java MyListTest.java -Xlint
Jiaruis-MacBook-Pro:hw6 li$ java MyListTest
list  = [(head: 1) -> (2) -> (3) -> (4)]
list1 = [(head: 2) -> (4) -> (3) -> (1)]
list == list1 is false
list.equals(list1) = true
list1.equals(list2) = true
list3 = [(head: 1) -> (2) -> (3) -> (1)]
list4 = [(head: 1) -> (2) -> (3) -> (1) -> (4)]
list1.equals(list3) = false
list1.equals(list4) = false
list.compareTo(list1) = 0
list.compareTo(list4) = -1
sum==10 OK


[(head: 1) -> (2) -> (3) -> (4)]
[(head: 4) -> (3) -> (2) -> (1)]
[(head: 1) -> (2) -> (3) -> (4)]
1
[(head: 22) -> (21) -> (2) -> (3) -> (4)]
22
[(head: 22) -> (21) -> (2) -> (3) -> (4)]
22 22
21 21
2 2
3 3
4 4

list1 = [(head: 2) -> (4) -> (3) -> (1)]
list2 = [(head: 4) -> (3) -> (2) -> (21) -> (22) -> (1) -> (2) -> (3) -> (4)]
list2.compareTo(list1) = 5
=== end of test