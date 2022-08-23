package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G5_21608_상어_초등학교 {
	static int[][] map, favor;
	static int[] index;
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		favor = new int[N*N][5];
		index = new int[N*N+1];
		
		for(int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			favor[i][0] = Integer.parseInt(st.nextToken());
			favor[i][1] = Integer.parseInt(st.nextToken());
			favor[i][2] = Integer.parseInt(st.nextToken());
			favor[i][3] = Integer.parseInt(st.nextToken());
			favor[i][4] = Integer.parseInt(st.nextToken());
			
			index[favor[i][0]] = i;
		}
		
		for(int i = 0; i < N*N; i++) {
			int num = favor[i][0];
			
			List<int[]> cand = search(i);
			
			int[] cord = cand.get(0);
			int r = cord[2];
			int c = cord[3];
			
			map[r][c] = num;
		}
		
		int sum = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int adj = 0;
				
				for(int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(!isIn(nr, nc)) continue;
					
					if(check(nr, nc, index[map[r][c]])) adj++;
				}
				
				if(adj == 0) continue;
				
				int temp = 1;
				
				for(int k = 0; k < adj-1; k++) {
					temp *= 10;
				}
				
				sum += temp;
			}
		}
		
		System.out.println(sum);
	}
	
	static List<int[]> search(int idx) {
		List<int[]> res = new ArrayList<>();
		int max = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] == 0) {
					int adj = 0, empty = 0;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(!isIn(nr, nc)) continue;
						
						if(check(nr, nc, idx)) adj++;
						else if(map[nr][nc] == 0) empty++;
					}
					
					if(adj >= max) {
						max = adj;
						res.add(new int[] {adj, empty, r, c});
					}
				}
			}
		}
		
		Collections.sort(res, (o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : (o1[1] != o2[1] ? o2[1] - o1[1] : 0));
		
		return res;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static boolean check(int r, int c, int idx) {
		int a = map[r][c];
		return a == favor[idx][1] || a == favor[idx][2] || a == favor[idx][3] || a == favor[idx][4];
	}
}
