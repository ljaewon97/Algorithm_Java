package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G3_17090_미로_탈출하기 {
	static char[][] maze;
	static boolean[][] visited;
	static int N, M, ans = 0;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static char[] ch = {'D','U','R','L'};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new char[N][];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			maze[i] = br.readLine().toCharArray();
		}
		
		for(int r = 0; r < N; r++) {
			if(!visited[r][0] && maze[r][0] == 'L') bfs(r, 0);
			if(!visited[r][M-1] && maze[r][M-1] == 'R') bfs(r, M-1);
		}
		
		for(int c = 0; c < M; c++) {
			if(!visited[0][c] && maze[0][c] == 'U') bfs(0, c);
			if(!visited[N-1][c] && maze[N-1][c] == 'D') bfs(N-1, c);
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
			ans++;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				char before = ch[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && maze[nr][nc] == before) {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
