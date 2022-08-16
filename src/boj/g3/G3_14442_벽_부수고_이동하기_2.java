package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G3_14442_벽_부수고_이동하기_2 {
	static char[][] map;
	static int[][] visited;
	static int N, M, K, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		visited = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		if(N + M == 2) {
			System.out.println(1);
			return;
		}
		
		bfs();
		
		System.out.println(ans == 0 ? -1 : ans);
	}
	
	static void bfs() {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {0, 0, 1, 0});
		visited[0][0] |= 1 << 0;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			int crack = cur[3];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc)) {
					if(nr == N-1 && nc == M-1) {
						ans = d + 1;
						return;
					}
					else if((visited[nr][nc] & (1 << crack)) == 0 && map[nr][nc] == '0') {
						visited[nr][nc] |= 1 << crack;
						deque.add(new int[] {nr, nc, d+1, crack});
					}
					else if((visited[nr][nc] & (1 << crack)) == 0 && (visited[nr][nc] & (1 << crack+1)) == 0 && map[nr][nc] == '1' && crack < K) {
						visited[nr][nc] |= 1 << crack+1;
						deque.add(new int[] {nr, nc, d+1, crack+1});
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
