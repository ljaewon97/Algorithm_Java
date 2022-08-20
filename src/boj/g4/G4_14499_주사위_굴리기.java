package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_14499_주사위_굴리기 {
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int[] dice = new int[6];
	static int N, M, r, c;
	static int[] dr = {0,0,0,-1,1};
	static int[] dc = {0,1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			roll(Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(sb);
	}
	
	static void roll(int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if(!isIn(nr, nc)) return;
		
		if(dir == 1) {
			int temp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[3];
			dice[3] = dice[5];
			dice[5] = temp;
		}
		else if(dir == 2) {
			int temp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = dice[4];
			dice[4] = temp;
		}
		else if(dir == 3) {
			int temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = dice[0];
			dice[0] = temp;
		}
		else {
			int temp = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[2];
			dice[2] = temp;
		}
		
		int upper = dice[1];
		int lower = dice[3];
		
		if(map[nr][nc] == 0) {
			map[nr][nc] = lower;
		}
		else {
			dice[3] = map[nr][nc];
			map[nr][nc] = 0;
		}
		
		r = nr;
		c = nc;
		sb.append(upper).append("\n");
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
