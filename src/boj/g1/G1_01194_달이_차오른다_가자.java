package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G1_01194_달이_차오른다_가자 {
	static char[][] map;
	static boolean[][][] visited;
	static int N, M, ans = -1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[64][N][M];
		map = new char[N][];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == '0') {
					map[i][j] = '.';
					bfs(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sr, sc, 0, 0});
		visited[0][sr][sc] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			int key = cur[3];
			
			if(map[r][c] == '1') {
				ans = d;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!isIn(nr, nc) || map[nr][nc] == '#' || visited[key][nr][nc]) {
					continue;
				}
				
				if('a' <= map[nr][nc] && map[nr][nc] <= 'f') {
					int newKey = (1 << (map[nr][nc] - 'a')) | key;
					
					if(!visited[newKey][nr][nc]) {
						visited[newKey][nr][nc] = true;
						visited[key][nr][nc] = true;
						deque.add(new int[] {nr, nc, d+1, newKey});
					}
				}
				else if('A' <= map[nr][nc] && map[nr][nc] <= 'F') {
					int temp = 1 << (map[nr][nc] - 'A');
					
					if((~key & temp) != temp) {
						visited[key][nr][nc] = true;
						deque.add(new int[] {nr, nc, d+1, key});
					}
				}
				else {
					visited[key][nr][nc] = true;
					deque.add(new int[] {nr, nc, d+1, key});
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
