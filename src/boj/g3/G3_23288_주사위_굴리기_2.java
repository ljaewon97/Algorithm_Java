package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G3_23288_주사위_굴리기_2 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dice = {2,1,5,6,4,3};
	static int N, M, r, c, ans, dir = 1;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < K; i++) {
			roll();
		}
		
		System.out.println(ans);
	}
	
	static void roll() {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if(!isIn(nr, nc)) {
			dir = (dir + 2) % 4;
			roll();
			return;
		}
		
		if(dir == 0) {
			int temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = dice[0];
			dice[0] = temp;
		}
		else if(dir == 1) {
			int temp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[3];
			dice[3] = dice[5];
			dice[5] = temp;
		}
		else if(dir == 2) {
			int temp = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[2];
			dice[2] = temp;
		}
		else if(dir == 3) {
			int temp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = dice[4];
			dice[4] = temp;
		}	
		
		int A = dice[3];
		int B = map[nr][nc];
		
		if(A > B) dir = (dir + 1) % 4;
		else if(A < B) dir = (dir + 3) % 4;
		
		r = nr;
		c = nc;
		
		ans += bfs(r, c) * B;
	}
	
	static int bfs(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		visited = new boolean[N][M];
		deque.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		int v = map[sr][sc];
		int cnt = 0;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			cnt++;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == v) {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc});
				}
			}
		}
		
		return cnt;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}