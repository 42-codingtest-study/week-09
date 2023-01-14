import java.util.*;

public class Boj_7576 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m, n;
        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        Queue<Pair> q = new LinkedList<>();
        int visited[][] = new int[n+1][m+1];
        int table[][] = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(scanner.nextLine());
            for (int j = 0; j < m; j++) {
                table[i][j] = Integer.parseInt(st2.nextToken());
                if (table[i][j] == 1) {     //초기 토마토 있는 상태일 때 큐에 추가해 줍니다.
                    q.add(new Pair(i, j));
                    visited[i][j] = 1;
                }
                if (table[i][j] == -1) {
                    visited[i][j] = -1;
                }
            }
        }

        int dir_x[] = {1, 0, -1, 0};
        int dir_y[] = {0, 1, 0, -1};
        while (!q.isEmpty()) {          //BFS 수행
            Pair cur = q.peek();
            q.remove();
            for (int i = 0; i < 4; i++) {
                if (cur.x +dir_x[i] < 0 || cur.x +dir_x[i] >= n || cur.y + dir_y[i] < 0 || cur.y + dir_y[i] >= m) continue;
                if (table[cur.x +dir_x[i]][cur.y + dir_y[i]] == -1) continue;
                if (visited[cur.x + dir_x[i]][cur.y + dir_y[i]] > 0) continue;
                // 값 범위를 벗어났거나 토마토가 없는 칸, 이전에 방문한 적 있는 칸이면 continue
                visited[cur.x +dir_x[i]][cur.y + dir_y[i]] = visited[cur.x][cur.y] + 1;
                q.add(new Pair(cur.x +dir_x[i], cur.y + dir_y[i]));
            }
        }

        int maxVal = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                maxVal = Integer.max(maxVal, visited[i][j]);
            }

        }
        System.out.println(maxVal - 1);

    }

    public static class Pair {
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y =y;
        }
    }

}
