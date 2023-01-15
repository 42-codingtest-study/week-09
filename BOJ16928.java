import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {

    static int[] board;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        setBoard(st, br);

        int result = bfs(1);
        System.out.println(result);
    }

    /**
     * [보드 값 설정]
     *
     * 1. 자기 인덱스 값을 원소로 초기화 합니다
     * 2. 사다리 또는 뱀의 값을 원소로 넣어줍니다
     */
    private static void setBoard(StringTokenizer st, BufferedReader br) throws IOException {
        int ladder = Integer.parseInt(st.nextToken());
        int snake = Integer.parseInt(st.nextToken());

        board = new int[101];

        for (int i = 1; i < board.length; i++)
            board[i] = i;

        setValueFor(ladder, st, br);
        setValueFor(snake, st, br);
    }

    private static void setValueFor(int range, StringTokenizer st, BufferedReader br)
        throws IOException {
        for (int i = 0; i < range; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int des = Integer.parseInt(st.nextToken());

            board[idx] = des;
        }
    }

    /**
     * [bfs]
     *
     * 1.   visited 방문 유무를 확인하는 배열
     *      visited 값 0 은 방문하지 않음을 말해줍니다
     *      visited 배열은 방문 유무 뿐만 아니라 몇번째 방문인지의 데이터로서의 역할도 합니다
     *      방문하기 이전의 visited 값 + 1 을 저장하면 위 사항을 구현할 수 있습니다
     * 2.   queue 다음 출발 위치를 저장하고 반환하는 배열
     * 3.   출발 위치에서 1 ~ 6 까지 이동이 가능합니다
     *      이동한 위치에 방문횟수 값을 넣는 것이 아니라
     *      뱀 또는 사다리의 결과 값을 기준으로 방문횟수 값을 넣습니다
     *      예를들어 4 7 사다리가 있는 칸에 도달했을 때 visit[4]에 값을 넣는 것이 아니라
     *      visit[board[7]]에 값을 넣습니다
     *      visit[4]에는 값을 넣을 필요가 없습니다
     *      어짜피 bfs 특성상 100에 도달하는 순간
     *      return 하기 때문입니다
     */

    public int bfs(int start) {
        int[] visited = new int[101];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = 0;
        while (!queue.isEmpty()) {
            int curPos = queue.poll();

            for (int i = 1; i <= 6; i++) {
                int movPos = curPos + i;

                if (movPos <= 100 && visited[board[movPos]] == 0) {
                    queue.offer(board[movPos]);
                    visited[board[movPos]] = visited[curPos] + 1;
                }
                if (movPos == 100) {
                    return visited[100];
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        new BOJ16928().solution();
    }
}
