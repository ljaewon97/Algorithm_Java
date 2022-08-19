package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class G3_17135_캐슬_디펜스 {
	static int[][] map;
	static int[][] visited;
	static int[] loc;
	static int N, M, D, cnt, ans;
	static int[] dr = {0,-1,0};
	static int[] dc = {-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		loc = new int[3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void defense() {
		int[][] arr = new int[N][];
		cnt = 0;
		
		for(int i = 0; i < N; i++) {
			arr[i] = Arrays.copyOf(map[i], M);
		}
		
		visited = new int[N+1][M];
		for(int r = N-1; r >= 0; r--) {
			
			for(int i = 0; i < N+1; i++) {
				for(int j = 0; j < M; j++) {
					visited[i][j] = -1;
				}
			}
			
			List<int[]> pend = new LinkedList<>();
			
			for(int i = 0; i < 3; i++) {
				int[] killed = shoot(arr, r+1, loc[i], i+3*(N-1-r));
			
				if(killed == null) continue;

				pend.add(killed);
			}
			
			for(int[] p: pend) {
				if(arr[p[0]][p[1]] == 1) {
					arr[p[0]][p[1]] = 0;
					cnt++;
				}
			}
			
			for(int c = 0; c < M; c++) {
				arr[r][c] = 0;
			}
		}
		
		ans = Math.max(ans, cnt);
	}
	
	static int[] shoot(int[][] arr, int sr, int sc, int code) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sr, sc, 0});
		visited[sr][sc] = code;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int dist = cur[2];
			
			if(dist == D) continue;
			
			for(int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && visited[nr][nc] != code) {
					if(arr[nr][nc] == 0) {
						deque.add(new int[] {nr, nc, dist+1});
						visited[nr][nc] = code;
					}
					else {
						return new int[] {nr, nc};
					}
				}
			}
		}
		
		return null;
	}
	
	static void comb(int nth, int start) {
		if(nth == 3) {
			defense();
			return;
		}
		
		for(int i = start; i < M; i++) {
			loc[nth] = i;
			comb(nth+1, i+1);
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
