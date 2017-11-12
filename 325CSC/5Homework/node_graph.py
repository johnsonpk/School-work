class Node:
def __init__(self, key):
self.id = key
self.connectedTo = {}
def addNeighbor(self, nbr, weight = 0):
self.connectedTo[nbr] = weight
def __str__(self):
return str(self.id) + ' connected to: ' + str([x.id for x in self.connectedTo])
def getConnections(self):
return self.connectedTo.keys()

def getId(self):
return self.id
def getWeight(self, nbr):
return self.connectedTo[nbr]
class Graph:
def __init__(self):
self.nodeList = {}
self.numNodes = 0
def addNode(self,key):
self.numNodes = self.numNodes + 1
newNode = Node(key)
self.nodeList[key] = newNode
return newNode
def getNode(self, n):
if n in self.nodeList:
return self.nodeList[n]
else:
return None
def __contains__(self, n):
return n in self.nodeList
def addEdge(self, f, t, cost = 0):
if f not in self.nodeList:
nv = self.addNode(f)
if t not in self.nodeList:
nv = self.addNode(t)
self.nodeList[f].addNeighbor(self.nodeList[t], cost)
def __iter__(self):
return iter(self.nodeList.values())
