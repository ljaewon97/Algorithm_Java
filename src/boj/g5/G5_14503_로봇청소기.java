package boj.g5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_14503_로봇청소기 {
	static int[][] map;
	static int N, M;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			if(map[r][c] == 0) {
				map[r][c] = 2;
				ans++;
			}
			
			boolean moved = false;
			for(int i = 0; i < 4; i++) {
				d = (d + 3) % 4;
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(isIn(nr, nc) && map[nr][nc] == 0) {
					r = nr;
					c = nc;
					moved = true;
					break;
				}
			}
			
			if(!moved) {
				int nr = r - dr[d];
				int nc = c - dc[d];
				if(isIn(nr, nc) && map[nr][nc] != 1) {
					r = nr;
					c = nc;
				}
				else {
					break;
				}
			}
		}
		System.out.println(ans);
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
