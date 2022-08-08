package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_16234_인구_이동 {
	static int[][] map;
	static boolean[][] visited;
	static int N, L, R;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			boolean move = false;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						if(bfs(i, j)) {
							move = true;
						}
					}
				}
			}
			
			if(move) ans++;
			else break;
		}
		
		System.out.println(ans);
	}
	
	static boolean bfs(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		List<int[]> ally = new ArrayList<>();	
		deque.add(new int[] {sr, sc});
		ally.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		int sum = map[sr][sc];
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc]) {
					int diff = Math.abs(map[r][c] - map[nr][nc]);
					
					if(L <= diff && diff <= R) {
						visited[nr][nc] = true;
						sum += map[nr][nc];
						deque.add(new int[] {nr, nc});
						ally.add(new int[] {nr, nc});
					}
				}
			}
		}
		
		if(ally.size() > 1) {
			int newP = sum / ally.size();
			
			for(int[] cord: ally) {
				map[cord[0]][cord[1]] = newP;
			}
			
			ally.clear();
			return true;
		}
		
		ally.clear();
		return false;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
