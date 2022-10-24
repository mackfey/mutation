# Triangle
Triangle is an example program for a software testing exercise that focuses on
unit testing and test effectiveness, using code coverage criteria.

#### How to build Triangle and run its tests from the terminal:

1. Change into the Triangle root directory, which contains the *build.xml* build
   file.

2. Run `ant compile` to compile Triangle. The compiled class files will be in
   the *bin* directory.

4. Run `ant test` to run all Triangle unit tests.

5. Run `ant clean` whenever you want to clean up the project (i.e., delete all
   generated files).

#### Mutation analysis

1. Run `./mutation.sh` to compute the mutation score and list all mutants that
are covered (i.e., the mutated code is executed) but live (i.e., the mutant is
not detected by any test).

2. After completion, the following files in the *mutation_results* directory
provide additional information:

    - *killed.csv*:  a summary of whether a covered mutant is still live or was
                     killed with an exception/assertion/timeout.

    - *summary.csv*: a one-line summary that indicates how many mutants were
                     generated and killed, and how long the analysis took.

    - *mutants.log*: a summary of the generated mutants (i.e., what part of the
                     code was changed and how).

    - *covMap.csv*:  a matrix that indicates which tests cover which mutants.

    - *killMap.csv*: a matrix that indicates which tests detect (kill) which
                     mutants. (Only computed and exported if `exportKillMap` is
                     set to true in the `build.xml` build file.)

    - *testMap.csv*: a mapping from test id (TestNo) to test name.

Rather than reading the mutants.log file, you can visualize the source code
differences between a mutant and the original program by running:
`./show_mutant.sh <mutant ID>`

For example, the following command shows how mutant 1 differs from the original
(i.e., unmutated) program:
`./show_mutant.sh 1`

The output is a unified diff that indicates what line the mutation changed:
```
--- a/src/triangle/Triangle.java
+++ b/.mutated/mutants/1/triangle/Triangle.java
@@ -17,7 +17,7 @@ public class Triangle {
      * of its three sides.
      */
     public static Type classify(int a, int b, int c) {
-        if (a <= 0 || b <= 0 || c <= 0) {
+        if (a <= 1 || b <= 0 || c <= 0) {
             return Type.INVALID;
         }
         int trian = 0;
```
The line starting with `@@` indicates the line number of the first context line.
By default, there are three context lines before the mutated line. In this
example, line 17 is the first context line, and line 20 is the mutated line.
The line starting with `- ` shows the removed line,
and the line starting with `+ ` shows the line that replaced it.

## Troubleshooting

#### Unit tests not running
The test target in the *build.xml* file is configured to (recursively) include
and run all *.java* files in the *test* folder as JUnit test suites.

+ Test suites placed elsewhere will not be run.

#### Outdated version of JUnit
If your system uses an outdated version of JUnit, you may encounter the
following error:
```
[junit] junit/framework/JUnit4TestAdapterCache
[junit] java.lang.NoClassDefFoundError: junit/framework/JUnit4TestAdapterCache
```
Run `ant -lib lib/ <target>` to explicitly use JUnit4, which is provided in the
*lib* directory. For example, run `ant -lib lib/ test` to run all unit tests.

#### Java JDK not installed or misconfigured
If a Java JDK is not installed or properly configured on your system, you may
encounter the following error: 
```
BUILD FAILED
build.xml:17 Unable to find a javac compiler;
```
Make sure that you have a JDK installed and that the JAVA_HOME environment
variable is properly set.
