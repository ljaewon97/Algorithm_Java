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
	static List<Queue<int[]>> lakes = new ArrayList<>();
	static List<Integer> swanLoc = new ArrayList<>();
	static Queue<int[]> pend = new LinkedList<>();
	static char[][] map;
	static int[][] visited;
	static int[] parent;
	static int R, C, lake = 1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		visited = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if((map[i][j] == '.' || map[i][j] == 'L') && visited[i][j] == 0) {
					bfs(i, j);
					lake++;
				}
			}
		}
		
		parent = new int[lakes.size()+1];
		
		for(int i = 1; i <= lakes.size(); i++) {
			parent[i] = i;
		}
		
		while(!pend.isEmpty()) {
			int[] cur = pend.poll();
			union(cur[0], cur[1]);
		}
		
		int ans = 1;
		
		while(true) {
			int p1 = find(swanLoc.get(0));
			int p2 = find(swanLoc.get(1));
			
			if(p1 == p2) break;
			
			if(search()) break;
			
			melt();

			ans++;

			System.out.println(Arrays.toString(parent));
	
		}
		
		for(int[] row: visited) {
			System.out.println(Arrays.toString(row));
		}
		
		
		
		System.out.println(ans);
	}
	
	static boolean search() {
		Queue<int[]> queue = lakes.get(swanLoc.get(0));
		int p = find(swanLoc.get(0));
		
		for(int[] cur: queue) {
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && visited[nr][nc] != 0 && find(visited[nr][nc]) == p) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	static void melt() {
		int l = 1;
		
		for(Queue<int[]> queue: lakes) {
			int size = queue.size();
			
			while(size-- > 0) {
				int[] cur = queue.poll();
				int r = cur[0];
				int c = cur[1];
				
				for(int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(isIn(nr, nc) && visited[nr][nc] == 0) {
						visited[nr][nc] = l;
						queue.add(new int[] {nr, nc});
					}
					else if(isIn(nr, nc) && visited[nr][nc] != 0) {
						union(visited[nr][nc], l);
					}
				}
			}
			
			l++;
		}
	}
	
	static void bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> bound = new LinkedList<>();
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = lake;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(map[r][c] == 'L') swanLoc.add(lake);
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && visited[nr][nc] == 0 && (map[nr][nc] == '.' || map[nr][nc] == 'L')) {
					visited[nr][nc] = lake;
					queue.add(new int[] {nr, nc});
				}
				else if(isIn(nr, nc) && visited[nr][nc] != lake && map[nr][nc] == 'X') {
					if(visited[nr][nc] != 0) {
						pend.add(new int[] {visited[nr][nc], lake});
					}
					visited[nr][nc] = lake;
					bound.add(new int[] {nr, nc});
				}
			}
		}
		
		lakes.add(bound);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		
		return parent[node] = find(parent[node]);
	}
	
	static boolean union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		
		if(p1 == p2) return false;
		
		parent[p1] = p2;
		return true;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
