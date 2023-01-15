from collections import deque

n, m = map(int, input().split())

graph = [i for i in range(101)]
visited = [-1] * 101

for _ in range(n):
    x, y = map(int, input().split())
    graph[x] = y

for _ in range(m):
    u, v = map(int, input().split())
    graph[u] = v

def bfs(i):
    q = deque()
    q.append(i)
    visited[i] = 0

    while q:
        node = q.popleft()
        for i in range(1, 7):
            n = node + i
            if n > 100:
                continue
            t = graph[n]
            if visited[t] == -1:
                q.append(t)
                visited[t] = visited[node] + 1
                if t == 100:
                    return

bfs(1)
print(visited[100])