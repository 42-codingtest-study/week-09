import java.io.*;
import java.util.*;

public class Boj_2206 {
    static class Pair {
        int x;
        int y;
        boolean chance;
        int cnt;
        public Pair(int x, int y, boolean chance, int cnt) {
            this.x = x;
            this.y = y;
            this.chance=  chance;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {


       // int n,m;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());

        String[] inputs = bf.readLine().split(" ");

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

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                int nxt_cnt = cur.cnt+1;

                if (table[nx][ny] == '0'){
                    if (!cur.chance && !visited[nx][ny][0]) {
                        q.add(new Pair(nx,ny, false, nxt_cnt));
                        visited[nx][ny][0] = true;
                    } else if (cur.chance && !visited[nx][ny][1]) {
                        q.add(new Pair(nx,ny, true, nxt_cnt));
                        visited[nx][ny][1] = true;
                    }
                } else if (table[nx][ny] == '1') {
                    if (!cur.chance) {
                        q.add(new Pair(nx,ny, true, nxt_cnt));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }

}

