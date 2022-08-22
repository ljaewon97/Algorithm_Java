package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_07562_나이트의_이동 {
	static boolean[][] visited;
	static int l, tr, tc, ans;
	static int[] dr = {-1,-2,-2,-1,1,2,2,1};
	static int[] dc = {-2,-1,1,2,2,1,-1,-2};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			l = Integer.parseInt(br.readLine());
			
			visited = new boolean[l][l];
			
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			tr = Integer.parseInt(st.nextToken());
			tc = Integer.parseInt(st.nextToken());
			
			bfs(sr, sc);
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			
			if(r == tr && c == tc) {
				ans = d;
				return;
			}
			
			for(int i = 0; i < 8; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc, d+1});
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < l && 0 <= c && c < l;
	}
}
