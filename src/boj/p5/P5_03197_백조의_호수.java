package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5_03197_백조의_호수 {
	static Queue<int[]> waterQueue = new LinkedList<>();
	static Queue<int[]>[] swanQueue = new LinkedList[3];
	static char[][] map;
	static boolean[][] visited;
	static int[][] swanArea;
	static int R, C, swan = 1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		visited = new boolean[R][C];
		swanArea = new int[R][C];
		swanQueue[1] = new LinkedList<>();
		swanQueue[2] = new LinkedList<>();
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] != 'X' && !visited[i][j]) {
					waterBfs(i, j);
				}
			}
		}
		
		int day = 1;
		
		while(true) {	
			melt();
			if(swanMove()) break;
			
			day++;
		}
		
		System.out.println(day);
	}
	
	static void melt() {
		int size = waterQueue.size();
		
		while(size-- > 0) {
			int[] cur = waterQueue.poll();
			int r = cur[0];
			int c = cur[1];
			
			map[r][c] = '.';
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					waterQueue.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static boolean swanMove() {
		for(int s = 1; s <= 2; s++) {
			Queue<int[]> temp = new LinkedList<>();
			
			while(!swanQueue[s].isEmpty()) {
				int[] cur = swanQueue[s].poll();
				int r = cur[0];
				int c = cur[1];
				
				for(int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(isIn(nr, nc) && map[nr][nc] == '.' && swanArea[nr][nc] != 0 && swanArea[nr][nc] != s) {
						return true;
					}
					else if(isIn(nr, nc) && map[nr][nc] == '.' && swanArea[nr][nc] == 0) {
						swanArea[nr][nc] = s;
						swanQueue[s].add(new int[] {nr, nc});
					}
					else if(isIn(nr, nc) && map[nr][nc] == 'X' && swanArea[nr][nc] == 0) {
						swanArea[nr][nc] = s;
						temp.add(new int[] {nr, nc});
					}
				}
			}
			
			swanQueue[s] = temp;
		}
		
		return false;
	}
	
	static void waterBfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr,sc});
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(map[r][c] == 'L') {
				swanBfs(r, c, swan);
				swan++;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'X') {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
				else if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'X') {
					visited[nr][nc] = true;
					waterQueue.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static void swanBfs(int sr, int sc, int num) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr,sc});
		swanArea[sr][sc] = num;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && swanArea[nr][nc] == 0 && map[nr][nc] != 'X') {
					swanArea[nr][nc] = num;
					queue.add(new int[] {nr, nc});
				}
				else if(isIn(nr, nc) && swanArea[nr][nc] == 0 && map[nr][nc] == 'X') {
					swanArea[nr][nc] = num;
					swanQueue[num].add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
