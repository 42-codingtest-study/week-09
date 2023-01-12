from collections import deque
import sys
input = sys.stdin.readline      
 
def bfs():
    while queue:
        a, b, c = queue.popleft() 
        # 토마토 상하좌후 탐색
        for i in range(6):
            x, y, z = a + dx[i], b + dy[i], c + dz[i]
            # print(type(x))
            # 좌표의 크기를 넘어가면 안 되고 0이어야 함(익지 않은 상태)
            if 0 <= x < h and 0 <= y < n and 0 <= z < m:
                # print(graph)
                if graph[x][y][z] == 0:
                    graph[x][y][z] = graph[a][b][c] + 1
                    queue.append([x,y,z])  
                           
m,n,h = map(int, input().split())
queue = deque()
graph = [[] for _ in range(h)]
dx = [0,0,0,0,1,-1]
dy = [0,0,1,-1,0,0]
dz = [1,-1,0,0,0,0]
count = 0            

for i in range(h):
    for _ in range(n):
        graph[i].append(list(map(int,input().split())))   
# print(graph)

for i in range(h):
    for j in range(n):
        for k in range(m):
            if graph[i][j][k] == 1:
                queue.append([i,j,k])
# print(queue)

bfs()
# print(graph)
for a in graph:
    for b in a:
        for c in b:
            if c == 0:
                print("-1")
                exit(0)
        count = max(count, max(b))
print(count-1)


# directions = [(1, 0, 0), (-1, 0, 0), (0, 0, -1), (0, 0, 1), (0, -1, 0), (0, 1, 0)]
# TypeError: 'int' object is not iterable
# TypeError: 'int' object is not subscriptable
# 방향을 위처럼 주고 싶었는데 형이 안 맞는 오류가 계속 나와서 인터넷 도움을 슬쩍 받음 ㅎㅎ