import java.util.*;

public class Boj_16928 {

    public static void main(String[] args) {
        int n,m;
        Scanner scanner = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());       //n,m 입력
        List<List<Integer>> table = new ArrayList<>();
        for (int i = 0; i <= 106; i++) {
            table.add(new ArrayList<>());       //리스트 배열 초기화
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
        }

        // 그럼 현재 리스트에 그냥 줄줄이 붙어있는 경우는 없고, 다리나 뱀 타고 이동하는 경우만 남아있다.
        //bfs 시작
        Queue<Integer> q = new LinkedList<>();
        int visited[] = new int[110];
        q.add(1);
        visited[1] = 1;

        while (!q.isEmpty()) {
            int cur = q.peek();
            q.remove();
            for (int i = 1; i <= 6; i++) {  //주사위 1~6칸 이동할 경우에 대해 모두.
                int n_c = cur + i;
                if (n_c == 100) {
                    System.out.println(visited[cur]);
                    return;
                }
                else if (n_c < 100) {
                    if (table.get(cur + i).size() == 1) {   //cur 칸에 이동할 뱀이나 사다리가 있는 경우
                        n_c = table.get(cur+i).get(0);  //이동할 좌표 변경
                    }
                    if (visited[n_c] == 0) {        //이동할 좌표가 방문 안한 지점일 때.
                        q.add(n_c);
                        visited[n_c] = visited[cur] + 1;
                    }
                }
            }
        }
    }
}
