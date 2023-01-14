import java.io.*;
import java.util.*;

public class Boj_2206 {
    static class Pair {
        int x;
        int y;
        boolean chance;         //벽 부수기 찬스를 썼는지 안썼는지
        int cnt;
        public Pair(int x, int y, boolean chance, int cnt) {
            this.x = x;
            this.y = y;
            this.chance=  chance;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = bf.readLine().split(" "); //토크나이저 안쓰고 바로 분리가능 했네

        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        char table[][] = new char[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = bf.readLine();
            for (int j = 0; j < m; j++) {
                table[i][j] = tmp.charAt(j);
            }
        }
        dfsFunc(table, n, m);
    }

    public static void dfsFunc(char[][] table, int n, int m) {
        boolean visited[][][] = new boolean[n][m][2];
        Queue<Pair> q = new LinkedList<>();
        List<Integer> dir_x = List.of(1,0,-1,0);
        List<Integer> dir_y = List.of(0,1,0,-1);

        q.add(new Pair(0,0,false, 1));

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            if (cur.x == n-1 && cur.y == m-1) {
                System.out.println(cur.cnt);
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dir_x.get(dir);
                int ny = cur.y + dir_y.get(dir);

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;   //범위 벗어난 경우
                int nxt_cnt = cur.cnt+1;

                if (table[nx][ny] == '0'){  // 벽이 아닌 경우
                    if (!cur.chance && !visited[nx][ny][0]) {   //벽을 지금까지 안부섰고, 방문한적이 없다.
                        q.add(new Pair(nx,ny, false, nxt_cnt));
                        visited[nx][ny][0] = true;
                    } else if (cur.chance && !visited[nx][ny][1]) { //이미 벽을 부섰고, 방문한적이 없다.
                        q.add(new Pair(nx,ny, true, nxt_cnt));
                        visited[nx][ny][1] = true;
                    }
                } else if (table[nx][ny] == '1') {  //벽인 경우
                    if (!cur.chance) {  // 아직 한번도 안부섰으면 부수고 들어감.
                        q.add(new Pair(nx,ny, true, nxt_cnt));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}

