package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_15685_드래곤_커브 {
	static boolean[][] map = new boolean[101][101];
	static int N;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dragonCurve(r, c, d, g);
		}
		
		int cnt = 0;
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	static void dragonCurve(int r, int c, int d, int g) {
		map[r][c] = true;
		
		List<Integer> dirs = new ArrayList<>();
		dirs.add(d);
		
		for(int i = 1; i <= g; i++) {
			int size = dirs.size();
			
			for(int j = 0; j < size; j++) {
				d = (dirs.get(size-1-j) + 1) % 4;
				dirs.add(d);
			}
		}
		
		for(int dir: dirs) {
			r += dr[dir];
			c += dc[dir];
			
			map[r][c] = true;
		}
	}
}
