from collections import deque

k = int(input())

def dfs (x, group):
    visited[x] = group

    for nx in graph[x]:
        if visited[nx] == 0:
            if not dfs(nx, -group):
                return False
        elif visited[nx] == group:
            return False

    return True


for _ in range(int(input())):
    v, e = map(int, input().split())
    graph = [[] for _ in range(v + 1)]
    visited = [0] * (v + 1)
    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    bipartite = True
    for i in range(1, v + 1):
        if visited[i] != 0:
            continue
        if not bipartite:
            break

        bipartite = dfs(i, 1)

    print("YES" if bipartite else "NO")