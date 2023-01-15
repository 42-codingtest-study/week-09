#그녀는 비얌비얌비얌 같은 여자
#근데 인생이 이렇게 호락호락하게 되나
import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())
visited = [0] * 101
queue = deque([1])
# 보드에 숫자표시, 올라가고 내려가는 애들 도착위치 표시
board = [i for i in range(101)]
for _ in range(n + m):
    a, b = map(int, input().split())
    board[a] = b


def bfs(v):
    visited[v] = 1
    while queue:
        target = queue.popleft()
        for dice in range(1, 7):
            next = target + dice
			#100넘으면 넘기고 다음 반복
            if next > 100:
                continue
            cur = board[next]
			#안가봤으면 탐색
            if visited[cur] == 0:
                queue.append(cur)
                visited[cur] = visited[target] + 1
                if cur == 100:
                    return

bfs(1)
print(visited[100] - 1)