# 출력 나오는 부분에서 max값 잡는 거 도움 받음
from collections import deque
import sys
input = sys.stdin.readline      
 
def bfs():
    while queue:
        a, b = queue.popleft() 
        # 토마토 상하좌후 탐색
        for i in range(4):
            x = a + dx[i]
            y = b + dy[i]
            # print(x,y)
        # 좌표의 크기를 넘어가면 안 되고 0이어야 함(익지 않은 상태)
            if 0 <= x < n and 0 <= y < m:
                # print(graph)
                if graph[x][y] == 0:
                    graph[x][y] = graph[a][b] + 1
                    queue.append([x,y])  
                           
m,n = map(int, input().split())
queue = deque([])
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]  #오왼아래위
graph = []
count = 0            
    
for _ in range(n):
    graph.append(list(map(int, input().split())))
# print(graph)

for i in range(n):
    for j in range(m):
        if graph[i][j] == 1:
            queue.append((i,j))
# print(queue)

bfs()
# print(graph)
for i in graph:
    for j in i:
        if j == 0:
            print("-1")
            quit()
    count = max(count, max(i))
print(count-1)