package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class G3_17135_캐슬_디펜스 {
	static int[][] map;
	static int[][] visited;
	static int[] loc;
	static int N, M, D, enemy, enemyCnt, cnt, ans;
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
				if(map[i][j] == 1) enemyCnt++;
			}
		}
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void defense() {
		int[][] arr = new int[N][M];
		enemy = enemyCnt;
		cnt = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = map[i][j];
			}
		}
		
		while(enemy > 0) {
			visited = new int[N+1][M];
			for(int i = 0; i < N+1; i++) {
				for(int j = 0; j < M; j++) {
					visited[i][j] = -1;
				}
			}
			
			List<int[]> pend = new LinkedList<>();
			
			for(int i = 0; i < 3; i++) {
				int[] killed = shoot(arr, N, loc[i], i);
			
				if(killed == null) continue;
				
				int r = killed[0];
				int c = killed[1];
				
				if(arr[r][c] == 1) {
					pend.add(killed);
				}
			}
			
			for(int[] p: pend) {
				if(arr[p[0]][p[1]] == 1) {
					arr[p[0]][p[1]] = 0;
					enemy--;
					cnt++;
				}
			}
			
			arr = move(arr);
		}
		
		ans = Math.max(ans, cnt);
	}
	
	static int[][] move(int[][] arr) {
		for(int i = 0; i < M; i++) {
			if(arr[N-1][i] == 1) {
				enemy--;
			}
		}
		
		for(int i = N-1; i > 0; i--) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = arr[i-1][j];
			}
		}
		
		for(int i = 0; i < M; i++) {
			arr[0][i] = 0;
		}
		
		return arr;
	}
	
	static int[] shoot(int[][] arr, int sr, int sc, int code) {
		Deque<int[]> deque = new LinkedList<>();
		List<int[]> killed = new ArrayList<>();
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
						killed.add(new int[] {nr, nc, dist+1});
					}
				}
			}
		}
		
		Collections.sort(killed, (o1, o2) -> o1[2] == o2[2] ? o1[1] - o2[1] : o1[2] - o2[2]);
		
		if(killed.isEmpty()) return null;
		else return killed.get(0);
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
