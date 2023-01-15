package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16928 {
    static int[] sl = new int[101];
    static int[] dist = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        Arrays.fill(dist, -1);

        for (int i = 0; i < N + M; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            sl[x] = y;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dist[1] = 0;

        while (!q.isEmpty()){
            int x = q.poll();

            for (int i = 1; i <= 6; i++){
                int nx = x + i;

                if (nx > 100)
                    continue;

                if (isSnakeOrLadder(nx) != -1)
                    nx = isSnakeOrLadder(nx);

                if (dist[nx] == -1){
                    q.add(nx);
                    dist[nx] = dist[x] + 1;
                }
            }
        }
        System.out.println(dist[100]);
    }

    private static int isSnakeOrLadder(int nx) {

        for (int pos : sl)
            if (sl[nx] != 0)
                return sl[nx];
        return -1;
    }
}
