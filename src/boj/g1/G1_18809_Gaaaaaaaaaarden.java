package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_18809_Gaaaaaaaaaarden {
	static int[][] map, visited;
	static List<Point> startpoints = new ArrayList<>();
	static Point[] result;
	static boolean[] reds;
	static int N, M, G, R, S, ans;
	static final int D = 1000000;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		S = G + R;
		
		map = new int[N][M];
		result = new Point[S];
		reds = new boolean[S];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					startpoints.add(new Point(i, j));
				}
			}
		}
			
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int nth, int start) {
		if(nth == S) {
			selectRed(0, 0);
			return;
		}
		
		for(int i = start; i < startpoints.size(); i++) {
			result[nth] = startpoints.get(i);
			comb(nth+1, i+1);
		}
	}
	
	static void selectRed(int nth, int idx) {
		if(nth == R) {
			bfs();
			return;
		}
		
		if(idx == S) return;
		
		reds[idx] = true;
		selectRed(nth+1, idx+1);
		reds[idx] = false;
		selectRed(nth, idx+1);
	}
	
	static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		visited = new int[N][M];
		int flower = 0;
		
		// 0: red, 1: green
		for(int i = 0; i < S; i++) {
			if(reds[i]) {
				queue.add(new int[] {result[i].r, result[i].c, 1, 0});
				visited[result[i].r][result[i].c] = 1;
			}
			else {
				queue.add(new int[] {result[i].r, result[i].c, D+1, 0});
				visited[result[i].r][result[i].c] = D+1;
			}
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			int color = cur[3];
			
			if(visited[r][c] == 2000000) continue;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				if(visited[nr][nc] == 0 && map[nr][nc] != 0) {
					visited[nr][nc] = d + 1;
					queue.add(new int[] {nr, nc, d+1, color});
				}
				else if(d - visited[nr][nc] == D-1 || visited[nr][nc] - d == D+1) {
					flower++;
					visited[nr][nc] = 2000000;
				}
			}
		}
		
		ans = Math.max(ans, flower);
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}

class Point {
	int r;
	int c;
	
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}