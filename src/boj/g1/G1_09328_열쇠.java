package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class G1_09328_열쇠 {
	static char[][] map;
	static boolean[][] visited;
	static boolean[] keys;
	static List<int[]>[] doors;
	static int H, W, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H+2][W+2];
			visited = new boolean[H+2][W+2];
			keys = new boolean[26];
			doors = new ArrayList[26];
			
			for(int i = 0; i < 26; i++) {
				doors[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < H+2; i++) {
				map[i][0] = '.';
				map[i][W+1] = '.';
			}
			
			for(int i = 0; i < W+2; i++) {
				map[0][i] = '.';
				map[H+1][i] = '.';
			}
			
			for(int i = 1; i < H+1; i++) {
				String line = br.readLine();
				for(int j = 1; j < W+1; j++) {
					map[i][j] = line.charAt(j-1);
				}
			}
			
			String line = br.readLine();
			if(!line.equals("0")) {
				for(int i = 0; i < line.length(); i++) {
					keys[line.charAt(i) - 'a'] = true;
				}
			}
			
			bfs();
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {0, 0});
		visited[0][0] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != '*') {
					if(map[nr][nc] == '.') {
						visited[nr][nc] = true;
						deque.add(new int[] {nr, nc});
					}
					else if(map[nr][nc] == '$') {
						ans++;
						visited[nr][nc] = true;
						deque.add(new int[] {nr, nc});
					}
					else if('a' <= map[nr][nc] && map[nr][nc] <= 'z') {
						visited[nr][nc] = true;
						deque.add(new int[] {nr, nc});
						int keyNum = map[nr][nc] - 'a';
						
						if(!keys[keyNum]) {
							keys[keyNum] = true;
						}
						
						if(!doors[keyNum].isEmpty()) {
							for(int[] door: doors[keyNum]) {
								deque.add(new int[] {door[0], door[1]});
								visited[door[0]][door[1]] = true;
							}
							doors[keyNum].clear();
						}
					}
					else if('A' <= map[nr][nc] && map[nr][nc] <= 'Z') {
						if(keys[map[nr][nc] - 'A']) {
							visited[nr][nc] = true;
							deque.add(new int[] {nr, nc});
						}
						else {
							doors[map[nr][nc] - 'A'].add(new int[] {nr, nc});
						}
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < H+2 && 0 <= c && c < W+2;
	}
}
