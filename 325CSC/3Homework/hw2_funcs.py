############################
# Name: Pablo Johnson
# Class: CSC 325 - Adv. Data Structures and Algorithms
# Assignment: Implementation of KMP Algorithm
# Due: Oct 31, 2017 (Spooky)
############################
import time, sys
# Time to compare speed of algorithms
# Sys to read from stdin

# I'm going to refrain from adding more comments since these
#   algorithms were provided

def compute_kmp_fail(P):
# Utility that computes and returns KMP 'fail' list.
    m = len(P)
    fail = [0] * m          # by default, presume overlap of 0 everywhere
    j = 1
    k = 0
    while j < m:            # compute f(j) durint this pass, if nonzero
        if P[j] == P[k]:    # k + 1 characters match thus far
            fail[j] = k + 1
            j += 1
            k += 1
        elif k > 0:         # k follows a matching prefix
            k = fail[k-1]
        else:               # no match found starting at j
            j += 1
    return fail

def find_fmp(T,P):
    # Return the lowest index of T at which substring begins (or else -1)
    n, m = len(T), len(P)           # introduce convenient notations
    if m == 0: return 0             # trivial search for empty string
    fail = compute_kmp_fail(P)      # rely on utility to precompute
    j = 0                           # index into text
    k = 0                           # index into pattern
    while j < n:
        if T[j] == P[k]:            # P[0:1 + k] matched thus far
            if k == m - 1:          # match is complete
                return j - m + 1
            j += 1                  # try to extend match
            k += 1
        elif k > 0:
            k = fail[k-1]           # reuse suffix of P[0:k]
        else:
            j += 1                  

    return -1                       # reached end without match

def find_boyer_moore(T, P):
    # Return the lowest index of T at which substring P begins ( or else -1)
    n, m = len(T), len(P)           # introduce convenient notations
    if m == 0: return 0             # trivial search for empty string
    last = {}                   # build 'last' dictionary
    for k in range(m):
        last[ P[k]] = k             # later occurrence overwrites
    # align end of pattern at index m - 1 of text
    i = m - 1                       # an index into T
    k = m - 1                       # an index into P
    while i < n:
        if T[i] == P[k]:            # a matching character
            if k == 0:
                return i            # pattern begins at index i of text
            else:
                i -= 1              # examine previous character
                k -= 1              # of both T and P
        else:
            j = last.get(T[i], -1)  # last(T[i]) is -1 if not found
            i += m - min(k, j+ 1)   # case analysis for jump step
            k = m - 1               # restart at end of pattern
    return -1

# Set up array containing text to search
# Initialize array
i1 = []
for line in sys.stdin:
    i1.append(line.lower())             # We want the input to all be lowercase

pattern_file = open('pattern.in', 'r')  # We will read our pattern from pattern.in
pattern = pattern_file.read()           # Read the pattern to search for
pattern = pattern.rstrip()              # We need to strip any whitespace...
pattern = pattern.lower()               # ... but also make our pattern lowercase
pattern_file.close()                    # close file we are reading from

# Print out the pattern we are searching for
print("Pattern from pattern.in file is: {}\n".format(pattern))

# Time to search using the boyer moore algorithm!
print('boyer_moore')
# We need a global variable so we can check whether our last search was successful
#   outside of the for loop
index = 0
# Save our current time in time1
time1 = time.time()
# For each line in our text
for x in range(len(i1)):
# Make sure index is our global one
    global index
    index = find_boyer_moore(i1[x], pattern)
# If we get a match, print out some info about what was found
    if (index != -1): 
        time1 = time.time() - time1
        print("Pattern found at line {} character {} !".format(x, index))
        print("It took {} sec to find this pattern!".format(time1))
        print("Line {}:".format(x))
        print(i1[x])
        break

# Because we use break to exit our for loop, we need a global index variable
if index == -1: print("Pattern not found!")


# Time to search using the fmp algorithm!
# Same logic as boyer_moore. No need to recomment
print('fmp')
time1 = time.time()
for x in range(len(i1)):
    index = find_fmp(i1[x], pattern)
    if (index != -1): 
        time1 = time.time() - time1
        print("Pattern found at line {} character {} !".format(x, index))
        print("It took {} sec to find this pattern!".format(time1))
        print("Line {}:".format(x))
        print(i1[x])
        break

if index == -1: print("Pattern not found!")
