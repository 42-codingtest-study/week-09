#2분법,, 어려워요우
import sys
input = sys.stdin.readline
from collections import deque

k = int(input())

def bfs(num):
	queue = deque()
	queue.append(num)
	visited[num] = 1
	while queue:
		x = queue.popleft()
		for i in graph[x]:
			if visited[i] == 0:
				queue.append(i)
				# 상위 정점과 다른 그룹으로 편성
				visited[i] = -1 * visited[x]
			# 온적있는데 같은 그룹이면 탈락
			elif visited[i] == visited[x]:
				return False
	return True

for _ in range(k):
	v, e = map(int, input().split())
	graph = [[] for _ in range(v + 1)]
	visited = [0] * (v + 1)

	for _ in range(e):
		a, b = map(int, input().split())
		# 무방향 그래프
		graph[a].append(b)
		graph[b].append(a)
	
	for i in range(1, v + 1):
		if visited[i] == 0:
			result = bfs(i)
			if not result:
				break

	# if result:
	# 	print('YES')
	# else :
	# 	print('NO')
	print('YES' if result else 'NO')
