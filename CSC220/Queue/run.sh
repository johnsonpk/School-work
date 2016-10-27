#!/bin/bash
javac QueueTest.java 2> 2errors.txt
java QueueTest 2> errors.txt > output.out
