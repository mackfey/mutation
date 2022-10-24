#!/bin/sh

git diff --no-index --unified=3 src/triangle/Triangle.java .mutated/mutants/$1/triangle/Triangle.java
