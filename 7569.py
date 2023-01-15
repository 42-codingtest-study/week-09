#철수의 3차원 토마토 익히기
import sys
input = sys.stdin.readline
from collections import deque

m, n, h = map(int,input().split())
#쫄지마,, 고작 삼차원이야,,
boxes = [[list(map(int, input().split())) for _ in range(n)] for _ in range(h)]
queue = deque([])

dx = [-1, 1, 0, 0, 0, 0]
dy = [0, 0, -1, 1, 0, 0]
dz = [0, 0, 0, 0, -1, 1]

for z in range(h):
    for x in range(n):
        for y in range(m):
            if boxes[z][x][y] == 1:
                queue.append((z, x, y))

def bfs():
    while queue:
        z, x, y = queue.popleft()
        for i in range(6):
            nx = x + dx[i]
            ny = y + dy[i]
            nz = z + dz[i]
            if 0 <= nx < n and 0 <= ny < m and 0 <= nz < h:
                if boxes[nz][nx][ny] == 0:
                    boxes[nz][nx][ny] = boxes[z][x][y] + 1
                    queue.append((nz, nx, ny))

bfs()
day = 0
for box in boxes:
    for row in box:
        for tomato in row:
            if tomato == 0:
                print(-1)
                exit(0)
        day = max(day, max(row))
print(day - 1)