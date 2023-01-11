import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Boj_7569 {

    static int[][][] visited = new int[102][102][102];
    static List <Integer> x_dir = List.of(1, 0, 0, -1, 0 ,0 );
    static List <Integer> y_dir = List.of(0, 1, 0, 0, -1 ,0 );
    static List <Integer> z_dir = List.of(0, 0, 1, 0, 0 ,-1 );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m,n,h;
        int table[][][] = new int[102][102][102];
        Queue<Coordinate> q = new LinkedList<>();

        m = scanner.nextInt(); n = scanner.nextInt(); h = scanner.nextInt();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    int tmp = scanner.nextInt();
                    table[j][k][i] = tmp;
                    if (table[j][k][i] == 1) {
                        q.add(new Coordinate(j, k, i));
                    }
                    if (table[j][k][i] == 0) {
                        visited[j][k][i] = -1;
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            Coordinate cur = q.peek(); q.remove();
            for (int dir = 0; dir < 6; dir++) {
                int nx = cur.x + x_dir.get(dir);
                int ny = cur.y + y_dir.get(dir);
                int nz = cur.z + z_dir.get(dir);
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || nz < 0 || nz >= h) continue;
                if (visited[nx][ny][nz] >= 0) continue;
                visited[nx][ny][nz] = visited[cur.x][cur.y][cur.z]+1;
                //visited[nx][ny][nz] = 1;
                q.add(new Coordinate(nx,ny,nz));
                //   System.out.println("q size is   : " + q.size());
                //cnt++;
            }
        }
        int ans = -1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (visited[j][k][i] == -1) {
                        System.out.println(-1);
                        return;
                    }
                    ans = Math.max(ans, visited[j][k][i]);
                }
            }
        }
        System.out.println(ans);

    }

    public static class Coordinate {
        int x, y, z;

        Coordinate(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
