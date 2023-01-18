# 토마토
# https://www.acmicpc.net/problem/7569

M, N, H = map(int, input().split())
box = []
for _ in range(N * H) :
    box += list(map(int, input().split()))	# 1차원으로 받아줬다

MNH = M * N * H
day = 0
to_lst = [set([i for i in range(len(box)) if box[i] == 1])]	# 0일째 토마토 위치
while 1 :
    cnt = 0
    day_tomato = to_lst.pop(0)
    next = set()
    for t in list(day_tomato) :
        m = [t + 1, t - 1, t + M, t - M, t + M * N, t - M * N]	# 1차원 상하좌우
        if m[0] < MNH and m[0] % M != 0 and box[m[0]] == 0:	# 조건에 만족하면 옆칸에 토마토 심을 수 있음
            box[m[0]] = 1
            next.add(m[0])
            cnt += 1
        if m[1] >= 0 and t % M != 0 and box[m[1]] == 0 :
            box[m[1]] = 1
            next.add(m[1])
            cnt += 1
        if m[2] < MNH and t % (M * N) - (M * (N - 1)) < 0 and box[m[2]] == 0 :
            box[m[2]] = 1
            next.add(m[2])
            cnt += 1
        if m[3] >= 0 and t % (M * N) >= M and box[m[3]] == 0 :
            box[m[3]] = 1
            next.add(m[3])
            cnt += 1
        if m[4] < MNH and box[m[4]] == 0 :
            box[m[4]] = 1
            next.add(m[4])
            cnt += 1
        if m[5] >= 0 and box[m[5]] == 0 :
            box[m[5]] = 1
            next.add(m[5])
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
