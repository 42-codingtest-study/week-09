import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [이분 그래프]
 *
 * 색칠되지 않은 모든 정점에 대해 BFS 를 진행합니다
 * BFS 의 시작 정점을 임의의 색상으로 색칠합니다
 * 연결된 정점을 다른 색상으로 색칠합니다
 * 연결된 정점이 서로 동일한 색상을 가지는 경우 이분 그래프가 아닙니다
 * 모든 정점이 각 독립된 그룹을 구성하면 이분 그래프 입니다
 *
 * 방문 처리를 색상 배열로 채킹이 가능합니다
 *
 * [Skill]
 * BFS 에서 방문 처리 배열을 또다른 값을 저장하는 배열로 활용하자
 * 해당 문제에서는 숫자를 저장하는 곳을 방문 처리 배열에 사용했습니다
 */
public class BOJ01707 {
    Queue<Integer> queue;
    ArrayList<ArrayList<Integer>> graph;
    int[] visited;
    int result;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        int V, E, u, v;

        for (int idx = 0; idx < testCase; idx++) {
            graph = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            visited = new int[V + 1];
            result = 0;

            for (int i = 0; i < V + 1; i++) {
                graph.add(new ArrayList<>());
            }

            makeGraph(br, E);
            checkBipartiteGraph(V);
            printResult();
        }
    }

    private void checkBipartiteGraph(int V) {
        for (int i = 1; i < V + 1; i++) {
            if (visited[i] == 0)
                bfs(i);
            if (result == 1)
                break;
        }
    }

    private void printResult() {
        if (result == 0) {
            System.out.println("YES");
        }
        else System.out.println("NO");
    }

    private void makeGraph(BufferedReader br, int E) throws IOException {
        StringTokenizer st;
        int u;
        int v;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
        }
    }

    public void bfs(int start) {
        queue = new LinkedList<>();
        queue.offer(start);

        visited[start] = 1;
        while (!queue.isEmpty()) {
            int visitNode = queue.poll();

            for (int nextNode : graph.get(visitNode)) {
                if (visited[visitNode] == visited[nextNode])
                    result = 1;
                if (visited[nextNode] == 0) {
                    visited[nextNode] = visited[visitNode] * -1;
                    queue.offer(nextNode);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ01707().solution();
    }

}