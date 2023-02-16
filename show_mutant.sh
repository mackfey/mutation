#!/bin/sh

git diff --no-index -W src/triangle/Triangle.java .mutated/mutants/$1/triangle/Triangle.java
