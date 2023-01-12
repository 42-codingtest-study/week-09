# 토마토
# https://www.acmicpc.net/problem/7576

M, N = map(int, input().split())
box = []
for _ in range(N) :
    box += list(map(int, input().split()))	# 1차원으로 받아줬다

day = 0
move = [1, -1, M, -M]	# 1차원 상하좌우
to_lst = [[i for i in range(len(box)) if box[i] == 1]]	# 0일째 토마토 위치
while 1 :
    cnt = 0
    day_tomato = to_lst.pop(0)
    next = []
    for idx in day_tomato :
        if idx + 1 < M * N and (idx + 1) % M != 0 and box[idx + 1] == 0:	# 조건에 만족하면 옆칸에 토마토 심을 수 있음
            box[idx + 1] = 1
            next.append(idx + 1)
            cnt += 1
        if idx - 1 >= 0 and idx % M != 0 and box[idx - 1] == 0 :
            box[idx - 1] = 1
            next.append(idx - 1)
            cnt += 1
        if idx + M < M * N and box[idx + M] == 0 :
            box[idx + M] = 1
            next.append(idx + M)
            cnt += 1
        if idx - M >= 0 and box[idx - M] == 0 :
            box[idx - M] = 1
            next.append(idx - M)
            cnt += 1
    if cnt == 0 :	# 하나도 못심었다면 다 심은거임
        break
    day += 1
    if len(next) > 0 :
        to_lst.append(next)
        
if len([1 for i in box if i == 0]) > 0 :
	print(-1)
else :
	print(day)