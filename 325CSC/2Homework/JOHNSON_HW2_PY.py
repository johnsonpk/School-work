#P = [3,1,4,1,5,9,2,6,5,3,5,8,9,8]
P = [2,3,4,5,6,7,8,9,10,11,12,13,14,15]

class Stack:
	def __init__(self):
		self.items = []

	def push(self, item):
		self.items.append(item)

	def pop(self):
		return self.items.pop()

	def peek(self):

		return self.items[len(self.items)-1]
	
	def isEmpty(self):
		return self.items == []
	
	def size(self):
		return len(self.items)

def computeSpans1(P):
	S = [0] * len(P)
	for i in range(0,len(P)):
		k = 0
		done = True
		while ((k <=i) and done):
			if (P[i-k] <= P[i]):
				k = k+1
			else:
				done = False 
		S[i] = k
	return S

def computeSpans2(P):
	S = [0] * len(P)
	D = Stack()	
	for i in range(0,len(P)):
		h = 0
		done = False
		while ((D.isEmpty()==False) and (done==False)):
			if P[i] >= P[D.peek()]:
				D.pop()
			else:
				done = True
		if D.isEmpty():
			h = -1
		else:
			h = D.peek()
		S[i] = (i-h)
		D.push(i)
	return S


print(computeSpans1(P))
print(computeSpans2(P))
