package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G3_16724_피리_부는_사나이 {
	static char[][] map;
	static boolean[][] visited;
	static int N, M, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static char[] before = {'D','U','R','L'};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int dir = getDir(map[r][c]);
			
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if(!visited[nr][nc]) {
				deque.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
			
			for(int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				char b = before[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == b) {
					deque.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	static int getDir(char c) {
		switch(c) {
		case 'U': return 0;
		case 'D': return 1;
		case 'L': return 2;
		case 'R': return 3;
		}
		return 0;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
