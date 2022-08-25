package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_20005_보스몬스터_전리품 {
	static char[][] map;
	static boolean[][] visited;
	static int[] damages;
	static int N, M, P, hp, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];
		int bossr = -1, bossc = -1;
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			
			for(int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
				
				if(map[r][c] == 'B') {
					bossr = r; bossc = c;
				}
			}
		}
		
		damages = new int[26];
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			damages[st.nextToken().charAt(0) - 'a'] = Integer.parseInt(st.nextToken());
		}
		
		hp = Integer.parseInt(br.readLine());
		
		Queue<int[]> players = bfs(bossr, bossc);
		
		int dmg = 0;
		int prev = -1;
		
		while(!players.isEmpty()) {
			int[] cur = players.poll();
			int dist = cur[0];
			
			hp -= dmg * (dist - prev);
			if(hp <= 0) break;
			
			dmg += cur[1];
			ans++;
			
			while(!players.isEmpty() && players.peek()[0] == dist) {
				int[] cur2 = players.poll();
				dmg += cur2[1];
				ans++;
			}
			
			prev = dist;
		}
		
		System.out.println(ans);
	}
	
	static Queue<int[]> bfs(int sr, int sc) {
		Queue<int[]> res = new LinkedList<>();
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {	
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			
			if('a' <= map[r][c] && map[r][c] <= 'z') {
				res.add(new int[] {d, damages[map[r][c] - 'a']});
				cnt++;
			}
			
			if(cnt == P) break;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'X') {
					queue.add(new int[] {nr, nc, d+1});
					visited[nr][nc] = true;	
				}
			}
		}
		
		return res;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
