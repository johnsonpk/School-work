################################################
# Pablo Johnson
# CSC 325
# Trie Implementation
# 11.7.2017
################################################


# Node class for our, well, nodes :)
class Node(object):
# init the node
# we want our self.word variable to tell us if this node is the end of a word
# prev will point to the parent node
# keys will hold an array of none until assigned a child
# we use 128 here because I originally expected to have to do all ascii characters
# i.e. you could add "Hello.!" and "Hello" would not register as being stored.
    def __init__(self, word, prev):
        self.word = word
        self.prev = prev
        self.keys = [None] * 128


# This is our Trie class, it utilizes the node class we created earlier
# It ignores all non alpha characters, including when checking for is a word is stored
#   i.e. if you store "Hi!" only "Hi" will be stored.
#   likewise, if you check .isStored("Hi!") and "Hi" is stored, it will return True
class Trie(object):
    # We will init, setting our root to None and our words to add to either "" or whatever is
    #   passed in. If text is passed in, we will go ahead and utilize our add function
    def __init__(self, words_to_add = ""):
        self.root = None
        self.words_to_add = words_to_add

        if len(self.words_to_add) > 0:
            self.add(self.words_to_add)

    # Function to check if a word is stored. It does differentiate between subwords and words.
    #   i.e. if "Piper" is stored, "Pipe" will not return as being stored since it was not 
    #   explicitly stored
    def isStored(self, text):
        for i in range(len(text)):
            if not ((65 <= ord(text[i]) <= 90) or (97 <= ord(text[i]) <= 122)):
                text = text[:i] + " " + text[i+1:]
        node = self.root
        for i in range(len(text)):
            if node.keys[ord(text[i])] is not None:
                node = node.keys[ord(text[i])]
            # Will only return true if the last letter indicates that it is the end of a word that has
            #   been explicitly stored
            if ((i == (len(text) - 1)) and (node.word)):
                return True
        # If we reach the end of the word but it hasn't been explicitly stored, return False
        return False
        
    # Our add function. This is the bulk of the class
    def add(self, text):
    # We want to only take alpha characters in.
    #   so if we have "Piper.", we will only store "Piper"
        for i in range(len(text)):
            if not ((65 <= ord(text[i]) <= 90) or (97 <= ord(text[i]) <= 122)):
                text = text[:i] + " " + text[i+1:]
        # Split up the input text to a list
        text = text.split()
        for pattern in text:
            # If this is the first word we add, need to make sure it branches from root
            if self.root == None:
                prev_node = Node(False, None)
                self.root = prev_node
                for i in range(len(pattern)):
                    # If we are on the last letter, we need to mark it as the end of a word
                    if (i == (len(pattern) - 1)):
                        new_node = Node(True, prev_node)
                    else:
                        new_node = Node(False, prev_node)
                        prev_node.keys[ord(pattern[i])] = new_node
                        prev_node = new_node

            else:
                prev_node = self.root
                for i in range(len(pattern)):
                    # If a node exists with our letter:
                    if (prev_node.keys[ord(pattern[i])] is not None):
                        # If we are on the last letter of the word being stored and the character already
                        #   has been stored from a previous word, set the letter to mark that it is the end of the word
                        if (i == (len(pattern) - 1)):
                            prev_node.keys[ord(pattern[i])].word = True
                        prev_node = prev_node.keys[ord(pattern[i])]
                    else:
                        # Making sure the last letter indicates it is the end of a stored word
                        if (i == (len(pattern) - 1)):
                            new_node = Node(True, prev_node)
                        else:
                            new_node = Node(False, prev_node)
                        prev_node.keys[ord(pattern[i])] = new_node
                        prev_node = new_node


# This is some text code I was using
"""
text = "Peter Piper picked a peck of pickled peppers; A peck of pickled peppers Peter Piper picked;"
x = Trie(text)
text = text.split()
for word in text:
    print("{} is stored: {}".format(word, x.isStored(word)))
"""    


##### ENTER CODE AFTER HERE TO TEST CLASS #####
# initialize Trie by x = Trie(<string of words to add>)
# COMMANDS ARE .add(<text to add>), .isStored(<text to check>)
