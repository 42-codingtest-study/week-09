import sys
from collections import deque
input = sys.stdin.readline

k = int(input())

for _ in range(k):
    v, e = map(int,input().split())
    graph = [[] for _ in range(v+1)]
    visited = [0] * (v + 1) #방문한 정점
    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
    
    queue = deque()
    result = True
    group = 1
    for i in range (v):
        if visited[i] == 0:
            queue.append(i)
            visited[i] = group
        while queue:
            x = queue.popleft()
            for v in graph[x]:
                if visited[v] == 0:
                    visited[v] = visited[x] + 1
                    queue.append(v)
                    
                if visited[v] == visited[x]:
                    result = False
    
    if result:
        print("YES")
    else:
        print("NO")
        
        

