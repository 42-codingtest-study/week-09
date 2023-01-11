import java.util.*;

public class Boj_16928 {

    public static void main(String[] args) {
        int n,m;
        Scanner scanner = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        List<List<Integer>> table = new ArrayList<>();
        for (int i = 0; i <= 106; i++) {
            table.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st2 = new StringTokenizer(scanner.nextLine());
            int from = Integer.parseInt(st2.nextToken());
            int to = Integer.parseInt(st2.nextToken());
            table.get(from).add(to);
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st3 = new StringTokenizer(scanner.nextLine());
            int from = Integer.parseInt(st3.nextToken());
            int to = Integer.parseInt(st3.nextToken());
            table.get(from).add(to);
        }               // n + m 으로 for 문 돌리면 한번에 할 수 있을거 같은데..

        // 그럼 현재 리스트에 그냥 줄줄이 붙어있는 경우는 없고, 다리나 뱀 타고 이동하는 경우만 남아있다.
        //dfs 시작
        Queue<Integer> q = new LinkedList<>();
        int visited[] = new int[110];
        q.add(1);
        visited[1] = 1;

        while (!q.isEmpty()) {
            int cur = q.peek();
            q.remove();
            for (int i = 1; i <= 6; i++) {
                int n_c = cur + i;
                if (n_c == 100) {
                    System.out.println(visited[cur]);
                    return;
                }
                else if (n_c < 100) {
                    if (table.get(cur + i).size() == 1) {
                        n_c = table.get(cur+i).get(0);
                    }
                    if (visited[n_c] == 0) {
                        q.add(n_c);
                        visited[n_c] = visited[cur] + 1;
                    }
                }
            }
        }
    }
}
