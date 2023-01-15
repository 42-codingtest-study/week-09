package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1707 {

    static List<Integer>[] adj;
    static int[] RB;
    static int[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < K; i++) {

            stringTokenizer = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(stringTokenizer.nextToken());
            int E = Integer.parseInt(stringTokenizer.nextToken());

            // 테스트 케이스 반복으로 인한 자료구조 초기화
            adj = new ArrayList[20001];
            RB = new int[20001];
            isVisited = new int[20001];

            // 내부 리스트 초기화
            for (int j = 0; j <= V; j++) {
                adj[j] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(stringTokenizer.nextToken());
                int v = Integer.parseInt(stringTokenizer.nextToken());

                adj[u].add(v);
                adj[v].add(u);
            }
            make_group(V);
        }
    }

    private static void make_group(int V) {

        for (int i = 0; i < V; i++) {
            // 초기 시작점 세팅
            Queue<Integer> q = new LinkedList<>();
            if (isVisited[i + 1] == 0) {
                q.add(i + 1);
                isVisited[i + 1]++;
                RB[i + 1] = 1; // red
            }

            while (!q.isEmpty()) {

                int now = q.poll();
                List<Integer> adjacents = adj[now];

                for (Integer adjacent : adjacents) {
                    if (RB[adjacent] == RB[now]) {
                        System.out.println("NO");
                        return;
                    }

                    // Red or Black
                    if (isVisited[adjacent] == 0) {
                        isVisited[adjacent]++;
                        if (RB[now] == 1) {
                            RB[adjacent] = 2; // black
                        }
                        if (RB[now] == 2) {
                            RB[adjacent] = 1; // red
                        }
                        q.add(adjacent);
                    }
                }
            }
        }
        System.out.println("YES");
    }
}

