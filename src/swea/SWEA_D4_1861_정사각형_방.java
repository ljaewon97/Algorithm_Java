package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_D4_1861_정사각형_방 {
	static int[][] map;
	static boolean[][] visited;
	static int N, ans, start;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			ans = 0; start = 0;
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) { 
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			
			sb.append(String.format("#%d %d %d\n", t, start, ans));
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		int cnt = 0, min = Integer.MAX_VALUE;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int dist = cur[2];
			
			if(map[r][c] < min) {
				min = map[r][c];
			}
			cnt++;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && Math.abs(map[nr][nc] - map[r][c]) <= 1) {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc, dist+1});
				}
			}
		}
		
		if(cnt > ans) {
			ans = cnt;
			start = min;
		}
		else if(cnt == ans) {
			if(start > min) {
				start = min;
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
