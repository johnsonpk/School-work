class Node(object):
    def __init__(self, data, prev):
        self.data = data
        self.prev = prev
        self.keys = [None] * 128

class Trie(object):
    def __init__(self, words_to_add = []):
        self.root = None
        self.words_to_add = words_to_add
        if len(self.words_to_add) > 0:
            for x in self.words_to_add:
                self.add(x)

    def isStored(self, text):
        node = self.root
        for letter in text:
            if node.keys[ord(letter)] is not None:
                node = node.keys[ord(letter)]
            else:
                return False

        return True
        
    def add(self, pattern):
        if self.root == None:
            prev_node = Node(None, None)
            self.root = prev_node
            for i in range(len(pattern)):
                new_node = Node(ord(pattern[i]), prev_node)
                prev_node.keys[ord(pattern[i])] = new_node
                prev_node = new_node
        else:
            prev_node = self.root
            for i in range(len(pattern)):
                if prev_node.keys[ord(pattern[i])] is not None:
                    prev_node = prev_node.keys[ord(pattern[i])]
                else:
                    new_node = Node(ord(pattern[i]), prev_node)
                    prev_node.keys[ord(pattern[i])] = new_node
                    prev_node = new_node

    def printAllWords(self):
        string = []
        def recursePrint(node, string):
            for i in range(128):
                if node.keys[i] is not None:
                    string += chr(node.keys[i].data)
                    return recursePrint(node.keys[i], string)
            return string

        for i in range(128):
            if self.root.keys[i] is not None:
                string.append(recursePrint(self.root.keys[i], chr(self.root.keys[i].data)))


        return string


x = Trie(["Hi","boo"])
print(x.printAllWords())

exit()
print(x.isStored("Hi"))
print(x.isStored("Hii"))
print(x.isStored("blah"))
print(x.root.keys[ord('H')].data)
print(x.root.keys[ord('H')].keys[ord('i')].data)
print(x.root.keys[ord('b')].data)
print(x.root.keys[ord('b')].keys[ord('o')].data)
print(x.root.keys[ord('b')].keys[ord('o')].keys[ord('o')].data)
#print(x.root.keys[ord('b')].keys[ord('o')].keys[ord('o')].keys)
