A=[9,4,1,7,9,1,2,0]

min = A.min()
max = A.max()

k = [0] * len(list(range(min,max)))

for i in range(0, len(A)-1):
	k[A[i]] = k[A[i]] + 1

for i in range(0,len(k)-2):
	sk[i+1] = sk[i] + k[i]

for i in range(0, len(A)-1):
	B[sk[
