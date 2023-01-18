# 뱀과 사다리 게임
# https://www.acmicpc.net/problem/16928

import heapq
N, M = map(int, input().split())
graph = [i for i in range(101)]	# [0 - 100]
for _ in range(N + M) :
    x, y = map(int, input().split())
    graph[x] = y

answer = []
visited = [1, 1] + [0] * 101	# 방문여부 확인 해주어야 계속 돌아가는것을 방지할 수 있다.
heapq.heappush(answer, (0, 1))	# 힙을 사용해서 계속 낮은 카운트 순으로 뽑아줄 수 있다.
while 1 :
    tmp = heapq.heappop(answer)
    if tmp[1] == 100 :	# 100이 되면 바로 출력하고 종료
        print(tmp[0])
        break
    if tmp[1] > 100 :
        continue
    for i in range(1, 7) :
        next = tmp[1] + i
        if next < 100 and next != graph[next] :
            next = graph[next]
        if next <= 100 and visited[next] == 0 :
            visited[next] = 1
            heapq.heappush(answer, (tmp[0] + 1, next))
