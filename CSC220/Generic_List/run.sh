#!/bin/bash
javac ListTest.java 2> 2errors.txt
java ListTest 2> errors.txt > output.out
