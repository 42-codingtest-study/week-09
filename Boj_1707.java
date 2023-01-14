import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1707 {

    public static void main(String[] args) throws IOException {
        int n;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int v, e;
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            List<List<Integer>> table = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                table.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                int from, to;
                st = new StringTokenizer(bf.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                table.get(from).add(to);
                table.get(to).add(from);
            }
            int visited[] = new int[v+1];       //visited가 0이면 한번도 방문 안한거고, 1,2 를 왔다리 갔다리 하자.
            dfs_func(v,table, visited);

        }
    }

    public static void dfs_func(int v, List<List<Integer>> table, int [] visited) {
        Queue<Integer> q = new LinkedList<>();

        for (int node = 1; node <= v; node++) {
            if (visited[node] == 0) {
                q.add(node);           //1번 정점을 VIsited 1 로 하여 시작
                visited[node] = 1;
            }
            while (!q.isEmpty()) {  // node 점을 시작으로 BFS 시작.
                int cur = q.peek();
                q.remove();

                for (int i = 0; i < table.get(cur).size(); i++) {
                    int next_c = table.get(cur).get(i);
                    if (visited[next_c] == 0) { // 아직 한번도 방문 안한점이라니까
                        q.add(next_c);
                        visited[next_c] = (visited[cur] * 2)%3;         // 1 이었음 2가, 2였으면 1이 들어갈 수 있도록 함.
                    } else {
                        // 여기서 만약 cur 의 visited 값과 next_C 로 배정되어야 할 값이 일치한다고 나오는 경우가 사고가 난 경우임.
                        if (visited[cur] == visited[next_c]) {
                            System.out.println("NO");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("YES");
    }
}
