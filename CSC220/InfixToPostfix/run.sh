#!/bin/bash
javac InfixToPostfix.java 2> 2errors.txt
java InfixToPostfix < input.txt 2> errors.txt > output.txt
cat output.txt 
cat errors.txt
cat 2errors.txt
