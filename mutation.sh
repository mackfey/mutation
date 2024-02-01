#!/bin/sh

MAJOR_HOME="lib/major"

$MAJOR_HOME/bin/ant -f mutation.xml
echo Live mutants: $(grep ,LIVE mutation_results/killed.csv | cut -f1 -d',')
