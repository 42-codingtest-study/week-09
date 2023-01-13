# 이분 그래프
# https://www.acmicpc.net/problem/1707

# 색칠하기 문제와 비슷하다

import sys
from collections import deque

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline
N = int(input())
for _ in range(N) :
    V, E = map(int, input().split())
    graph = [[] for _ in range(V + 1)]
    for _ in range(E) :
        u, v = map(int,input().split())
        graph[u].append(v)
        graph[v].append(u)
    
    color_map = [-1] * (V + 1)		# -1로 초기화한 지도 사용
    for i in range(1, V + 1) :		# 그래프가 두개 이상으로 나뉘어있을 수 있으므로 전체적으로 확인해야 한다.
        if color_map[i] != -1 :
            continue
        color_map[i] = 0
        dq = deque([i])		# deque, bfs 사용 (dfs로 해봤는데 어째서인지 시간초과가 났었다)
        while dq :
            curr = dq.popleft()
            for next in graph[curr] :
                if color_map[next] == -1 :
                    color_map[next] = (color_map[curr] + 1) % 2	# 색칠을 0 혹은 1로 칠해준다.
                    dq.append(next)
    
    answer = True
    for i in range(1, V + 1) :
        color = color_map[i]
        for j in graph[i] :
            if color_map[j] == color :
                answer = False
    
    if answer :
        print("YES")
    else :
        print("NO")