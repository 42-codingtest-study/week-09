# 혼자 풀었나요 ? X -> 구현하는 부분에 대해 감이 안 잡혀서 로직 검색함
import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
dice = [1, 2, 3, 4, 5, 6]
board = [0] * 101 #1~100
visited = [False] * 101

ladder, snake = dict(), dict()

for _ in range(n):
    a, b = map(int, input().split())
    ladder[a] = b
    
for _ in range(m):
    a, b = map(int, input().split())
    snake[a] = b
    
def bfs(i):
    queue = deque()
    queue.append(i)
    visited[i] = True
    while queue:
        x = queue.popleft()
        for i in dice:
            next = x + i
            if 1 <= next <= 100:
                if next in ladder:
                    next = ladder[next]
                if next in snake:
                    next = snake[next]
                if not visited[next]:
                    queue.append(next)
                    visited[next] = True
                    board[next] = board[x] + 1
                    
bfs(1)
print(board[100])
                    
            

# 이동하는 위치에 사다리가 있으면 사다리를 타고 올라가야 함
# 이동하는 위치에 뱀이 있으면 뱀을 타고 내려가야 함

# N개의 사다리의 (시작점, 끝점)과 M개의 뱀의 (시작점, 끝점)을 각각 dictionary에 넣어준다.


