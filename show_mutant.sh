#!/bin/sh

# Invoke as
#   show_mutant NUMBER
# For example:
#   show_mutant 45

git diff --no-index -W src/main/java/triangle/Triangle.java .mutated/mutants/"$1"/triangle/Triangle.java
