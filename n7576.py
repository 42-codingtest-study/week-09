from collections import deque

m, n = map(int, input().split())

graph = [] # 좌표로 표시하기 위해서
queue = deque([])

for i in range(n):
    graph.append(list(map(int, input().split())))

    for j in range(m):
        if graph[i][j] == 1:
            queue.append([i, j]) # 익은 토마토의 좌표를 큐에 저장

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

while queue:
    x, y = queue.popleft()

    for i in range(4):
        # 익은 토마토 상하좌우 확인
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx < n and 0 <= ny < m:
            if graph[nx][ny] == 0:
                graph[nx][ny] = graph[x][y] + 1
                queue.append([nx, ny])

answer = 0

for line in graph:
    for tomato in line:
        if tomato == 0:
            # 안익은 토마토가 있으면 (=0) 정지
            print(-1)
            exit()
    answer = max(answer, max(line))

print(answer - 1)
