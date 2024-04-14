# Improving test suites via mutation

## High-level goal
The high-level goals of this exercise are to (1) learn about mutation testing
and (2) reason about test-goal utility.

## Setup

1. Use a Unix environment or Git bash on Windows for this exercise.
Make sure a [**Java 8, 11, 17, or 21 JDK**](https://www.oracle.com/java/technologies/downloads),
and [**Git**](https://git-scm.com/) are installed.

2. Test your setup: compile and test the Triangle program.

3. Run `mutation.sh`.  The last line printed should start with:
   `Live mutants: 2 3 4 7 8 9 ...`
   * *On Windows*: You may need to change `./gradlew` to `./gradlew.bat` on
    line 9 of `mutation.xml`.

You may use an experimental IntelliJ plugin for this exercise. See the
[detailed
instructions](https://docs.google.com/document/d/1VHMUY7VZqU4GZvzVY00Sx5aFFZKYhFLiL8BCmD24WrQ)
for more details.

## Instructions

0. **Read the entire assignment and ask any clarifying questions that you might have.**

1. If you have done a prior testing exercise with the Triangle program, incorporate your best test suite into **`testTable` in `TriangleTest.java`**.

2. Run `mutation.sh` and note the number of covered and detected mutants
   (see Question 1 below).  Note that the mutation tool (Major) refers to
   "detected mutants" as "killed mutants".  See terminology below.

3. Add tests to `testTable` in file *test/triangle/TriangleTest.java* to satisfy
   mutation adequacy -- that is,
   until your test suite detects all non-equivalent mutants:

    a. Select a live mutant (for which you have not proven equivalence) for analysis.

    b. Examine its source code (maybe by running `show_mutant.sh `*ID*) to
       determine whether it is equivalent.

        * If it is equivalent, provide an argument to establish that fact.

        * If it is not equivalent, write a test that the original program passes
          but the mutant fails.  Run `./gradlew clean test` after adding the new
          test to ensure that it passes on the original program.

    c. Run `mutation.sh` and continue with step a.

    Note that you will likely observe certain patterns (i.e., similar mutants
    requiring similar tests) because of the systematic mutation of the source code
    -- adding multiple tests at once may speed up your testing process. Likewise,
    some mutants are easier to resolve than others -- triaging the set of live
    mutants and selecting mutants out of order may speed up your testing process.

    **If you get stuck on a mutant**, take notes, move on, and revisit unresolved
    mutants later.

3. Disable (comment out) the `assertEquals` statement in the `testTriangle`
   method and run `./gradlew jacocoTestReport` and `mutation.sh`. Note the code coverage
   ratio(s) and mutation score (see Question 4 below).

5. Extra credit: **Re-enable the `assertEquals` statement** in the `testTriangle` method and
   **set `exportKillMap` to true** in the `build.xml` file.
   **Rerun `mutation.sh`, which now computes `killMap.csv`.**
   Determine the set of dominator mutants (see Question 5 below).

## Questions

1. How many mutants does the initial test suite cover?
   How many mutants does the initial test suite detect?

2. How many mutants are equivalent (to the original program)?
   Justify your judgments by providing a proof or argument for each equivalent mutant.
   You do not need to provide a formal proof, but you should
   demonstrate proper reasoning and provide a valid argument. You may group
   equivalent mutants in your answer if the reason for equivalence is the same
   for all mutants in a group (e.g., mutants 4711, 4712, 4713 are equivalent
   because they exist in dead code).

3. Were any of the generated mutants unproductive? Briefly explain your answer.

4. What changes in code coverage ratio and mutation score did you observe after
   disabling the `assertEquals` statement in the `testTriangle` method? What are the
   implications for using the code coverage ratio as an adequacy criterion?

5. Extra credit: Given the `killMap.csv` (mutant-test detection matrix), determine the set of
   dominator mutants in the DMSG (dynamic mutant subsumption graph) that can be
   derived from the mutant-test detection matrix. You may automate this analysis.


## Deliverables

1. A plain-text file with your answers to the four questions above.

2. Your mutation-adequate `TriangleTest.java` test suite.

3. Your `<timestamp>.csv` file, if you used the IntelliJ plugin.


## Hints

To avoid spurious results and confusion, it is a good idea to **run**
`ant clean test` to make sure that your added **tests pass** on the original
program.

It is possible to write test cases that detect ("kill") every mutant.
If you are not able to create tests for a few of the mutants, don't
sweat it.  Turn in what you have.


## Background

[Mutation testing](https://en.wikipedia.org/wiki/Mutation_testing) is a way of
**evaluating a test suite**.  It tells you how good your test suite is, and it
helps you improve your test suite.  First, some terminology:

A "mutant" is a slight variation of a program, for instance changing `+` to `-`
or changing `1` to `0`.  For examples, run in this repository:
```
show_mutant.sh 54
show_mutant.sh 101
```

When a program is mutated, the mutant might be:

 * "equivalent":  This means that the mutant is equivalent to the original
   program.  The mutant always behaves exactly like the original program did,
   even though its source code differs.  For example, a change from
   `b = a; x = a + **b**;` to `b = a; x = a + **a**;`.
 * "non-equivalent":  There exists some input such that the mutant's behavior
   differs from the original program.

Because a mutant's interface (the structure of its inputs and outputs) is the
same as the original program, every test case for the original program is a test
case for the mutant.  To evaluate a test suite, run it with many mutants.  A
mutant is "detected" or "discovered" or "killed" if the mutant *fails* the test
suite.  Otherwise, the mutant is "live".  A mutant that is equivalent will
always be live.  A mutant is "covered" if it is executed by the test suite.
Every uncovered mutant is live.

If a mutant is live but not equivalent, that indicates a problem with the test
suite:  there exists a small change to the program (namely, the mutation)
that introduces a defect that the
test suite does not detect.  The mutation score is the proportion of mutants
that were killed.  For example, if the test suite failed for 65 out of 100
mutants, then the mutation score of the test suite is .65.  Higher numbers are
better.  A perfect score is 1.0, in which case we say the test suite is
"mutation-adequate".  (All of these terms are *with respect to a particular set
of mutants*, but that set is usually left implicit.)

If you augment a test suite and its mutation score goes up, then the augmented
test suite is better than the original, because it detects more defects.  (A
mutation score of 1.0 does not guarantee that the test suite will find all
defects, though.)


