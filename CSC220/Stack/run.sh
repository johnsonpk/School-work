#!/bin/bash
javac StackTest.java 2> 2errors.txt
java StackTest 2> errors.txt > output.out
