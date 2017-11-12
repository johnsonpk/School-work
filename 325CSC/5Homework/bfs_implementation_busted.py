#################################################
# Pablo Johnson
# Advanced Data Structures and Algorithms
# 11.13.17
# Homework 5
#################################################
from pythonds.graphs import Graph, Vertex
from pythonds.basic import Queue

def bfs(g, start):
    start.setDistance(0)
    start.setPred(None)
    nodeQueue = Queue()
    nodeQueue.enqueue(start)
    while (nodeQueue.size() > 0):
        currentNode = nodeQueue.dequeue()
        for nbr in currentNode.getConnections():
            if (nbr.getColor() == 'white'):
                nbr.setColor('gray')
                nbr.setDistance(currentNode.getDistance() + 1)
                nbr.setPred(currentNode)
                nodeQueue.enqueue(nbr)
        currentNode.setColor('black')

def traverse(x):
    while (x.getPred()):
        print(x.getId())
        x = x.getPred()
    print(x.getId())
       

g = Graph()
for character in range(ord('a'), ord('h') + 1):
    g.addVertex(chr(character))

print(g.vertices)
g.addEdge('a','b')
g.addEdge('b','a')

g.addEdge('b','g')
g.addEdge('g','b')

g.addEdge('g','h')
g.addEdge('h','g')

g.addEdge('h','c')
g.addEdge('c','h')

g.addEdge('a','e')
g.addEdge('e','a')
print()

print("We will do a breadth first search starting at pin 'a'")
bfs(g, g.getVertex('a'))
print()
print("Our connections are:")
print("Note that the connections are sometimes repeated since addEdge makes a unidirectional connection")
for v in g:
    for w in v.getConnections():
        print("( {} , {} )".format(v.getId(), w.getId()))


print()
print("We traverse now to see the shortest connection from c to a")
traverse(g.getVertex('c'))

print("\nWe traverse now to see the shortest connection from f to a, which doesn't exist")
traverse(g.getVertex('f'))
