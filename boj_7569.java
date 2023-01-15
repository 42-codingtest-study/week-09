package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj_7569 {

    static int[] dx = {1, 0, -1, 0, 0 ,0}; // DFS, BFS
    static int[] dy = {0, 1, 0, -1, 0 , 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int[][][] box; // 3차원 지도

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int H = sc.nextInt();

        box = new int[H][N][M];

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    box[i][j][k] =sc.nextInt();
                    if (box[i][j][k] == 1) q.offer(new int[]{i, j, k});
                }
            }
        }
        System.out.println(BFS(N, M, H, q));
    }

    public static int BFS(int N, int M, int H, Queue<int[]> q) {
        while (!q.isEmpty()) {
            int cur[] = q.poll();

            int z = cur[0];
            int x = cur[1];
            int y = cur[2];

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || nz < 0 || nz >= H)
                    continue;

                if (box[nz][nx][ny] == 0){
                    q.offer(new int[]{nz, nx, ny});
                    box[nz][nx][ny] = box[z][x][y] + 1;
                }
            }
        }

        int result = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 0)
                        return -1;
                    result = Math.max(result, box[i][j][k]);
                }
            }
        }
        if (result == 1) return 0;
        else return result - 1;
    }
}
