#include <iostream>
#include <algorithm>
#include <utility>
#include <vector>
#include <string>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <map>

using namespace std;

#define ffor(i, x) for (int (i) = 0; (i) < (x) ; (i)++) // ⭐️ 0부터 x까지 for-loop
#define fffor(i, x) for (int (i) = 1; (i) <= (x) ; ++(i)) // ⭐️ 1부터 x까지 for-loop
#define cc(x) cin >> (x); // ⭐️ stdin 인풋 하나 받기
#define ccc(x) int (x); cin >> (x); // ⭐️ int 변수 만들고 그걸로 stdin 인풋 하나 받기
#define coo(x) cout << x << '\n'; // ⭐️ 변수 출력하고 줄바꿈
#define cob(x) cout << x << ' '; // ⭐️ 변수 출력하고 스페이스바
#define ii pair<int, int> // ⭐️ 보통 vector랑 많이 쓴다
#define ll long long // ⭐️ 매번 long long 치고 있으면 현기증이 옴

int dx[] = {1, 0, -1, 0}; // 동 남 서 북 // ⭐️ DFS, BFS 문제 풀 때 꼭 씀
int dy[] = {0, 1, 0, -1}; // ⭐️ 이렇게 안하고 2x4 배열로 만들어 쓰는 사람들도 있는 것 같다
int INF = 1e9 + 7; // ⭐️ 의외로 자주 쓰는 수. 자주 쓰고 말고는 문제를 풀면서 생각해보면 될 문제

int n,m;
int box[1001][1001];
int visited[1001][1001];
queue<ii> q;
int days = -1;
bool flag = false;

int main()
{
	ios_base::sync_with_stdio(false); 
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> m >> n;
	ffor(i, n){
		ffor(j, m){
			cin >> box[i][j];
			visited[i][j] = -1;

			if (box[i][j] == 1){
				q.push({i,j});
				visited[i][j]++;
			}
		}
	}

	while (!q.empty()){
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx <= n && 0 <= ny && ny <= m
				&& box[nx][ny] == 0 && visited[nx][ny] == -1){
					q.push({nx,ny});
					visited[nx][ny] = visited[x][y]+1;
				}
		}
	}

	ffor(i, n){
		ffor(j, m){
			if (days <= visited[i][j]){
				days = visited[i][j];
			}
			if (box[i][j] == 0 && visited[i][j] == -1){
				days = -1;
				flag = true;
				break;
			}
		}
		if (flag)
			break;
	}

	cout << days;
	return (0);
}
