#철수의 2차원 토마토 익히기
import sys
input = sys.stdin.readline
from collections import deque

m, n = map(int, input().split())
box = [list(map(int, input().split())) for _ in range(n)]
queue = deque([])

dx = [-1, 1, 0, 0] #왼, 오
dy = [0, 0, -1, 1] #아래, 위

for x in range(n):
	for y in range(m):
		if box[x][y] == 1:
			queue.append([x, y])

def bfs():
	while queue:
		x, y = queue.popleft()
		#왼, 오, 아래, 위
		for i in range(4):
			nx = dx[i] + x
			ny = dy[i] + y 
			if 0 <= nx < n and 0 <= ny < m:
				if box[nx][ny] == 0:
					box[nx][ny] = box[x][y] + 1
					queue.append([nx, ny])

bfs()
#박스에 이런식으로 저장됨.
# [1, -1, 0, 0, 0, 0]
# [0, -1, 0, 0, 0, 0]
# [0, 0, 0, 0, -1, 0]
# [0, 0, 0, 0, -1, 1]

# [1, -1, 7, 6, 5, 4] 
# [2, -1, 6, 5, 4, 3] 
# [3, 4, 5, 6, -1, 2] 
# [4, 5, 6, 7, -1, 1]
day = 0
for row in box:
	for tomato in row:
		if tomato == 0:
			print(-1)
			exit(0)
	day = max(day, max(row))
print(day - 1)