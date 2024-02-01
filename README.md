# Triangle
Triangle is an example program for a software testing exercise that focuses on
unit testing and test effectiveness, using code coverage criteria.
The Triangle program comes with 150 mutants, in directory *.mutated/mutants*.

#### How to build Triangle and run its tests from the terminal:

1. Change into the Triangle root directory, which contains the *build.gradle* build
   file.

2. Run `./gradlew classes` to compile Triangle. The compiled class files will be in
   the *build/classes/java/main/* directory.

3. Run `./gradlew test` to run all Triangle unit tests. The test results are
   available as html report: *build/reports/tests/test/index.html*.

4. Run `./gradlew jacocoTestReport` to produce a code coverage report. The
   coverage results are printed to the console and are available as html report:
   *build/reports/jacoco/test/html/index.html*.

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

##### Inspecting a mutant

Run `./show_mutant.sh <mutant ID>` to visualize the source code
differences between a mutant and the original program by running.

For example, the following command shows how mutant 1 differs from the original
(i.e., unmutated) program:
`./show_mutant.sh 1`

The output is a unified diff that indicates what line the mutation changed.
The line starting with `-` shows the removed line (original program),
and the line starting with `+` shows the line that replaced it (mutant).
A line starting with ` ` is the same in both files.

Here is output of `./show_mutant.sh 1`:

```
diff --git a/src/triangle/Triangle.java b/.mutated/mutants/1/triangle/Triangle.java
index f31c52d..3b3899e 100644
--- a/src/triangle/Triangle.java
+++ b/.mutated/mutants/1/triangle/Triangle.java
@@ -3,50 +3,50 @@ package triangle;
 /**
  * An implementation that classifies triangles.
  */
 public class Triangle {

     /**
      * This enum gives the type of the triangle.
      */
     public static enum Type {
         INVALID, SCALENE, EQUILATERAL, ISOSCELES
     };

     /**
      * This static method does the actual classification of a triangle, given the lengths
      * of its three sides.
      */
     public static Type classify(int a, int b, int c) {
-        if (a <= 0 || b <= 0 || c <= 0) {
+        if (a <= 1 || b <= 0 || c <= 0) {
             return Type.INVALID;
         }
         int trian = 0;
         if (a == b) {
             trian = trian + 1;
         }
         if (a == c) {
             trian = trian + 2;
         }
         if (b == c) {
             trian = trian + 3;
         }
         if (trian == 0) {
             if (a + b <= c || a + c <= b || b + c <= a) {
                 return Type.INVALID;
             } else {
                 return Type.SCALENE;
             }
         }
         if (trian > 3) {
             return Type.EQUILATERAL;
         }
         if (trian == 1 && a + b > c) {
             return Type.ISOSCELES;
         } else if (trian == 2 && a + c > b) {
             return Type.ISOSCELES;
         } else if (trian == 3 && b + c > a) {
             return Type.ISOSCELES;
         }
         return Type.INVALID;
     }
 }
```
