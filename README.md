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
