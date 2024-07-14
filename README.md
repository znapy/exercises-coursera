# About

Exercises from www.coursera.org

## Algorithms - Part 1

### Preparing

#### Links

https://www.coursera.org/learn/algorithms-part1/resources/CrmR4

https://lift.cs.princeton.edu/java/linux/

```
spotbugs HelloWorld.class
pmd HelloWorld.java
checkstyle -coursera HelloWorld.java
```

https://algs4.cs.princeton.edu/code/ - install algs4. I already add [algs4.jar](./Algorithms%20Part%20I/algs4.jar) file to a project - you can replace it.  The library *algs4.jar* is released under the GNU General Public License, version 3 (GPLv3).

#### Geany commands:

[Geany](https://www.geany.org/) - is a lightweight code editor, available in Linux repos.

Settings in *exercises-coursera-algp1.geany*

**Compile**: `docker run --rm -v "<path-to-project>/Algorithms Part I":/usr/src/classpath -v "%d":/usr/src/myapp -w /usr/src/myapp openjdk:11 javac --class-path "$CLASSPATH:/usr/src/classpath/algs4.jar" "%f"`

**Compile & execute**: `docker run --rm -v "<path-to-project>/Algorithms Part I":/usr/src/classpath -v "%d":/usr/src/myapp -w /usr/src/myapp openjdk:11 bash -c "javac --class-path /usr/src/myapp --class-path $CLASSPATH:/usr/src/classpath/algs4.jar %f && sleep 0.1s && java --class-path $CLASSPATH:/usr/src/classpath/algs4.jar %e"`

**Execute inplace**: `docker run --rm -v /tmp/.X11-unix:/tmp/.X11-unix:ro -e DISPLAY=unix$DISPLAY -v "<path-to-project>/Algorithms Part I":/usr/src/classpath -v "%d":/usr/src/myapp -w /usr/src/myapp openjdk:11 java --class-path "$CLASSPATH:/usr/src/classpath/algs4.jar" "%e"`

**Dialog command**: `docker run --rm -v "<path-to-project>/Algorithms Part I":/usr/src/classpath -v "%d":/usr/src/myapp -w /usr/src/myapp openjdk:11 java --class-path "$CLASSPATH:/usr/src/classpath/algs4.jar" kdtree/KdTreeVisualizerSolver `

It doesn't work with visualizer in week 6, so environment prepared in virtual machine by [links](#links) above.

### Week #1

> Bad programmers worry about the code, good programmers worry about data structures, and their relationships.
>
> Linus Torvalds

#### Interview Questions: Union–Find

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
