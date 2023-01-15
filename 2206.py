#뿌셔뿌셔
import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())
board = [list(map(int, input().rstrip())) for _ in range(n)]
visited = [[[0] * 2 for _ in range(m)] for _ in range(n)]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def bfs():
    # 0,0에서 벽 안부수고 출발
	queue = deque([(0, 0, 0)])
	visited[0][0][0] = 1
	
	while queue:
		x, y, wall = queue.popleft()
        #시작 11인데 나는 00이니까
		if x == n - 1 and y == m - 1:
			return visited[x][y][wall]
			
		for i in range(4):
			nx = x + dx[i]
			ny = y + dy[i]
            # 맵 범위안에서
			if 0 <= nx < n and 0 <= ny < m and visited[nx][ny][wall] == 0:
                #벽이면 고고
				if board[nx][ny] == 0:
					queue.append((nx, ny, wall))
					visited[nx][ny][wall] = visited[x][y][wall] + 1
					
				if wall == 0 and board[nx][ny] == 1:
					queue.append((nx, ny, 1))
					visited[nx][ny][1] = visited[x][y][wall] + 1
					
	return -1

print(bfs())