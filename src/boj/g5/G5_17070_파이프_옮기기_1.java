package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_17070_파이프_옮기기_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][][] dp = new int[N+1][N+1][3];
		int[][] map = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				if(r == 1 && c == 2) {
					dp[1][2][0] = 1;
					continue;
				}
				
				if(map[r][c] == 0) {
					dp[r][c][0] = dp[r][c-1][0] + dp[r][c-1][1];
					dp[r][c][2] = dp[r-1][c][2] + dp[r-1][c][1];
				}
				
				if(map[r][c] == 0 && map[r-1][c] == 0 && map[r][c-1] == 0) {
					dp[r][c][1] = dp[r-1][c-1][0] + dp[r-1][c-1][1] + dp[r-1][c-1][2];
				}
			}
		}
		
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
	}
}

////BFS ver.
//public class G5_17070_파이프_옮기기_2 {
//	static int[][] map;
//	static int N, ans;
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		
//		N = Integer.parseInt(br.readLine());
//		map = new int[N+1][N+1];
//		
//		for(int i = 1; i <= N; i++) {
//			st = new StringTokenizer(br.readLine());
//			
//			for(int j = 1; j <= N; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}	
//		
//		bfs();
//		
//		System.out.println(ans);
//	}
//	
//	static void bfs() {
//		Queue<Pipe> queue = new ArrayDeque<>();
//		queue.add(new Pipe(1, 2, 0));
//		
//		while(!queue.isEmpty()) {
//			Pipe cur = queue.poll();
//			
//			if(cur.r == N && cur.c == N) {
//				ans++;
//				continue;
//			}
//			
//			if(isIn(cur.r+1, cur.c+1) && map[cur.r+1][cur.c+1] == 0 && map[cur.r+1][cur.c] == 0 && map[cur.r][cur.c+1] == 0) {
//				queue.add(new Pipe(cur.r+1, cur.c+1, 1));
//			}
//			
//			if(cur.dir != 2) {
//				if(isIn(cur.r, cur.c+1) && map[cur.r][cur.c+1] == 0) {
//					queue.add(new Pipe(cur.r, cur.c+1, 0));
//				}
//			}
//			
//			if(cur.dir != 0) {
//				if(isIn(cur.r+1, cur.c) && map[cur.r+1][cur.c] == 0) {
//					queue.add(new Pipe(cur.r+1, cur.c, 2));
//				}
//			}
//		}
//	}
//	
//	static boolean isIn(int r, int c) {
//		return 1 <= r && r <= N && 1 <= c && c <= N;
//	}
//	
//	static class Pipe {
//		int r;
//		int c;
//		int dir;
//		
//		public Pipe(int r, int c, int dir) {
//			this.r = r;
//			this.c = c;
//			this.dir = dir;
//		}
//	}
//}