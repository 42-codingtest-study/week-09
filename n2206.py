from collections import deque

n, m = map(int, input().split())

graph = []
for _ in range(n):
    row = list(str(input().rstrip()))
    graph.append(list(map(int, row)))

# 3차원 배열
# z항이 0 = 벽 안 뚫음 / 1 = 벽 뚫음
moves = [
    [1, 0],
    [-1, 0],
    [0, 1],
    [0, -1]
]
visited = [[[0] * 2 for _ in range(m)] for _ in range(n)]

def bfs():
    q = deque()
    q.append([0, 0, 0])
    visited[0][0][0] = 1

    while q:
        x, y, w = q.popleft()

        # 도착시 거리 리턴
        if x == n - 1 and y == m - 1:
            return visited[x][y][w]

        for move in moves:
            nx, ny = x + move[0], y + move[1]

            if 0 <= nx < n and 0 <= ny < m:
                # 위치 이동 가능 + 아직 방문x
                if graph[nx][ny] == 0 and visited[nx][ny][w] == 0:
                    visited[nx][ny][w] = visited[x][y][w] + 1
                    q.append([nx, ny, w])

                # 벽 도착 + 벽 부술 수o
                elif graph[nx][ny] == 1 and w == 0:
                    visited[nx][ny][w + 1] = visited[x][y][w] + 1
                    q.append([nx, ny, w + 1])

    # 도착 x  -> -1리턴
    return -1


print(bfs())