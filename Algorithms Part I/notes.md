# Algorithms - Part 1

## Preparing

### Links

https://www.coursera.org/learn/algorithms-part1/resources/CrmR4

https://lift.cs.princeton.edu/java/linux/

```
spotbugs HelloWorld.class
pmd HelloWorld.java
checkstyle -coursera HelloWorld.java
```

https://algs4.cs.princeton.edu/code/ - install algs4. I already add [algs4.jar](./Algorithms%20Part%20I/algs4.jar) file to a project - you can replace it.  The library *algs4.jar* is released under the GNU General Public License, version 3 (GPLv3).

### Geany commands:

[Geany](https://www.geany.org/) - is a lightweight code editor, available in Linux repos.

Settings in *exercises-coursera-algp1.geany*

**Compile**: `docker run --rm -v "<path-to-project>/Algorithms Part I":/usr/src/classpath -v "%d":/usr/src/myapp -w /usr/src/myapp openjdk:11 javac --class-path "$CLASSPATH:/usr/src/classpath/algs4.jar" "%f"`

**Compile & execute**: `docker run --rm -v "<path-to-project>/Algorithms Part I":/usr/src/classpath -v "%d":/usr/src/myapp -w /usr/src/myapp openjdk:11 bash -c "javac --class-path /usr/src/myapp --class-path $CLASSPATH:/usr/src/classpath/algs4.jar %f && sleep 0.1s && java --class-path $CLASSPATH:/usr/src/classpath/algs4.jar %e"`

**Execute inplace**: `docker run --rm -v /tmp/.X11-unix:/tmp/.X11-unix:ro -e DISPLAY=unix$DISPLAY -v "<path-to-project>/Algorithms Part I":/usr/src/classpath -v "%d":/usr/src/myapp -w /usr/src/myapp openjdk:11 java --class-path "$CLASSPATH:/usr/src/classpath/algs4.jar" "%e"`

**Dialog command**: `docker run --rm -v "<path-to-project>/Algorithms Part I":/usr/src/classpath -v "%d":/usr/src/myapp -w /usr/src/myapp openjdk:11 java --class-path "$CLASSPATH:/usr/src/classpath/algs4.jar" kdtree/KdTreeVisualizerSolver `

It doesn't work with visualizer in week 6, so environment prepared in virtual machine by [links](#links) above.

## Week #1

> Bad programmers worry about the code, good programmers worry about data structures, and their relationships.
>
> Linus Torvalds

### Interview Questions: Union–Find

Total points 3. Your answers cannot be more than 10000 characters.

1. Q: Social network connectivity. Given a social network containing *n* members and a log file containing *m* timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be *m* log *⁡n* or better and use extra space proportional to *n*.

<!--
   A: The earliest time (at which all members are connected) is the timestamp, when the only one root will remain. For m log n running time (worst-case time) we should use "weighted QU + path compression" algorithm. It will works, because we have sorted by timestamp log file (used as union operation).
-->

2. Q: Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing *i*. The operations, union(), connected(), and find() should all take logarithmic time or better.

   For example, if one of the connected components is *\{1, 2, 6, 9\}*, then the find() method should return *9* for each of the four elements in the connected components.

<!--
   A: We can use 1 additional array where index is number of component and value is the maximum. Every union operation we will update additional array, every find operation we will get value in additional array for root component (in classical array).
-->

3. Q: Successor with delete. Given a set of *n* integers *S = \{ 0, 1, ... , n-1 \}* and a sequence of requests of the following form:

   - Remove *x* from *S*
   - Find the *successor* of *x:* the smallest *y* in *S* such that *y ≥ x*.

   design a data type so that all operations (except construction) take logarithmic time or better in the worst case.

<!--
   A:
-->

===

correct 2 of 3 questions

## Week #2

### Bytes counting

| #    | Structure                           | bytes    |
| ---- | ----------------------------------- | -------- |
| 1    | Object overhead                     | 16       |
| 2    | inner class extra overhead          | 8        |
| 3    | reference to String                 | 8        |
| 4    | reference to Object                 | 8        |
| 5    | reference to Integer                | 4        |
| 6    | reference to array                  | 8        |
| 7    | array overhead                      | 24       |
| 8    | Items without memory for themselves | 8 * size |
| 9    | array padding                       | 4        |

### Interview Questions: Stacks and Queues

Total points 3 (1 per question). Your answer cannot be more than 10000 characters.

1. Q: **Queue with two stacks.** Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.

   *Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.*

<!--
   A: We can use first stack for enqueue operations. When we need dequeue operation - we get item from second stack, if it is empty - move all items from first to second before.
-->

2. Q: **Stack with max.** Create a data structure that efficiently supports the stack operations  (push and pop) and also a return-the-maximum operation. Assume the  elements are real numbers so that you can compare them.

<!--
   A: We can't use extra variables for maximum and for quantities of maximum elements, because second pop operation need new scan. Also, we don't study sorted lists yet. So I have only one bad idea - every pop operation scan full stack.
-->

3. Q: **Java generics.** Explain why Java prohibits generic array creation.

<!--
   A: I guess, for arrays compiler make memory allocation before run-time and generic type haven't size for it.
-->

## Week #3

### Mergesort

> Good algorithms are better than supercomputers

Mergesort uses at most N lg N compares and 6 N lg N array accesses to sort any array of size N

> Home PC executes 10^8 compares / second
>
> Supercomputer executes 10^12 compares / second

![Screenshot from 2020-05-20 10-27-30](.assets/Screenshot%20from%202020-05-20%2010-27-30.png)

![Screenshot from 2020-05-20 10-28-29](.assets/Screenshot%20from%202020-05-20%2010-28-29.png)

![Screenshot from 2020-05-20 10-31-53](.assets/Screenshot%20from%202020-05-20%2010-31-53.png)

#### Insertion sort

![Screenshot from 2020-05-20 10-30-41](.assets/Screenshot%20from%202020-05-20%2010-30-41.png)

#### Selection sort

![Screenshot from 2020-05-20 10-29-47](.assets/Screenshot%20from%202020-05-20%2010-29-47.png)

### Interview Questions: Mergesort

1. Q: **Merging with smaller auxiliary array.** Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted using an auxiliary array of length *n* (instead of *2n*)?
   
   *Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.*
   
<!--
   A: We can use 2 extra separators ("_" = 0 then started) that indicated ready and buffer spaces. Do not clone items to aux and start compare in result array - put minimal to aux.
Then aux filled - move items after separator2 to front of result array till it has free cells (after separator1) and move separator1 and separator2. Then result array hasn't free cells - continue compare items after separator1 to aux before separator2. In the end - move all items from aux to result array.
```
_ 2   4   6   8 |'1   3   5   7 // _  
_'2   4   6   8 |     3   5   7 // _ 1
_     4   6   8 |    '3   5   7 // _ 1   2
_    '4   6   8 |         5   7 // _ 1   2   3
_         6   8 |         5   7 // _'1   2   3   4
  1 _     6   8 |        '5   7 //     _ 2   3   4
  1 _     6   8 |             7 //   5 _'2   3   4
  1   2 _'6   8 |             7 //   5     _ 3   4
  1   2 _     8 |             7 //   5   6 _'3   4
  1   2   3 _ 8 |            '7 //   5   6     _ 4
  1   2   3 _ 8 |               //   5   6   7 _'4
  1   2   3   4 |               //  '5  '6  '7  '8
  1   2   3   4 | 5   6   7   8 //   
```
-->

2. Q: **Counting inversions**. An *inversion* in an array *a[ ]* is a pair of entries *a[i]* and *a[j]* such that *i<j* but *a[i]>a[j]*. Given an array, design a linearithmic algorithm to count the number of inversions.
   
<!--
   A: Apply mergesort for given array, use extra counter for every merge operation where you take item from second part before take item from first part.
-->

3. Q: **Shuffling a linked list.** Given a singly-linked list containing *n* items, rearrange the items uniformly at random. Your algorithm should  consume a logarithmic (or constant) amount of extra memory and run in time proportional to *n log n* in the worst case. 
   
<!--
   A: Use array-based randomized queue (from 2 week practice), erase every item after enqueue.
-->


### Quicksort

3-way partitioning with quicksort:

![Screenshot from 2020-05-20 10-23-56](.assets/Screenshot%20from%202020-05-20%2010-23-56.png)

![Screenshot from 2020-05-20 10-21-34](.assets/Screenshot%20from%202020-05-20%2010-21-34.png)

|          | insertion sort (N2) |           |           | mergesort (N log N) |          |         | quicksort (N log N) |         |         |
| -------- | ------------------- | --------- | --------- | ------------------- | -------- | ------- | ------------------- | ------- | ------- |
| computer | thousand            | million   | billion   | thousand            | million  | billion | thousand            | million | billion |
| home     | instant             | 2.8 hours | 317 years | instant             | 1 second | 18 min  | instant             | 0.6 sec | 12 min  |
| super    | instant             | 1 second  | 1 week    | instant             | instant  | instant | instant             | instant | instant |

In quick sort avarage compares ~ 1.39 N *log* N - it's 39% more than mergesort, but faster, 'cause it has less data movement.

In worst case it is quadratic compares: 1/2 N^2

![Screenshot from 2020-05-21 11-50-41](.assets/Screenshot%20from%202020-05-21%2011-50-41.png)

"inplace" - doesn't take extra space for new array (the sort done inplace)

### Interview Questions: Quicksort

1. Q: **Nuts and bolts.** A disorganized carpenter has a mixed pile of *n* nuts and *n* bolts. The goal is to find the corresponding pairs of nuts and bolts.  Each nut fits exactly one bolt and each bolt fits exactly one nut. By  fitting a nut and a bolt together, the carpenter can see which one is  bigger (but the carpenter cannot compare two nuts or two bolts  directly). Design an algorithm for the problem that uses at most  proportional to n *log* ⁡n compares (probabilistically).
   
<!--
   A: We have 2 arrays - nuts and bolts. Get first nut and divide bolts to 2 parts - bigger and smaller, unite them.  Repeat with take second nut. In the end we will get sorted bolts array - just compare every nut with them to get sorted 2nd array.
-->

2. Q: **Selection in two sorted arrays.** Given two sorted arrays *a[  ]* and *b[  ]*, of lengths *n1* and *n2* and an integer *0 ≤ k < n1 + n2*, design an algorithm to find a key of rank *k*. The order of growth of the worst case running time of your algorithm should be log *⁡n*, where *n = n1 + n2*.

   - Version 1: n1=n2 (equal length arrays) and k=n/2 (median).

   - Version 2: k=n/2 (median).

   - Version 3: no restrictions.
   
<!--
   A:
-->

3. Q: **Decimal dominants.** Given an array with *n* keys, design an algorithm to find all values that occur more than *n / 10* times. The expected running time of your algorithm should be linear.
   
<!--
   A: 
-->

## Week #4

### Interview Questions: Priority Queues

1. Q: **Dynamic median.** Design a data type that supports *insert* in logarithmic time, *find-the-median* in constant time, and *remove-the-median* in logarithmic time. If the number of keys in the data type is even, find/remove the *lower median*.

<!--
   A: 
-->

2. Q:  **Randomized priority queue.** Describe how to add the methods ```sample()``` and ```delRandom()``` to our binary heap implementation. The two methods return a key that is chosen uniformly at random among the remaining keys, with the latter  method also removing that key. The ```sample()``` method should take constant time; the ```delRandom()ёёё method should take logarithmic time. Do not worry about resizing the underlying array.

<!--
   A: 
-->

3. Q: **Taxicab numbers.** A *taxicab* number is an integer that can be expressed as the sum of two cubes of positive integers in two different ways: *a^3 + b^3 = c^3 + d^3*. For example, 172917291729 is the smallest taxicab number: *9^3 + 10^3 = 1^3 + 12^3*. Design an algorithm to find all  taxicab numbers with *a*, *b*, *c*, and *d* less than *n*.

- Version 1: Use time proportional to *n^2 log n* and space proportional to *n^2*.
- Version 2: Use time proportional to *n^2 log n* and space proportional to *n*.

<!--
   A: 
-->

### Interview Questions: Elementary Symbol Tables

1. Q: **Java autoboxing and equals()**. Consider  two *double* values *a* and *b* and their corresponding *Double* values *x* and *y*.

	- Find values such that *(a==b)* is *true* but *x.equals(y)* is *false*.

	- Find values such that *(a==b)* is *false* but *x.equals(y)* is *true*.

<!--
   A: 
-->

2. Q: **Check if a binary tree is a BST.** Given a binary tree where each *Node* contains a key, determine whether it is a binary search tree. Use extra space proportional to the height of the tree.

<!--
   A: 
-->

3. Q: **Inorder traversal with constant extra space**. Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space.

<!--
   A: 
-->

4. Q: **Web tracking.** Suppose that you are tracking *n* web sites and *m* users and you want to support the following API:

	- User visits a website.

	- How many times has a given user visited a given site?

<!--
   A: 
-->

## Week #5

### Interview Questions: Balanced Search Trees

1. Q: **Red–black BST with no extra memory.** Describe how to save the memory for storing the color information when implementing a red–black BST. 

<!--
   A: We need just one bit per node for save the color of parent link
-->

2. Q: **Document search.** Design an algorithm that takes a sequence of *n* document words and a sequence of *m* query words and find the shortest interval in which the *m* query words appear in the document in the order given. The length of an interval is the number of words in that interval.

<!--
   A: 
-->

3. Q: **Generalized queue**. Design a generalized queue data type that supports all of the following operations in logarithmic time (or better) in the worst case.

   - Create an empty data structure.
   - Append an item to the end of the queue.
   - Remove an item from the front of the queue.
   - Return the ith item in the queue.
   - Remove the ith item from the queue.

<!--
   A: 
-->


