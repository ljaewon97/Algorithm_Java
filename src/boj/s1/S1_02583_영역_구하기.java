package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_02583_영역_구하기 {
	static boolean[][] map, visited;
	static int M, N, K;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[M][N];
		visited = new boolean[M][N];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int x = x1; x < x2; x++) {
				for(int y = y1; y < y2; y++) {
					map[y][x] = true;
				}
			}
		}
		
		int ans = 0;
		List<Integer> areas = new ArrayList<>();
		
		for(int r = 0; r < M; r++) {
			for(int c = 0; c < N; c++) {
				if(!map[r][c] && !visited[r][c]) {
					areas.add(bfs(r, c));
					ans++;
				}
			}
		}
		
		Collections.sort(areas);
		
		for(int area: areas) {
			sb.append(area).append(" ");
		}
		
		System.out.println(ans);
		System.out.println(sb);
	}
	
	static int bfs(int sr, int sc) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			cnt++;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && !map[nr][nc]) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
		
		return cnt;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < M && 0 <= c && c < N;
	}
}
 