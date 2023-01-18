# 벽 부수고 이동하기
# https://www.acmicpc.net/problem/2206

from collections import deque
N, M = map(int, input().split())
graph = []
for _ in range(N) :
    graph.append(list(input()))

visited = [[[0] * 2 for _ in range(M)] for _ in range(N)]
visited[0][0][0] = 1

def bfs() :
    dq = deque()
    dq.append((0, 0, 0))
    mx = [1, -1, 0, 0]
    my = [0, 0, 1, -1]
    while dq :
        x, y, bricks = dq.popleft()
        if x == N - 1 and y == M - 1 :
            return visited[x][y][bricks]
        for i in range(4) :
            nx = x + mx[i]
            ny = y + my[i]
            if nx < 0 or nx >= N or ny < 0 or ny >= M :
                continue
            if graph[nx][ny] == '1' and bricks == 0 :
                visited[nx][ny][1] = visited[x][y][0] + 1
                dq.append([nx, ny, 1])
            elif graph[nx][ny] == '0' and visited[nx][ny][bricks] == 0 :
                visited[nx][ny][bricks] = visited[x][y][bricks] + 1
                dq.append([nx, ny, bricks])
    return -1

print(bfs())
